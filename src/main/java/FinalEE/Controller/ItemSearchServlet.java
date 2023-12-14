package FinalEE.Controller;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        try {
            ServletContext servletContext = getServletContext();

            accountServiceImpl = (AccountServiceImpl) servletContext.getAttribute("accountServiceImpl");
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
            List<Order> orderList = orderServiceImpl.getAllOrder();
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
            if (req.getParameter("currentPage") != null) {
                currentPage = Integer.parseInt(req.getParameter("currentPage"));
            }

            /*Init Variable*/
            int totalPage;
            List<String> pageList = new ArrayList<>();
            List<Item> itemSearchList = null;
            String sortBy = req.getParameter("sort");
            Double rangeMin;
            Double rangeMax;

            if(req.getParameter("rangeMin")!=null&&req.getParameter("rangeMax")!=null){
                rangeMin=Double.parseDouble(req.getParameter("rangeMin"));
                rangeMax=Double.parseDouble(req.getParameter("rangeMax"));
                System.out.println("RangeMin:"+rangeMin+" RangeMax:"+rangeMax);
            }else{
                System.out.println("Test:"+req.getParameter("rangeMin"));
                rangeMin=itemServiceImpl.getItemMinPrice();
                rangeMax=itemServiceImpl.getItemMaxPrice();
            }


            if (req.getParameter("itemCollectionID") != null && !req.getParameter("itemCollectionID").isBlank()) {
                int itemCollectionID = Integer.parseInt(req.getParameter("itemCollectionID"));
                req.setAttribute("itemCollectionID", itemCollectionID);

                if (sortBy != null) {
                    itemSearchList = getItemsByItemCollectionID(itemCollectionID,rangeMin,rangeMax,sortBy, currentPage);
                }

                totalPage = itemServiceImpl.getTotalPagesByItemCollectionID(itemCollectionID, rangeMin, rangeMax);
                for (int i = 0; i < totalPage; i++) {
                    pageList.add(String.valueOf(i + 1));
                }

            } else if (req.getParameter("itemTypeID") != null && !req.getParameter("itemTypeID").isBlank()) {
                int itemTypeID = Integer.parseInt(req.getParameter("itemTypeID"));
                req.setAttribute("itemTypeID", itemTypeID);

                if (sortBy != null) {
                    itemSearchList = getItemsByItemTypeID(itemTypeID,rangeMin,rangeMax,sortBy, currentPage);
                }

                totalPage = itemServiceImpl.getTotalPagesByItemTypeID(itemTypeID, rangeMin, rangeMax);
                for (int i = 0; i < totalPage; i++) {
                    pageList.add(String.valueOf(i + 1));
                }

            } else if (req.getParameter("itemMaterialID") != null && !req.getParameter("itemMaterialID").isBlank()) {
                int itemMaterialID = Integer.parseInt(req.getParameter("itemMaterialID"));
                req.setAttribute("itemMaterialID", itemMaterialID);

                if (sortBy != null) {
                    itemSearchList = getItemsByItemMaterialID(itemMaterialID,rangeMin,rangeMax,sortBy, currentPage);
                }

                totalPage = itemServiceImpl.getTotalPagesByItemMaterialID(itemMaterialID, rangeMin, rangeMax);
                for (int i = 0; i < totalPage; i++) {
                    pageList.add(String.valueOf(i + 1));
                }

            } else if (req.getParameter("itemName") != null && !req.getParameter("itemName").isBlank()) {
                String itemName = req.getParameter("itemName");
                req.setAttribute("itemName", itemName);

                if (sortBy != null) {
                    itemSearchList = getItemsByItemName(itemName,rangeMin,rangeMax,sortBy, currentPage);
                    System.out.println(itemSearchList.size());
                }

                totalPage = itemServiceImpl.getTotalPagesByName(itemName, rangeMin, rangeMax);
                for (int i = 0; i < totalPage; i++) {
                    pageList.add(String.valueOf(i + 1));
                }

            } else if (req.getParameter("searchInput") != null) {
                String searchInput = req.getParameter("searchInput");
                req.setAttribute("searchInput", searchInput);

                if (sortBy != null) {
                    itemSearchList = getItemsByItemName(searchInput,rangeMin,rangeMax,sortBy, currentPage);
                }

                totalPage = itemServiceImpl.getTotalPagesByName(searchInput, rangeMin, rangeMax);
                for (int i = 0; i < totalPage; i++) {
                    pageList.add(String.valueOf(i + 1));
                }

            } else {
                if (sortBy != null) {
                    itemSearchList = getItems(rangeMin,rangeMax,sortBy, currentPage);
                }

                totalPage = itemServiceImpl.getTotalPages(rangeMin,rangeMax);
                for (int i = 0; i < totalPage; i++) {
                    pageList.add(String.valueOf(i + 1));
                }

            }

            double minPrice = itemServiceImpl.getItemMinPrice();
            double maxPrice = itemServiceImpl.getItemMaxPrice();

            req.setAttribute("minPrice", minPrice);
            req.setAttribute("maxPrice", maxPrice);
            req.setAttribute("sort", sortBy);
            req.setAttribute("itemSearchList", itemSearchList);
            req.setAttribute("pageList", pageList);
            req.setAttribute("currentPage", currentPage);

            req.getRequestDispatcher("/Views/User/ProductList.jsp").forward(req, resp);
        } catch (Exception er) {
            er.printStackTrace();
        }

    }

    private List<Item> getItemsByItemCollectionID(int itemCollectionID, double min, double max, String sortBy, int currentPage) {
        System.out.println("Gia tri sortBy:" + sortBy);
        List<Item> itemList = null;
        if (sortBy.equalsIgnoreCase("az")) {
            itemList = itemServiceImpl.findAllByItemCollectionIdAndPriceBetween(currentPage, itemCollectionID, min, max, "name", ItemServiceImpl.SortOrder.ASC);
        } else if (sortBy.equalsIgnoreCase("za")) {
            itemList = itemServiceImpl.findAllByItemCollectionIdAndPriceBetween(currentPage, itemCollectionID, min, max, "name", ItemServiceImpl.SortOrder.DESC);
        } else if (sortBy.equalsIgnoreCase("priceAsc")) {
            itemList = itemServiceImpl.findAllByItemCollectionIdAndPriceBetween(currentPage, itemCollectionID, min, max, "price", ItemServiceImpl.SortOrder.ASC);
        } else if (sortBy.equalsIgnoreCase("priceDes")) {
            itemList = itemServiceImpl.findAllByItemCollectionIdAndPriceBetween(currentPage, itemCollectionID, min, max, "price", ItemServiceImpl.SortOrder.DESC);
        }
        return itemList;
    }

    private List<Item> getItemsByItemTypeID(int itemTypeID,double min,double max,String sortBy, int currentPage) {
        System.out.println("Gia tri sortBy:" + sortBy);
        List<Item> itemList = null;
        if (sortBy.equalsIgnoreCase("az")) {
            itemList = itemServiceImpl.findAllByItemTypeIdAndPriceBetween(currentPage, itemTypeID, min,max,"name", ItemServiceImpl.SortOrder.ASC);
        } else if (sortBy.equalsIgnoreCase("za")) {
            itemList = itemServiceImpl.findAllByItemTypeIdAndPriceBetween(currentPage, itemTypeID,min,max, "name", ItemServiceImpl.SortOrder.DESC);
        } else if (sortBy.equalsIgnoreCase("priceAsc")) {
            itemList = itemServiceImpl.findAllByItemTypeIdAndPriceBetween(currentPage, itemTypeID,min,max, "price", ItemServiceImpl.SortOrder.ASC);
        } else if (sortBy.equalsIgnoreCase("priceDes")) {
            itemList = itemServiceImpl.findAllByItemTypeIdAndPriceBetween(currentPage, itemTypeID, min,max,"price", ItemServiceImpl.SortOrder.DESC);
        }
        return itemList;
    }

    private List<Item> getItemsByItemMaterialID(int itemMaterialID,double min,double max,String sortBy, int currentPage) {
        System.out.println("Gia tri sortBy:" + sortBy);
        List<Item> itemList = null;
        if (sortBy.equalsIgnoreCase("az")) {
            itemList = itemServiceImpl.findAllByItemMaterialIdAndPriceBetween(currentPage, itemMaterialID, min,max,"name", ItemServiceImpl.SortOrder.ASC);
        } else if (sortBy.equalsIgnoreCase("za")) {
            itemList = itemServiceImpl.findAllByItemMaterialIdAndPriceBetween(currentPage, itemMaterialID, min,max,"name", ItemServiceImpl.SortOrder.DESC);
        } else if (sortBy.equalsIgnoreCase("priceAsc")) {
            itemList = itemServiceImpl.findAllByItemMaterialIdAndPriceBetween(currentPage, itemMaterialID, min,max,"price", ItemServiceImpl.SortOrder.ASC);
        } else if (sortBy.equalsIgnoreCase("priceDes")) {
            itemList = itemServiceImpl.findAllByItemMaterialIdAndPriceBetween(currentPage, itemMaterialID, min,max,"price", ItemServiceImpl.SortOrder.DESC);
        }
        return itemList;
    }

    private List<Item> getItemsByItemName(String itemName,double min,double max,String sortBy, int currentPage) {
        System.out.println("Gia tri sortBy:" + sortBy);
        List<Item> itemList = null;
        if (sortBy.equalsIgnoreCase("az")) {
            itemList = itemServiceImpl.findAllByNameContainsAndPriceBetween(currentPage, itemName, min,max,"name", ItemServiceImpl.SortOrder.ASC);
        } else if (sortBy.equalsIgnoreCase("za")) {
            itemList = itemServiceImpl.findAllByNameContainsAndPriceBetween(currentPage, itemName, min,max,"name", ItemServiceImpl.SortOrder.DESC);
        } else if (sortBy.equalsIgnoreCase("priceAsc")) {
            itemList = itemServiceImpl.findAllByNameContainsAndPriceBetween(currentPage, itemName,min,max, "price", ItemServiceImpl.SortOrder.ASC);
        } else if (sortBy.equalsIgnoreCase("priceDes")) {
            itemList = itemServiceImpl.findAllByNameContainsAndPriceBetween(currentPage, itemName,min,max, "price", ItemServiceImpl.SortOrder.DESC);
        }
        return itemList;
    }


    private List<Item> getItems(double min,double max,String sortBy, int currentPage) {
        System.out.println("Gia tri sortBy:" + sortBy);
        List<Item> itemList = null;
        if (sortBy.equalsIgnoreCase("az")) {
            itemList = itemServiceImpl.findAllByPriceBetween(currentPage,min,max, "name", ItemServiceImpl.SortOrder.ASC);
        } else if (sortBy.equalsIgnoreCase("za")) {
            itemList = itemServiceImpl.findAllByPriceBetween(currentPage, min,max,"name", ItemServiceImpl.SortOrder.DESC);
        } else if (sortBy.equalsIgnoreCase("priceAsc")) {
            itemList = itemServiceImpl.findAllByPriceBetween(currentPage,min,max, "price", ItemServiceImpl.SortOrder.ASC);
        } else if (sortBy.equalsIgnoreCase("priceDes")) {
            itemList = itemServiceImpl.findAllByPriceBetween(currentPage,min,max, "price", ItemServiceImpl.SortOrder.DESC);
        }
        return itemList;
    }


}
