
package FinalEE.Service;

import FinalEE.Entity.*;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelService {

    public ByteArrayInputStream exportToExcel(List<Order> orderList);

    public ByteArrayInputStream exportAccountToExcel(List<Account> accountList);

    public ByteArrayInputStream exportCartToExcel(List<Cart> cartList);

    public ByteArrayInputStream exportCustomerToExcel(List<Customer> customerList);

    public ByteArrayInputStream exportDiscountCardToExcel(List<DiscountCard> discountCardList);

    public ByteArrayInputStream exportItemToExcel(List<Item> itemList);

    public ByteArrayInputStream exportItemCollectionToExcel(List<ItemCollection> itemCollectionList);

    public ByteArrayInputStream exportItemImageToExcel(List<ItemImage> itemImageList);

    public ByteArrayInputStream exportItemMaterialToExcel(List<ItemMaterial> itemMaterialList);

    public ByteArrayInputStream exportItemOrderToExcel(List<Order> orderList);

    public ByteArrayInputStream exportItemTypeToExcel(List<ItemType> itemTypeList);

    public ByteArrayInputStream exportOrderDetailToExcel(List<OrderDetail> orderDetailList);
    public ByteArrayInputStream exportOrderStatusToExcel(List<OrderStatus> orderStatusList);
    public ByteArrayInputStream exportPermissionToExcel(List<Permission> permissionList);
    public ByteArrayInputStream exportSaleToExcel(List<Sale> saleList);
    public ByteArrayInputStream exportStockItemToExcel(List<StockItem> stockItemList);

}
