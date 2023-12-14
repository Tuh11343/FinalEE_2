package FinalEE.ServiceImpl;

import FinalEE.Entity.*;
import FinalEE.Service.ExcelService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class ExcelServiceImpl implements ExcelService {


    @Override
    public ByteArrayInputStream exportToExcel(List<Order> orders) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("Orders");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Order ID");
            headerRow.createCell(1).setCellValue("Customer ID");
            headerRow.createCell(2).setCellValue("Total");
            headerRow.createCell(3).setCellValue("Date of Purchase");
            headerRow.createCell(4).setCellValue("Order Status");
            headerRow.createCell(5).setCellValue("Address");
            headerRow.createCell(6).setCellValue("Email");

            int rowIdx = 1;
            for (Order order : orders) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(order.getId());
                /*row.createCell(1).setCellValue(order.getCustomer().getId()); // Assuming Customer has an getId() method*/
                row.createCell(2).setCellValue(order.getTotal());
                row.createCell(3).setCellValue(order.getDate_purchase().toString()); // Convert Date to String
                row.createCell(4).setCellValue(order.getOrder_status().getName()); // Assuming OrderStatus has a getStatus() method
                row.createCell(5).setCellValue(order.getAddress());
                row.createCell(6).setCellValue(order.getEmail());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ByteArrayInputStream exportAccountToExcel(List<Account> accountList) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("Accounts");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Account ID");
            headerRow.createCell(1).setCellValue("Customer ID");
            headerRow.createCell(2).setCellValue("Permission ID");
            headerRow.createCell(3).setCellValue("Name");
            headerRow.createCell(4).setCellValue("Password");

            int rowIdx = 1;
            for (Account account : accountList) {
                Row row = sheet.createRow(rowIdx++);

                // Account ID
                if (account.getId() != null) {
                    row.createCell(0).setCellValue(account.getId());
                }

                // Customer ID
                if (account.getCustomer() != null && account.getCustomer().getId() != null) {
                    row.createCell(1).setCellValue(account.getCustomer().getId());
                }

                // Permission ID
                if (account.getPermission() != null && account.getPermission().getId() != null) {
                    row.createCell(2).setCellValue(account.getPermission().getId());
                }

                // Name
                if (account.getName() != null) {
                    row.createCell(3).setCellValue(account.getName());
                }

                // Password
                if (account.getPassword() != null) {
                    row.createCell(4).setCellValue(account.getPassword());
                }
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ByteArrayInputStream exportCartToExcel(List<Cart> cartList) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("Carts");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Cart ID");
            headerRow.createCell(1).setCellValue("Customer ID");
            headerRow.createCell(2).setCellValue("Stock Item ID");
            headerRow.createCell(3).setCellValue("Amount");

            int rowIdx = 1;
            for (Cart cart : cartList) {
                Row row = sheet.createRow(rowIdx++);

                // Cart ID
                if (cart.getId() != null) {
                    row.createCell(0).setCellValue(cart.getId());
                }

                // Customer ID
                if (cart.getCustomer() != null && cart.getCustomer().getId() != null) {
                    row.createCell(1).setCellValue(cart.getCustomer().getId());
                }

                // Stock Item ID
                if (cart.getStockItem() != null && cart.getStockItem().getId() != null) {
                    row.createCell(2).setCellValue(cart.getStockItem().getId());
                }

                // Amount
                row.createCell(3).setCellValue(cart.getAmount());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ByteArrayInputStream exportCustomerToExcel(List<Customer> customerList) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("Customers");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Customer ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Phone Number");
            headerRow.createCell(3).setCellValue("Email");
            headerRow.createCell(4).setCellValue("Address");

            int rowIdx = 1;
            for (Customer customer : customerList) {
                Row row = sheet.createRow(rowIdx++);

                // Customer ID
                if (customer.getId() != null) {
                    row.createCell(0).setCellValue(customer.getId());
                }

                // Name
                if (customer.getName() != null) {
                    row.createCell(1).setCellValue(customer.getName());
                }

                // Phone Number
                if (customer.getPhone_number() != null) {
                    row.createCell(2).setCellValue(customer.getPhone_number());
                }

                // Email
                if (customer.getEmail() != null) {
                    row.createCell(3).setCellValue(customer.getEmail());
                }

                // Address
                if (customer.getAddress() != null) {
                    row.createCell(4).setCellValue(customer.getAddress());
                }
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ByteArrayInputStream exportDiscountCardToExcel(List<DiscountCard> discountCardList) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("DiscountCards");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Discount Card ID");
            headerRow.createCell(1).setCellValue("Customer ID");
            headerRow.createCell(2).setCellValue("Name");
            headerRow.createCell(3).setCellValue("Discount Percentage");

            int rowIdx = 1;
            for (DiscountCard discountCard : discountCardList) {
                Row row = sheet.createRow(rowIdx++);

                // Discount Card ID
                row.createCell(0).setCellValue(discountCard.getId());

                // Customer ID
                if (discountCard.getCustomer() != null && discountCard.getCustomer().getId() != null) {
                    row.createCell(1).setCellValue(discountCard.getCustomer().getId());
                }

                // Name
                row.createCell(2).setCellValue(discountCard.getName());

                // Discount Percentage
                row.createCell(3).setCellValue(discountCard.getDiscount_percentage());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ByteArrayInputStream exportItemToExcel(List<Item> itemList) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("Items");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Item ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Item Type ID");
            headerRow.createCell(3).setCellValue("Item Collection ID");
            headerRow.createCell(4).setCellValue("Item Material ID");
            headerRow.createCell(5).setCellValue("Description");
            headerRow.createCell(6).setCellValue("Is New");
            headerRow.createCell(7).setCellValue("Is Hot");
            headerRow.createCell(8).setCellValue("Price");
            headerRow.createCell(9).setCellValue("Year Produce");
            headerRow.createCell(10).setCellValue("Sale ID");

            int rowIdx = 1;
            for (Item item : itemList) {
                Row row = sheet.createRow(rowIdx++);

                // Item ID
                row.createCell(0).setCellValue(item.getId());

                // Name
                row.createCell(1).setCellValue(item.getName());

                // Item Type ID
                if (item.getItemType() != null && item.getItemType().getId() != null) {
                    row.createCell(2).setCellValue(item.getItemType().getId());
                }

                // Item Collection ID
                if (item.getItemCollection() != null && item.getItemCollection().getId() != null) {
                    row.createCell(3).setCellValue(item.getItemCollection().getId());
                }

                // Item Material ID
                if (item.getItemMaterial() != null && item.getItemMaterial().getId() != null) {
                    row.createCell(4).setCellValue(item.getItemMaterial().getId());
                }

                // Description
                row.createCell(5).setCellValue(item.getDescription());

                // Is New
                row.createCell(6).setCellValue(item.getIs_new());

                // Is Hot
                row.createCell(7).setCellValue(item.getIs_hot());

                // Price
                row.createCell(8).setCellValue(item.getPrice());

                // Year Produce
                row.createCell(9).setCellValue(item.getYear_produce());

                // Sale ID
                if (item.getSale() != null && item.getSale().getId() != null) {
                    row.createCell(10).setCellValue(item.getSale().getId());
                }
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ByteArrayInputStream exportItemCollectionToExcel(List<ItemCollection> itemCollectionList) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("ItemCollections");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Item Collection ID");
            headerRow.createCell(1).setCellValue("Name");

            int rowIdx = 1;
            for (ItemCollection itemCollection : itemCollectionList) {
                Row row = sheet.createRow(rowIdx++);

                // Item Collection ID
                row.createCell(0).setCellValue(itemCollection.getId());

                // Name
                row.createCell(1).setCellValue(itemCollection.getName());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ByteArrayInputStream exportItemImageToExcel(List<ItemImage> itemImageList) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("ItemImages");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Item Image ID");
            headerRow.createCell(1).setCellValue("Item ID");
            headerRow.createCell(2).setCellValue("Image URL");

            int rowIdx = 1;
            for (ItemImage itemImage : itemImageList) {
                Row row = sheet.createRow(rowIdx++);

                // Item Image ID
                row.createCell(0).setCellValue(itemImage.getId());

                // Item ID
                if (itemImage.getItem() != null && itemImage.getItem().getId() != null) {
                    row.createCell(1).setCellValue(itemImage.getItem().getId());
                }

                // Image URL
                row.createCell(2).setCellValue(itemImage.getImage_url());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ByteArrayInputStream exportItemMaterialToExcel(List<ItemMaterial> itemMaterialList) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("ItemMaterials");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Item Material ID");
            headerRow.createCell(1).setCellValue("Name");

            int rowIdx = 1;
            for (ItemMaterial itemMaterial : itemMaterialList) {
                Row row = sheet.createRow(rowIdx++);

                // Item Material ID
                row.createCell(0).setCellValue(itemMaterial.getId());

                // Name
                row.createCell(1).setCellValue(itemMaterial.getName());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ByteArrayInputStream exportItemOrderToExcel(List<Order> orderList) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("ItemOrders");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Order ID");
            headerRow.createCell(1).setCellValue("Customer ID");
            headerRow.createCell(2).setCellValue("Discount Card ID");
            headerRow.createCell(3).setCellValue("Total");
            headerRow.createCell(4).setCellValue("Date of Purchase");
            headerRow.createCell(5).setCellValue("Order Status ID");
            headerRow.createCell(6).setCellValue("Address");
            headerRow.createCell(7).setCellValue("Note");
            headerRow.createCell(8).setCellValue("Email");

            int rowIdx = 1;
            for (Order order : orderList) {
                Row row = sheet.createRow(rowIdx++);

                // Order ID
                row.createCell(0).setCellValue(order.getId());

                // Customer ID
                if (order.getCustomer() != null && order.getCustomer().getId() != null) {
                    row.createCell(1).setCellValue(order.getCustomer().getId());
                }

                // Discount Card ID
                if (order.getDiscountCard() != null && order.getDiscountCard().getId() != null) {
                    row.createCell(2).setCellValue(order.getDiscountCard().getId());
                }

                // Total
                row.createCell(3).setCellValue(order.getTotal());

                // Date of Purchase
                if (order.getDate_purchase() != null) {
                    row.createCell(4).setCellValue(order.getDate_purchase().toString());
                }

                // Order Status ID
                if (order.getOrder_status() != null && order.getOrder_status().getId() != null) {
                    row.createCell(5).setCellValue(order.getOrder_status().getId());
                }

                // Address
                row.createCell(6).setCellValue(order.getAddress());

                // Note
                row.createCell(7).setCellValue(order.getNote());

                // Email
                row.createCell(8).setCellValue(order.getEmail());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ByteArrayInputStream exportItemTypeToExcel(List<ItemType> itemTypeList) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("ItemTypes");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Item Type ID");
            headerRow.createCell(1).setCellValue("Name");

            int rowIdx = 1;
            for (ItemType itemType : itemTypeList) {
                Row row = sheet.createRow(rowIdx++);

                // Item Type ID
                row.createCell(0).setCellValue(itemType.getId());

                // Name
                row.createCell(1).setCellValue(itemType.getName());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ByteArrayInputStream exportOrderDetailToExcel(List<OrderDetail> orderDetailList) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("OrderDetails");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Order Detail ID");
            headerRow.createCell(1).setCellValue("Order ID");
            headerRow.createCell(2).setCellValue("Item ID");
            headerRow.createCell(3).setCellValue("Amount");
            headerRow.createCell(4).setCellValue("Item Color");
            headerRow.createCell(5).setCellValue("Item Size");
            headerRow.createCell(6).setCellValue("Total");

            int rowIdx = 1;
            for (OrderDetail orderDetail : orderDetailList) {
                Row row = sheet.createRow(rowIdx++);

                // Order Detail ID
                row.createCell(0).setCellValue(orderDetail.getId());

                // Order ID
                if (orderDetail.getOrder() != null && orderDetail.getOrder().getId() != null) {
                    row.createCell(1).setCellValue(orderDetail.getOrder().getId());
                }

                // Item ID
                if (orderDetail.getItem() != null && orderDetail.getItem().getId() != null) {
                    row.createCell(2).setCellValue(orderDetail.getItem().getId());
                }

                // Amount
                row.createCell(3).setCellValue(orderDetail.getAmount());

                // Item Color
                row.createCell(4).setCellValue(orderDetail.getItem_color());

                // Item Size
                row.createCell(5).setCellValue(orderDetail.getItem_size());

                // Total
                row.createCell(6).setCellValue(orderDetail.getTotal());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ByteArrayInputStream exportOrderStatusToExcel(List<OrderStatus> orderStatusList) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("OrderStatuses");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Order Status ID");
            headerRow.createCell(1).setCellValue("Name");

            int rowIdx = 1;
            for (OrderStatus orderStatus : orderStatusList) {
                Row row = sheet.createRow(rowIdx++);

                // Order Status ID
                row.createCell(0).setCellValue(orderStatus.getId());

                // Name
                row.createCell(1).setCellValue(orderStatus.getName());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ByteArrayInputStream exportPermissionToExcel(List<Permission> permissionList) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("Permissions");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Permission ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Level");

            int rowIdx = 1;
            for (Permission permission : permissionList) {
                Row row = sheet.createRow(rowIdx++);

                // Permission ID
                row.createCell(0).setCellValue(permission.getId());

                // Name
                row.createCell(1).setCellValue(permission.getName());

                // Level
                row.createCell(2).setCellValue(permission.getLevel());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ByteArrayInputStream exportSaleToExcel(List<Sale> saleList) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("Sales");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Sale ID");
            headerRow.createCell(1).setCellValue("Item ID");
            headerRow.createCell(2).setCellValue("Name");
            headerRow.createCell(3).setCellValue("On Sale");
            headerRow.createCell(4).setCellValue("Sale Percentage");

            int rowIdx = 1;
            for (Sale sale : saleList) {
                Row row = sheet.createRow(rowIdx++);

                // Sale ID
                row.createCell(0).setCellValue(sale.getId());

                // Item ID
                if (sale.getItem() != null && sale.getItem().getId() != null) {
                    row.createCell(1).setCellValue(sale.getItem().getId());
                }

                // Name
                row.createCell(2).setCellValue(sale.getName());

                // On Sale
                row.createCell(3).setCellValue(sale.getOn_sale());

                // Sale Percentage
                row.createCell(4).setCellValue(sale.getSale_percentage());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ByteArrayInputStream exportStockItemToExcel(List<StockItem> stockItemList) {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            Sheet sheet = workbook.createSheet("StockItems");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Stock Item ID");
            headerRow.createCell(1).setCellValue("Item ID");
            headerRow.createCell(2).setCellValue("Color");
            headerRow.createCell(3).setCellValue("Size");
            headerRow.createCell(4).setCellValue("Amount");

            int rowIdx = 1;
            for (StockItem stockItem : stockItemList) {
                Row row = sheet.createRow(rowIdx++);

                // Stock Item ID
                row.createCell(0).setCellValue(stockItem.getId());

                // Item ID
                if (stockItem.getItem() != null && stockItem.getItem().getId() != null) {
                    row.createCell(1).setCellValue(stockItem.getItem().getId());
                }

                // Color
                row.createCell(2).setCellValue(stockItem.getColor());

                // Size
                row.createCell(3).setCellValue(stockItem.getSize());

                // Amount
                row.createCell(4).setCellValue(stockItem.getAmount());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
