package FinalEE.Controller;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemSearchServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        HttpSession session = req.getSession();

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

        List<Account> accountList = accountServiceImpl.getAllAccount();
        List<Customer> customerList = customerServiceImpl.getAllCustomer();
        List<DiscountCard> discountCardList = discountCardServiceImpl.getAllDiscountCard();
        List<Item> itemList = itemServiceImpl.getAllItem();
        List<ItemCollection> itemCollectionList = itemCollectionServiceImpl.getAllItemCollection();
        List<ItemImage> imageList = itemImageServiceImpl.getAllItemImage();
        List<ItemMaterial> itemMaterialList = itemMaterialServiceImpl.getAllItemMaterial();
        List<ItemOrder> orderList = orderServiceImpl.getAllOrder();
        List<OrderDetail> orderDetailList = orderDetailServiceImpl.getAllOrderDetail();
        List<ItemType> itemTypeList = itemTypeServiceImpl.getAllItemType();
        List<Permission> permissionList = permissionServiceImpl.getAllPermission();
        List<Sale> saleList = saleServiceImpl.getAllSale();
        List<StockItem> stockItemList = stockItemServiceImpl.getAllStockItem();

        /*Set Data List*/
        req.setAttribute("accountList", accountList);
        req.setAttribute("customerList", customerList);
        req.setAttribute("discountCardList", discountCardList);
        req.setAttribute("itemList", itemList);
        req.setAttribute("itemCollectionList", itemCollectionList);
        req.setAttribute("imageList", imageList);
        req.setAttribute("itemMaterialList", itemMaterialList);
        req.setAttribute("orderList", orderList);
        req.setAttribute("orderDetailList", orderDetailList);
        req.setAttribute("itemTypeList", itemTypeList);
        req.setAttribute("permissionList", permissionList);
        req.setAttribute("saleList", saleList);
        req.setAttribute("stockItemList", stockItemList);

        /*Init Variable*/
        int totalPage = 0;
        List<String> pageList = new ArrayList<String>();
        int currentPage = 1;
        if(req.getParameter("currentPage")!=null){
            currentPage=Integer.parseInt(req.getParameter("currentPage"));
        }
        List<Item> itemSearchList = null;

        if (req.getParameter("itemCollectionID") != null) {
            int itemCollectionID = Integer.parseInt(req.getParameter("itemCollectionID"));
            req.setAttribute("itemCollectionID",itemCollectionID);

            itemSearchList = itemServiceImpl.getItemsByItemCollectionIDAndPageNumber(currentPage,itemCollectionID);

            totalPage = itemServiceImpl.getTotalPagesByItemCollectionID(itemCollectionID);
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        } else if (req.getParameter("itemTypeID") != null) {
            int itemTypeID = Integer.parseInt(req.getParameter("itemTypeID"));
            req.setAttribute("itemTypeID",itemTypeID);

            itemSearchList = itemServiceImpl.getItemsByItemTypeIDAndPageNumber(currentPage,itemTypeID);

            totalPage = itemServiceImpl.getTotalPagesByItemTypeID(itemTypeID);
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        }else if(req.getParameter("itemMaterialID")!=null){
            int itemMaterialID = Integer.parseInt(req.getParameter("itemMaterialID"));
            req.setAttribute("itemMaterialID",itemMaterialID);

            itemSearchList = itemServiceImpl.getItemsByItemMaterialIDAndPageNumber(currentPage,itemMaterialID);

            totalPage = itemServiceImpl.getTotalPagesByItemMaterialID(itemMaterialID);
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        }else if(req.getParameter("itemName")!=null){
            String itemName =req.getParameter("itemName");
            req.setAttribute("itemName",itemName);

            itemSearchList = itemServiceImpl.getItemsByNameAndPageNumber(currentPage,itemName);

            totalPage = itemServiceImpl.getTotalPagesByName(itemName);
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        }else{
            itemSearchList = itemServiceImpl.getItemsByPageNumber(currentPage);

            totalPage = itemServiceImpl.getTotalPages();
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        }
        
        req.setAttribute("itemSearchList", itemSearchList);
        req.setAttribute("pageList", pageList);


        req.getRequestDispatcher("/Views/User/ProductList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        if(req.getParameter("itemCollectionID")!=null){
            int itemCollectionID=Integer.parseInt(req.getParameter("itemCollectionID"));
            req.setAttribute("itemCollectionID",itemCollectionID);
            req.setAttribute("currentPage",currentPage);
            doGet(req,resp);
        }
        else{
            System.out.println("WTF");
        }



    }
}
