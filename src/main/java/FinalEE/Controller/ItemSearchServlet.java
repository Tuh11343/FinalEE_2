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

        int currentPage=1;
        if(req.getParameter("currentPage")!=null){
            currentPage=Integer.parseInt(req.getParameter("currentPage"));
        }

        /*Init Variable*/
        int totalPage;
        List<String> pageList = new ArrayList<>();
        List<Item> itemSearchList = null;
        String sortBy=req.getParameter("sort");

        if (req.getParameter("itemCollectionID") != null && !req.getParameter("itemCollectionID").isBlank()) {
            int itemCollectionID = Integer.parseInt(req.getParameter("itemCollectionID"));
            req.setAttribute("itemCollectionID",itemCollectionID);

            if(sortBy!=null){
                itemSearchList=getItemsByItemCollectionID(sortBy,itemCollectionID,currentPage);
            }

            totalPage = itemServiceImpl.getTotalPagesByItemCollectionID(itemCollectionID);
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        } else if (req.getParameter("itemTypeID") != null && !req.getParameter("itemTypeID").isBlank()) {
            int itemTypeID = Integer.parseInt(req.getParameter("itemTypeID"));
            req.setAttribute("itemTypeID",itemTypeID);

            if(sortBy!=null){
                itemSearchList=getItemsByItemTypeID(sortBy,itemTypeID,currentPage);
            }

            totalPage = itemServiceImpl.getTotalPagesByItemTypeID(itemTypeID);
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        }else if(req.getParameter("itemMaterialID")!=null && !req.getParameter("itemMaterialID").isBlank()){
            int itemMaterialID = Integer.parseInt(req.getParameter("itemMaterialID"));
            req.setAttribute("itemMaterialID",itemMaterialID);

            if(sortBy!=null){
                itemSearchList=getItemsByItemMaterialID(sortBy,itemMaterialID,currentPage);
            }

            totalPage = itemServiceImpl.getTotalPagesByItemMaterialID(itemMaterialID);
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        }else if(req.getParameter("itemName")!=null && !req.getParameter("itemName").isBlank()){
            String itemName =req.getParameter("itemName");
            req.setAttribute("itemName",itemName);

            if(sortBy!=null){
                itemSearchList=getItemsByItemName(sortBy,itemName,currentPage);
            }

            totalPage = itemServiceImpl.getTotalPagesByName(itemName);
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        }else if(req.getParameter("searchInput")!=null){
            String searchInput =req.getParameter("searchInput");
            req.setAttribute("searchInput",searchInput);

            if(sortBy!=null){
                itemSearchList=getItemsByItemName(sortBy,searchInput,currentPage);
            }

            totalPage = itemServiceImpl.getTotalPagesByName(searchInput);
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        }else{
            if(sortBy!=null){
                itemSearchList=getItems(sortBy,currentPage);
            }

            totalPage = itemServiceImpl.getTotalPages();
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        }

        double minPrice=itemServiceImpl.getItemMinPrice();
        double maxPrice=itemServiceImpl.getItemMaxPrice();

        req.setAttribute("minPrice",minPrice);
        req.setAttribute("maxPrice",maxPrice);
        req.setAttribute("sort",sortBy);
        req.setAttribute("itemSearchList", itemSearchList);
        req.setAttribute("pageList", pageList);
        req.setAttribute("currentPage",currentPage);

        req.getRequestDispatcher("/Views/User/ProductList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private List<Item> getItemsByItemCollectionID(String sortBy, int itemCollectionID, int currentPage) {
        System.out.println("Gia tri sortBy:"+sortBy);
        List<Item> itemList=null;
        if(sortBy.equalsIgnoreCase("az")){
            itemList=itemServiceImpl.getItemsByItemCollectionIDAndPageNumber(currentPage,itemCollectionID,"name", ItemServiceImpl.SortOrder.ASC);
        }else if(sortBy.equalsIgnoreCase("za")){
            itemList=itemServiceImpl.getItemsByItemCollectionIDAndPageNumber(currentPage,itemCollectionID,"name", ItemServiceImpl.SortOrder.DESC);
        }else if(sortBy.equalsIgnoreCase("priceAsc")){
            itemList=itemServiceImpl.getItemsByItemCollectionIDAndPageNumber(currentPage,itemCollectionID,"price", ItemServiceImpl.SortOrder.ASC);
        }else if(sortBy.equalsIgnoreCase("priceDes")){
            itemList=itemServiceImpl.getItemsByItemCollectionIDAndPageNumber(currentPage,itemCollectionID,"price", ItemServiceImpl.SortOrder.DESC);
        }
        return itemList;
    }

    private List<Item> getItemsByItemTypeID(String sortBy,int itemTypeID,int currentPage) {
        System.out.println("Gia tri sortBy:"+sortBy);
        List<Item> itemList=null;
        if(sortBy.equalsIgnoreCase("az")){
            itemList=itemServiceImpl.getItemsByItemTypeIDAndPageNumber(currentPage,itemTypeID,"name", ItemServiceImpl.SortOrder.ASC);
        }else if(sortBy.equalsIgnoreCase("za")){
            itemList=itemServiceImpl.getItemsByItemTypeIDAndPageNumber(currentPage,itemTypeID,"name", ItemServiceImpl.SortOrder.DESC);
        }else if(sortBy.equalsIgnoreCase("priceAsc")){
            itemList=itemServiceImpl.getItemsByItemTypeIDAndPageNumber(currentPage,itemTypeID,"price", ItemServiceImpl.SortOrder.ASC);
        }else if(sortBy.equalsIgnoreCase("priceDes")){
            itemList=itemServiceImpl.getItemsByItemTypeIDAndPageNumber(currentPage,itemTypeID,"price", ItemServiceImpl.SortOrder.DESC);
        }
        return itemList;
    }

    private List<Item> getItemsByItemMaterialID(String sortBy,int itemMaterialID,int currentPage) {
        System.out.println("Gia tri sortBy:"+sortBy);
        List<Item> itemList=null;
        if(sortBy.equalsIgnoreCase("az")){
            itemList=itemServiceImpl.getItemsByItemMaterialIDAndPageNumber(currentPage,itemMaterialID,"name", ItemServiceImpl.SortOrder.ASC);
        }else if(sortBy.equalsIgnoreCase("za")){
            itemList=itemServiceImpl.getItemsByItemMaterialIDAndPageNumber(currentPage,itemMaterialID,"name", ItemServiceImpl.SortOrder.DESC);
        }else if(sortBy.equalsIgnoreCase("priceAsc")){
            itemList=itemServiceImpl.getItemsByItemMaterialIDAndPageNumber(currentPage,itemMaterialID,"price", ItemServiceImpl.SortOrder.ASC);
        }else if(sortBy.equalsIgnoreCase("priceDes")){
            itemList=itemServiceImpl.getItemsByItemMaterialIDAndPageNumber(currentPage,itemMaterialID,"price", ItemServiceImpl.SortOrder.DESC);
        }
        return itemList;
    }

    private List<Item> getItemsByItemName(String sortBy,String itemName,int currentPage) {
        System.out.println("Gia tri sortBy:"+sortBy);
        List<Item> itemList=null;
        if(sortBy.equalsIgnoreCase("az")){
            itemList=itemServiceImpl.getItemsByNameAndPageNumber(currentPage,itemName,"name", ItemServiceImpl.SortOrder.ASC);
        }else if(sortBy.equalsIgnoreCase("za")){
            itemList=itemServiceImpl.getItemsByNameAndPageNumber(currentPage,itemName,"name", ItemServiceImpl.SortOrder.DESC);
        }else if(sortBy.equalsIgnoreCase("priceAsc")){
            itemList=itemServiceImpl.getItemsByNameAndPageNumber(currentPage,itemName,"price", ItemServiceImpl.SortOrder.ASC);
        }else if(sortBy.equalsIgnoreCase("priceDes")){
            itemList=itemServiceImpl.getItemsByNameAndPageNumber(currentPage,itemName,"price", ItemServiceImpl.SortOrder.DESC);
        }
        return itemList;
    }


    private List<Item> getItems(String sortBy,int currentPage) {
        System.out.println("Gia tri sortBy:"+sortBy);
        List<Item> itemList=null;
        if(sortBy.equalsIgnoreCase("az")){
            itemList=itemServiceImpl.getItemsByPageNumber(currentPage,"name", ItemServiceImpl.SortOrder.ASC);
        }else if(sortBy.equalsIgnoreCase("za")){
            itemList=itemServiceImpl.getItemsByPageNumber(currentPage,"name", ItemServiceImpl.SortOrder.DESC);
        }else if(sortBy.equalsIgnoreCase("priceAsc")){
            itemList=itemServiceImpl.getItemsByPageNumber(currentPage,"price", ItemServiceImpl.SortOrder.ASC);
        }else if(sortBy.equalsIgnoreCase("priceDes")){
            itemList=itemServiceImpl.getItemsByPageNumber(currentPage,"price", ItemServiceImpl.SortOrder.DESC);
        }
        return itemList;
    }


}
