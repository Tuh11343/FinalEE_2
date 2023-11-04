package FinalEE.Controller;

import FinalEE.Entity.Account;
import FinalEE.ServiceImpl.*;
import jakarta.json.JsonObject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;


import java.io.IOException;
import java.io.PrintWriter;


public class HeaderServlet extends HttpServlet {

    private AccountServiceImpl accountServiceImpl;
    private CustomerServiceImpl customerServiceImpl;
    private DiscountCardServiceImpl discountCardServiceImpl;
    private ItemServiceImpl itemServiceImpl;
    private ItemCollectionServiceImpl itemCollectionServiceImpl;
    private ItemImageServiceImpl itemImageServiceImpl;
    private ItemMaterialServiceImpl itemMaterialServiceImpl;
    private OrderServiceImpl orderServiceImpl;
    private OrderDetailServiceImpl orderDetailServiceImpl;
    private ItemTypeServiceImpl itemTypeServiceImpl;
    private PermissionServiceImpl permissionServiceImpl;
    private SaleServiceImpl saleServiceImpl;
    private StockItemServiceImpl stockItemServiceImpl;
    private CartServiceImpl cartServiceImpl;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            ServletContext servletContext = getServletContext();
            HttpSession session=req.getSession();

            accountServiceImpl = (AccountServiceImpl) servletContext.getAttribute("accountServiceImpl");
            cartServiceImpl = (CartServiceImpl) servletContext.getAttribute("cartServiceImpl");
            customerServiceImpl = (CustomerServiceImpl) servletContext.getAttribute("customerServiceImpl");
            discountCardServiceImpl = (DiscountCardServiceImpl) servletContext.getAttribute("discountCardServiceImpl");
            itemServiceImpl = (ItemServiceImpl) servletContext.getAttribute("itemServiceImpl");
            itemCollectionServiceImpl = (ItemCollectionServiceImpl) servletContext.getAttribute("itemCollectionServiceImpl");
            itemImageServiceImpl = (ItemImageServiceImpl) servletContext.getAttribute("itemImageServiceImpl");
            itemMaterialServiceImpl = (ItemMaterialServiceImpl) servletContext.getAttribute("itemMaterialServiceImpl");
            orderServiceImpl = (OrderServiceImpl) servletContext.getAttribute("orderServiceImpl");
            orderDetailServiceImpl = (OrderDetailServiceImpl) servletContext.getAttribute("orderDetailServiceImpl");
            itemTypeServiceImpl = (ItemTypeServiceImpl) servletContext.getAttribute("itemTypeServiceImpl");
            permissionServiceImpl = (PermissionServiceImpl) servletContext.getAttribute("permissionServiceImpl");
            saleServiceImpl = (SaleServiceImpl) servletContext.getAttribute("saleServiceImpl");
            stockItemServiceImpl = (StockItemServiceImpl) servletContext.getAttribute("stockItemServiceImpl");

            String requestedWith = req.getHeader("X-Requested-With");
            if (requestedWith != null && requestedWith.equals("XMLHttpRequest")){

                resp.setContentType("application/json");
                PrintWriter out = resp.getWriter();
                JSONObject jsonResponse=new JSONObject();

                String name=req.getParameter("signInName");
                String password=req.getParameter("signInPassword");

                System.out.println("Name:"+name+",password:"+password);

                Account account=accountServiceImpl.findByNameAndPassword(name,password);
                if(account!=null){
                    System.out.println("Found Account");
                    jsonResponse.put("success",1);
                    jsonResponse.put("accountID",account.getId());
                }else{
                    jsonResponse.put("success",0);
                }
                out.print(jsonResponse.toString());
                out.flush();
                out.close();
            }else{
                String action=req.getParameter("action");
                switch (action){
                    case "homeClick"->{
                        resp.sendRedirect("/FinalEE/ItemServlet");
                    }
                    case "itemCollectionClick"->{
                        int itemCollectionID=Integer.parseInt(req.getParameter("itemCollectionID"));
                        resp.sendRedirect("/FinalEE/ItemSearchServlet?itemCollectionID=" + itemCollectionID+"&currentPage="+1);
                    }
                    case "itemTypeClick"->{
                        int itemTypeID=Integer.parseInt(req.getParameter("itemTypeID"));
                        resp.sendRedirect("/FinalEE/ItemSearchServlet?itemTypeID=" + itemTypeID);
                    }
                    case "cartClick"->{
                        resp.sendRedirect("/FinalEE/CartServlet");
                    }
                    case "btnSearchClick"->{
                        String searchInput=req.getParameter("searchInput");
                        resp.sendRedirect("/FinalEE/ItemSearchServlet?searchInput=" + searchInput);
                    }
                    default -> {

                    }
                }
            }
        }catch (Exception er){
            System.out.println(er.toString());
        }



    }
}
