/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package FinalEE.Controller;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
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
    private ItemImageServiceImpl itemImageServiceImpl;
    private SaleServiceImpl saleServiceImpl;


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
        itemImageServiceImpl = webApplicationContext.getBean(ItemImageServiceImpl.class);
        saleServiceImpl = webApplicationContext.getBean(SaleServiceImpl.class);

        List<ItemType> itemTypeList=itemTypeServiceImpl.getAllItemType();
        List<ItemCollection> itemCollectionList=itemCollectionServiceImpl.getAllItemCollection();
        List<Item> itemList=itemServiceImpl.getAllItem();
        List<ItemImage> itemImageList = itemImageServiceImpl.getAllItemImage();
        List<Sale> saleList = saleServiceImpl.getAllSale();


        HttpSession session=request.getSession();
        session.setAttribute("itemTypeList", itemTypeList);
        session.setAttribute("itemCollectionList", itemCollectionList);
        session.setAttribute("itemList",itemList);
        session.setAttribute("imageList",itemImageList);
        session.setAttribute("saleList", saleList);


        request.getRequestDispatcher("Views/User/Test_Home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action=request.getParameter("action");
        switch(action){
            case "itemCollectionClick" -> {
                System.out.println("Ban da click itemCollection"+request.getParameter("itemType"));
                request.getRequestDispatcher("Views/User/Test_Home.jsp").forward(request, response);

            }
            case "itemTypeClick"->{
                System.out.println("Ban da click itemType"+request.getParameter("itemType"));
                request.getRequestDispatcher("Views/User/Test_Home.jsp").forward(request, response);
            }
            case "btnSearchClick" -> {
                System.out.println("Ban da search:"+request.getParameter("btnSearch_data"));
                request.getRequestDispatcher("Views/User/Test_Home.jsp").forward(request, response);

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
