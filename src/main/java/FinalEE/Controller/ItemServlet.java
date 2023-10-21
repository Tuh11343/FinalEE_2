/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package FinalEE.Controller;

import FinalEE.Entity.Item;
import FinalEE.Entity.ItemCollection;
import FinalEE.Entity.ItemType;
import FinalEE.ServiceImpl.ItemCollectionServiceImpl;
import FinalEE.ServiceImpl.ItemServiceImpl;
import FinalEE.ServiceImpl.ItemTypeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Controller
public class ItemServlet extends HttpServlet {

    private ItemServiceImpl itemServiceImpl;
    private ItemTypeServiceImpl itemTypeServiceImpl;
    private ItemCollectionServiceImpl itemCollectionServiceImpl;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ItemServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ItemServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        itemServiceImpl = webApplicationContext.getBean(ItemServiceImpl.class);
        itemTypeServiceImpl=webApplicationContext.getBean(ItemTypeServiceImpl.class);
        itemCollectionServiceImpl=webApplicationContext.getBean(ItemCollectionServiceImpl.class);

        List<ItemType> itemTypeList=itemTypeServiceImpl.getAllItemType();
        List<ItemCollection> itemCollectionList=itemCollectionServiceImpl.getAllItemCollection();
        HttpSession session=request.getSession();
        session.setAttribute("itemTypeList", itemTypeList);
        session.setAttribute("itemCollectionList", itemCollectionList);
        //request.setAttribute("itemTypeList", itemTypeList);
        
        request.getRequestDispatcher("Views/Home2.jsp").forward(request, response);
        //response.sendRedirect("Views/Home2.jsp");au
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        String requestUri = request.getRequestURI();
//        if (requestUri.equals("/AddItemServlet")) {
//            Item item=new Item();
//            item.setAmount(Integer.parseInt(request.getParameter("amount")));
//            item.set_hot(Boolean.parseBoolean(request.getParameter("isHot")));
//            item.setItem_type_id(Integer.parseInt(request.getParameter("itemTypeID")));
//            item.setName(request.getParameter("name"));
//            item.set_new(Boolean.parseBoolean(request.getParameter("isNew")));
//            item.setPrice(Double.parseDouble(request.getParameter("price")));
//            item.setYear_produce(Integer.parseInt(request.getParameter("yearProduce")));
//            System.out.println(item.toString());
//            itemServiceImpl.create(item);
//        } else {
//            List<Item> itemList = itemServiceImpl.getAllItem();
//            System.out.println("Số lượng sản phẩm:" + itemList.size());
//            HttpSession session=request.getSession();
//            session.setAttribute("itemList", itemList);
////            request.setAttribute("itemList", itemList);
//            request.getRequestDispatcher("Views/Home.jsp").forward(request, response);
//        }

        String action=request.getParameter("action");
        switch(action){
            case "itemCollectionClick" -> {
                System.out.println("Ban da click itemCollection"+request.getParameter("itemType"));
            }
            case "btnSearchClick" -> {
                System.out.println("Ban da search:"+request.getParameter("btnSearch_data"));
            }
            default -> {
                System.out.println(action);
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
