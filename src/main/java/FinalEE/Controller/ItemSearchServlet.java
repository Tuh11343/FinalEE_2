package FinalEE.Controller;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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

        int currentPage = 1;
        if(req.getParameter("currentPage")!=null){
            currentPage=Integer.parseInt(req.getParameter("currentPage"));
        }
        if(req.getParameter("itemCollectionID")!=null&&!req.getParameter("itemCollectionID").isBlank()){
            int itemCollectionID=Integer.parseInt(req.getParameter("itemCollectionID"));
            req.setAttribute("itemCollectionID",itemCollectionID);
        }else if(req.getParameter("itemTypeID")!=null){
            int itemTypeID=Integer.parseInt(req.getParameter("itemTypeID"));
            req.setAttribute("itemTypeID",itemTypeID);
        }else if(req.getParameter("itemMaterialID")!=null){
            int itemMaterialID=Integer.parseInt(req.getParameter("itemMaterialID"));
            req.setAttribute("itemMaterialID",itemMaterialID);
        }else if(req.getParameter("itemName")!=null){
            String itemName=req.getParameter("itemName");
            req.setAttribute("itemName",itemName);
        }else if(req.getParameter("searchInput")!=null){
            String searchInput=req.getParameter("searchInput");
            req.setAttribute("searchInput",searchInput);
        }

        req.setAttribute("currentPage",currentPage);


        /*Init Variable*/
        int totalPage = 0;
        List<String> pageList = new ArrayList<String>();
        List<Item> itemSearchList = null;
        String value=req.getParameter("sort");
        int sortBy=-1;
        if(value!=null){
            switch (value){
                case "az"->{
                    sortBy=0;
                }
                case "za"->{
                    sortBy=1;
                }
                case "priceAsc"->{
                    sortBy=2;
                }
                case "priceDesc"->{
                    sortBy=3;
                }
            }
        }

        if (req.getParameter("itemCollectionID") != null) {
            int itemCollectionID = Integer.parseInt(req.getParameter("itemCollectionID"));
            req.setAttribute("itemCollectionID",itemCollectionID);

            itemSearchList = itemServiceImpl.getItemsByItemCollectionIDAndPageNumber(currentPage,itemCollectionID);
            if(sortBy!=-1&&itemSearchList!=null){
                itemSearchList = sortListBy(sortBy, itemSearchList);
            }
            else if(itemSearchList!=null)
            {
                itemSearchList = sortListAZ(itemSearchList);
            }

            totalPage = itemServiceImpl.getTotalPagesByItemCollectionID(itemCollectionID);
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        } else if (req.getParameter("itemTypeID") != null) {
            int itemTypeID = Integer.parseInt(req.getParameter("itemTypeID"));
            req.setAttribute("itemTypeID",itemTypeID);

            itemSearchList = itemServiceImpl.getItemsByItemTypeIDAndPageNumber(currentPage,itemTypeID);
            if(sortBy!=-1&&itemSearchList!=null){
                itemSearchList = sortListBy(sortBy, itemSearchList);
            }
            else if(itemSearchList!=null)
            {
                itemSearchList = sortListAZ(itemSearchList);
            }

            totalPage = itemServiceImpl.getTotalPagesByItemTypeID(itemTypeID);
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        }else if(req.getParameter("itemMaterialID")!=null){
            int itemMaterialID = Integer.parseInt(req.getParameter("itemMaterialID"));
            req.setAttribute("itemMaterialID",itemMaterialID);

            itemSearchList = itemServiceImpl.getItemsByItemMaterialIDAndPageNumber(currentPage,itemMaterialID);
            if(sortBy!=-1&&itemSearchList!=null){
                itemSearchList = sortListBy(sortBy, itemSearchList);
            }
            else if(itemSearchList!=null)
            {
                itemSearchList = sortListAZ(itemSearchList);
            }

            totalPage = itemServiceImpl.getTotalPagesByItemMaterialID(itemMaterialID);
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        }else if(req.getParameter("itemName")!=null){
            String itemName =req.getParameter("itemName");
            req.setAttribute("itemName",itemName);

            itemSearchList = itemServiceImpl.getItemsByNameAndPageNumber(currentPage,itemName);
            if(sortBy!=-1&&itemSearchList!=null){
                itemSearchList = sortListBy(sortBy, itemSearchList);
            }
            else if(itemSearchList!=null)
            {
                itemSearchList = sortListAZ(itemSearchList);
            }

            totalPage = itemServiceImpl.getTotalPagesByName(itemName);
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        }else if(req.getParameter("searchInput")!=null){
            String searchInput =req.getParameter("searchInput");
            req.setAttribute("searchInput",searchInput);

            itemSearchList = itemServiceImpl.getItemsByNameAndPageNumber(currentPage,searchInput);
            if(sortBy!=-1&&itemSearchList!=null){
                itemSearchList = sortListBy(sortBy, itemSearchList);
            }
            else if(itemSearchList!=null)
            {
                itemSearchList = sortListAZ(itemSearchList);
            }

            totalPage = itemServiceImpl.getTotalPagesByName(searchInput);
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        }else{
            itemSearchList = itemServiceImpl.getItemsByPageNumber(currentPage);
            if(sortBy!=-1&&itemSearchList!=null){
                itemSearchList = sortListBy(sortBy, itemSearchList);
            }
            else if(itemSearchList!=null)
            {
                itemSearchList = sortListAZ(itemSearchList);
            }

            totalPage = itemServiceImpl.getTotalPages();
            for (int i = 0; i < totalPage; i++) {
                pageList.add(String.valueOf(i + 1));
            }

        }
        
        req.setAttribute("itemSearchList", itemSearchList);
        req.setAttribute("pageList", pageList);


        req.getRequestDispatcher("/Views/User/ProductList.jsp").forward(req, resp);
    }

    private List<Item> sortListBy(int sortBy, List<Item> itemSearchList) {
        System.out.println("Gia tri sortBy:"+sortBy);
        if(sortBy ==0){
            itemSearchList =sortListAZ(itemSearchList);
        }else if(sortBy ==1){
            itemSearchList =sortListZA(itemSearchList);
        }else if(sortBy ==2){
            itemSearchList =sortListByPriceAscending(itemSearchList);
        }else if(sortBy ==3){
            itemSearchList =sortListByPriceDescending(itemSearchList);
        }
        return itemSearchList;
    }

    private List<Item> sortListAZ(List<Item> itemSearchList) {
        List<Item> modifiableItemList = new ArrayList<>(itemSearchList);
        modifiableItemList.sort(Comparator.comparing(Item::getName));
        itemSearchList = modifiableItemList;
        return itemSearchList;
    }

    private List<Item> sortListZA(List<Item> itemSearchList) {
        List<Item> modifiableItemList = new ArrayList<>(itemSearchList);
        modifiableItemList.sort(Comparator.comparing(Item::getName).reversed());
        itemSearchList = modifiableItemList;
        return itemSearchList;
    }

    private List<Item> sortListByPriceAscending(List<Item> itemSearchList) {
        List<Item> modifiableItemList = new ArrayList<>(itemSearchList);
        modifiableItemList.sort(Comparator.comparingDouble(Item::getPrice));
        itemSearchList = modifiableItemList;
        return itemSearchList;
    }

    private List<Item> sortListByPriceDescending(List<Item> itemSearchList) {
        List<Item> modifiableItemList = new ArrayList<>(itemSearchList);
        modifiableItemList.sort(Comparator.comparingDouble(Item::getPrice).reversed());
        itemSearchList = modifiableItemList;
        return itemSearchList;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        doGet(req,resp);

    }
}
