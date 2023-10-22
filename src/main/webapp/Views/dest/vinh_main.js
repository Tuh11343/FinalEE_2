function showtable() {

    const btntabs = document.querySelectorAll('.accordion-button'),
            tables = document.querySelectorAll('.table-data');
    function removeActive() {
        tables.forEach(function (item) {
            item.classList.remove('active');
        });
    }

    btntabs.forEach(function (item) {
        item.addEventListener('click', function (e) {
            e.preventDefault();
            removeActive();
            item.classList.add('active');
            let typebtn = item.getAttribute("data-type");
            tables.forEach(function (item) {
                let typeItem = item.getAttribute("data-type");
                if (typeItem === typebtn) {
                    item.classList.add('active');
                }
            });
        });
    });


}
showtable();


function handleDelete(e) {
    let confirmationResult = false;
    const deltabs = document.querySelectorAll('.btnDel');

    confirmationResult = confirm('Bạn có chắc chắn muốn xóa?');
    document.getElementById("testBtn").value = confirmationResult;
    console.log(confirmationResult);
    console.log(1);
    if (!confirmationResult) {
        event.preventDefault(); // Ngăn chặn sự kiện submit nếu không đồng ý xóa
    }
}

/*Account*/
function handleAddAccount() {
    var addUser = document.getElementById("account_addTrigger");
    var modal = document.getElementById("modal-add-account");
    var btnClose = document.querySelector(".clsadduser");
    addUser.addEventListener('click', function () {
        modal.classList.add("active");

    });
    btnClose.addEventListener('click', function () {
        modal.classList.remove("active");

    });


}
handleAddAccount();
function handleUpdateAccount() {
    var updateUser = document.querySelectorAll(".btnUpdateUser");
    var modal = document.getElementById("update-user");

    updateUser.forEach(function (item) {
        item.addEventListener("click", () => {
            modal.style.display = "block";

            var accountCustomerID = item.getAttribute("data-customerID");
            var accountPermissionID = item.getAttribute("data-permissionID");
            var accountName = item.getAttribute("data-accountName");
            var accountPass = item.getAttribute("data-accountPassword");

            console.log(accountCustomerID, accountPermissionID, accountName, accountPass);

            document.getElementById("update_accountNameID").value = accountName;
            document.getElementById("update_accountPasswordID").value = accountPass;

            var update_customer_selectElement = document.getElementById("update_label_customerID");
            for (var i = 0; i < update_customer_selectElement.options.length; i++) {
                var option = update_customer_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === accountCustomerID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }

            var update_permission_selectElement = document.getElementById("update_label_permissionID");
            for (var i = 0; i < update_permission_selectElement.options.length; i++) {
                var option = update_permission_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === accountPermissionID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }
        });

    });
    function closeModal() {
        modal.style.display = "none";
    }
    var closeBtn = document.getElementsByClassName("close")[0];
    closeBtn.addEventListener("click", closeModal);


}
handleUpdateAccount();

/*Item*/
function handleAddItem() {
    var addProducts = document.getElementById("item_addTrigger");
    var modal = document.getElementById("add-products");
    var btnClose = document.querySelector(".clsaddproduct");
    addProducts.addEventListener('click', function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener('click', function () {
        modal.classList.remove("active");

    });
}
handleAddItem();
function handleUpdateItem() {

    var updateItem = document.querySelectorAll(".btnUpdateItem");
    var modal = document.getElementById("update-products");
    var btnClose = document.querySelector(".clsupdateproduct");
    console.log(btnClose);

    updateItem.forEach(function (item) {
        item.addEventListener("click", () => {
            modal.style.display = "block";

            var itemName = item.getAttribute("data-itemName");
            var itemTypeID = item.getAttribute("data-itemTypeID");
            var itemCollectionID = item.getAttribute("data-itemCollectionID");
            var itemMaterialID = item.getAttribute("data-itemMaterialID");
            var isNew = item.getAttribute("data-itemIsNew");
            var isHot = item.getAttribute("data-itemIsHot");
            var price = item.getAttribute("data-itemPrice");
            var yearProduce = item.getAttribute("data-itemYearProduce");

            document.getElementById("update_itemNameID").value = itemName;
            document.getElementById("update_itemIsNewID").checked = (isNew === "1");
            document.getElementById("update_itemIsHotID").checked = (isHot === "1");
            document.getElementById("update_itemPriceID").value = price;
            document.getElementById("update_itemYearProduceID").value = yearProduce;

//          Combobox ItemType
            var update_itemType_selectElement = document.getElementById("update_label_itemTypeID");
            for (var i = 0; i < update_itemType_selectElement.options.length; i++) {
                var option = update_itemType_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === itemTypeID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }

//          Combobox ItemCollection
            var update_itemCollection_selectElement = document.getElementById("update_label_itemCollectionID");
            for (var i = 0; i < update_itemCollection_selectElement.options.length; i++) {
                var option = update_itemCollection_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === itemCollectionID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }

//            Combobox ItemMaterial
            var update_itemMaterial_selectElement = document.getElementById("update_label_itemMaterialID");
            for (var i = 0; i < update_itemMaterial_selectElement.options.length; i++) {
                var option = update_itemMaterial_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === itemMaterialID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }
        });

    });
    btnClose.addEventListener('click', function () {
        modal.style.display = "none";
    });

}
handleUpdateItem();

/*Customer*/
function handleAddCustomer() {
    var addCustomer = document.getElementById("customer_addTrigger");
    var modal = document.getElementById("add-customer");
    var btnClose = document.querySelector(".clsAddCustomer");
    addCustomer.addEventListener('click', function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener('click', function () {
        modal.classList.remove("active");

    });
}
handleAddCustomer();
function handleUpdateCustomer() {

    var updateCustomer = document.querySelectorAll(".btnUpdateCustomer");
    var modal = document.getElementById("update-customer");
    var btnClose = document.querySelector(".clsUpdateCustomer");

    updateCustomer.forEach(function (item) {
        item.addEventListener("click", () => {
            modal.style.display = "block";

            var name = item.getAttribute("data-customerName");
            var phoneNumber = item.getAttribute("data-customerPhoneNumber");
            var email = item.getAttribute("data-customerEmail");
            var address = item.getAttribute("data-customerAddress");

            document.getElementById("update_customerName").value = name;
            document.getElementById("update_customerPhoneNumber").value = phoneNumber;
            document.getElementById("update_customerEmail").value = email;
            document.getElementById("update_customerAddress").value = address;

        });

    });
    btnClose.addEventListener('click', function () {
        modal.style.display = "none";
    });
}
handleUpdateCustomer();

/*Discount Card*/
function handleAddDiscountCard() {
    var addDiscountCard = document.getElementById("discountCard_addTrigger");
    var modal = document.getElementById("add-discountCard");
    var btnClose = document.querySelector(".clsAddDiscountCard");
    addDiscountCard.addEventListener('click', function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener('click', function () {
        modal.classList.remove("active");

    });
}
handleAddDiscountCard();
function handleUpdateDiscountCard() {

    var updateDiscountCard = document.querySelectorAll(".btnUpdateDiscountCard");
    var modal = document.getElementById("update-discountCard");
    var btnClose = document.querySelector(".clsUpdateDiscountCard");

    updateDiscountCard.forEach(function (item) {
        item.addEventListener("click", () => {
            modal.style.display = "block";

            var customerID = item.getAttribute("data-discountCardCustomerID");
            var name = item.getAttribute("data-discountCardName");
            var discountPercentage = item.getAttribute("data-discountCardPercentage");

            document.getElementById("update_discountCardName").value = name;
            document.getElementById("update_discountCardPercentage").value = discountPercentage;

            /*Combobox CustomerID*/
            var update_discountCard_selectElement = document.getElementById("update_discountCardCustomerID");
            for (var i = 0; i < update_discountCard_selectElement.options.length; i++) {
                var option = update_discountCard_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === customerID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }
        });

    });
    btnClose.addEventListener('click', function () {
        modal.style.display = "none";
    });

}
handleUpdateDiscountCard();

/*Item Collection*/
function handleAddItemCollection() {
    var addItemCollection = document.getElementById("itemCollection_addTrigger");
    var modal = document.getElementById("add-itemCollection");
    var btnClose = document.querySelector(".clsAddItemCollection");
    addItemCollection.addEventListener('click', function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener('click', function () {
        modal.classList.remove("active");

    });
}
handleAddItemCollection();
function handleUpdateItemCollection() {

    var updateItemCollection = document.querySelectorAll(".btnUpdateItemCollection");
    var modal = document.getElementById("update-itemCollection");
    var btnClose = document.querySelector(".clsUpdateItemCollection");

    updateItemCollection.forEach(function (item) {
        item.addEventListener("click", () => {
            modal.style.display = "block";

            var name = item.getAttribute("data-itemCollectionName");

            document.getElementById("update_itemCollectionName").value = name;

        });

    });
    btnClose.addEventListener('click', function () {
        modal.style.display = "none";
    });
}
handleUpdateItemCollection();

/*Item Image*/
function handleAddItemImage() {
    var addItemImage = document.getElementById("itemImage_addTrigger");
    var modal = document.getElementById("add-itemImage");
    var btnClose = document.querySelector(".clsAddItemImage");
    addItemImage.addEventListener('click', function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener('click', function () {
        modal.classList.remove("active");

    });
}
handleAddItemImage();
function handleUpdateItemImage() {

    var updateItemImage = document.querySelectorAll(".btnUpdateItemImage");
    var modal = document.getElementById("update-itemImage");
    var btnClose = document.querySelector(".clsUpdateItemImage");

    updateItemImage.forEach(function (item) {
        item.addEventListener("click", () => {
            modal.style.display = "block";

            var itemID = item.getAttribute("data-itemImageItemID");
            var imageURL = item.getAttribute("data-itemImageURL");

            document.getElementById("update_itemImageURL").value = imageURL;

            /*Combobox CustomerID*/
            var update_itemImage_selectElement = document.getElementById("update_itemImageItemID");
            for (var i = 0; i < update_itemImage_selectElement.options.length; i++) {
                var option = update_itemImage_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === itemID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }
        });

    });
    btnClose.addEventListener('click', function () {
        modal.style.display = "none";
    });

}
handleUpdateItemImage();

/*Item Material*/
function handleAddItemMaterial() {
    var addItemMaterial = document.getElementById("itemMaterial_addTrigger");
    var modal = document.getElementById("add-itemMaterial");
    var btnClose = document.querySelector(".clsAddItemMaterial");
    addItemMaterial.addEventListener('click', function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener('click', function () {
        modal.classList.remove("active");

    });
}
handleAddItemMaterial();
function handleUpdateItemMaterial() {

    var updateItemMaterial = document.querySelectorAll(".btnUpdateItemMaterial");
    var modal = document.getElementById("update-itemMaterial");
    var btnClose = document.querySelector(".clsUpdateItemMaterial");

    updateItemMaterial.forEach(function (item) {
        item.addEventListener("click", () => {
            modal.style.display = "block";

            var name = item.getAttribute("data-itemMaterialName");

            document.getElementById("update_itemMaterialName").value = name;

        });

    });
    btnClose.addEventListener('click', function () {
        modal.style.display = "none";
    });
}
handleUpdateItemMaterial();

/*Order*/
function handleAddOrder() {
    var addOrder = document.getElementById("order_addTrigger");
    var modal = document.getElementById("add-order");
    var btnClose = document.querySelector(".clsAddOrder");
    addOrder.addEventListener('click', function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener('click', function () {
        modal.classList.remove("active");

    });
}
handleAddOrder();
function handleUpdateOrder() {

    var updateOrder = document.querySelectorAll(".btnUpdateOrder");
    var modal = document.getElementById("update-order");
    var btnClose = document.querySelector(".clsUpdateOrder");

    updateOrder.forEach(function (item) {
        item.addEventListener("click", () => {
            modal.style.display = "block";

            var customerID = item.getAttribute("data-orderCustomerID");
            var discountCardID = item.getAttribute("data-orderDiscountCardID");
            var total = item.getAttribute("data-orderTotal");
            var datePurchase = item.getAttribute("data-orderDatePurchase");

            document.getElementById("update_orderTotal").value = total;
            document.getElementById("update_orderDatePurchase").value = datePurchase;

            /*Combobox CustomerID*/
            var update_order_selectElement = document.getElementById("update_orderCustomerID");
            for (var i = 0; i < update_order_selectElement.options.length; i++) {
                var option = update_order_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === customerID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }

            /*Combobox DiscountCardID*/
            var update_order_selectElement = document.getElementById("update_orderDiscountCardID");
            for (var i = 0; i < update_order_selectElement.options.length; i++) {
                var option = update_order_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === discountCardID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }

        });

    });
    btnClose.addEventListener('click', function () {
        modal.style.display = "none";
    });

}
handleUpdateOrder();

/*OrderDetail*/
function handleAddOrderDetail() {
    var addOrderDetail = document.getElementById("orderDetail_addTrigger");
    var modal = document.getElementById("add-orderDetail");
    var btnClose = document.querySelector(".clsAddOrderDetail");
    addOrderDetail.addEventListener('click', function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener('click', function () {
        modal.classList.remove("active");

    });
}
handleAddOrderDetail();
function handleUpdateOrderDetail() {

    var updateOrderDetail = document.querySelectorAll(".btnUpdateOrderDetail");
    var modal = document.getElementById("update-orderDetail");
    var btnClose = document.querySelector(".clsUpdateOrderDetail");

    updateOrderDetail.forEach(function (item) {
        item.addEventListener("click", () => {
            modal.style.display = "block";

            var orderID = item.getAttribute("data-orderDetailOrderID");
            var itemID = item.getAttribute("data-orderDetailItemID");
            var amount = item.getAttribute("data-orderDetailAmount");
            var itemColor = item.getAttribute("data-orderDetailItemColor");
            var itemSize = item.getAttribute("data-orderDetailItemSize");
            var total = item.getAttribute("data-orderDetailTotal");

            document.getElementById("update_orderDetailAmount").value = amount;
            document.getElementById("update_orderDetailItemColor").value = itemColor;
            document.getElementById("update_orderDetailItemSize").value = itemSize;
            document.getElementById("update_orderDetailTotal").value = total;

            /*Combobox CustomerID*/
            var update_orderDetail_selectElement = document.getElementById("update_orderDetailOrderID");
            for (var i = 0; i < update_orderDetail_selectElement.options.length; i++) {
                var option = update_orderDetail_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === orderID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }

            /*Combobox DiscountCardID*/
            var update_orderDetail_selectElement = document.getElementById("update_orderDetailItemID");
            for (var i = 0; i < update_orderDetail_selectElement.options.length; i++) {
                var option = update_orderDetail_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === itemID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }

        });

    });
    btnClose.addEventListener('click', function () {
        modal.style.display = "none";
    });

}
handleUpdateOrderDetail();

/*Item Type*/
function handleAddItemType() {
    var addItemType = document.getElementById("itemType_addTrigger");
    var modal = document.getElementById("add-itemType");
    var btnClose = document.querySelector(".clsAddItemType");
    addItemType.addEventListener('click', function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener('click', function () {
        modal.classList.remove("active");

    });
}
handleAddItemType();
function handleUpdateItemType() {

    var updateItemType = document.querySelectorAll(".btnUpdateItemType");
    var modal = document.getElementById("update-itemType");
    var btnClose = document.querySelector(".clsUpdateItemType");

    updateItemType.forEach(function (item) {
        item.addEventListener("click", () => {
            modal.style.display = "block";

            var name = item.getAttribute("data-itemTypeName");

            document.getElementById("update_itemTypeName").value = name;

        });

    });
    btnClose.addEventListener('click', function () {
        modal.style.display = "none";
    });
}
handleUpdateItemType();

/*Permission*/
function handleAddPermission() {
    var addPermission = document.getElementById("permission_addTrigger");
    var modal = document.getElementById("add-permission");
    var btnClose = document.querySelector(".clsAddPermission");
    addPermission.addEventListener('click', function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener('click', function () {
        modal.classList.remove("active");

    });
}
handleAddPermission();
function handleUpdatePermission() {

    var updatePermission = document.querySelectorAll(".btnUpdatePermission");
    var modal = document.getElementById("update-permission");
    var btnClose = document.querySelector(".clsUpdatePermission");

    updatePermission.forEach(function (item) {
        item.addEventListener("click", () => {
            modal.style.display = "block";

            var name = item.getAttribute("data-permissionName");
            var level = item.getAttribute("data-permissionLevel");

            document.getElementById("update_permissionName").value = name;
            document.getElementById("update_permissionLevel").value = level;

        });

    });
    btnClose.addEventListener('click', function () {
        modal.style.display = "none";
    });
}
handleUpdatePermission();

/*Sale*/
function handleAddSale() {
    var addSale = document.getElementById("sale_addTrigger");
    var modal = document.getElementById("add-sale");
    var btnClose = document.querySelector(".clsAddSale");
    addSale.addEventListener('click', function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener('click', function () {
        modal.classList.remove("active");

    });
}
handleAddSale();
function handleUpdateSale() {

    var updateSale = document.querySelectorAll(".btnUpdateSale");
    var modal = document.getElementById("update-sale");
    var btnClose = document.querySelector(".clsUpdateSale");

    updateSale.forEach(function (item) {
        item.addEventListener("click", () => {
            modal.style.display = "block";

            var itemID = item.getAttribute("data-saleItemID");
            var name = item.getAttribute("data-saleName");
            var onSale = item.getAttribute("data-saleOnSale");
            var salePercentage = item.getAttribute("data-salePercentage");

            document.getElementById("update_saleName").value = name;
            document.getElementById("update_saleOnSale").checked = (onSale === "1");
            document.getElementById("update_salePercentage").value = salePercentage;

            /*Combobox ItemID*/
            var update_sale_selectElement = document.getElementById("update_saleItemID");
            for (var i = 0; i < update_sale_selectElement.options.length; i++) {
                var option = update_sale_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === itemID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }

        });

    });
    btnClose.addEventListener('click', function () {
        modal.style.display = "none";
    });

}
handleUpdateSale();

/*StockItem*/
function handleAddStockItem() {
    var addStockItem = document.getElementById("stockItem_addTrigger");
    var modal = document.getElementById("add-stockItem");
    var btnClose = document.querySelector(".clsAddStockItem");
    addStockItem.addEventListener('click', function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener('click', function () {
        modal.classList.remove("active");

    });
}
handleAddStockItem();
function handleUpdateStockItem() {

    var updateStockItem = document.querySelectorAll(".btnUpdateStockItem");
    var modal = document.getElementById("update-stockItem");
    var btnClose = document.querySelector(".clsUpdateStockItem");

    updateStockItem.forEach(function (item) {
        item.addEventListener("click", () => {
            modal.style.display = "block";

            var itemID = item.getAttribute("data-stockItemItemID");
            var color = item.getAttribute("data-stockItemColor");
            var size = item.getAttribute("data-stockItemSize");
            var amount = item.getAttribute("data-stockItemAmount");

            document.getElementById("update_stockItemColor").value = color;
            document.getElementById("update_stockItemSize").value = size;
            document.getElementById("update_stockItemAmount").value = amount;

            /*Combobox ItemID*/
            var update_stockItem_selectElement = document.getElementById("update_stockItemItemID");
            for (var i = 0; i < update_stockItem_selectElement.options.length; i++) {
                var option = update_stockItem_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === itemID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }

        });

    });
    btnClose.addEventListener('click', function () {
        modal.style.display = "none";
    });

}
handleUpdateStockItem();

//
//const productData = [
//    {product: 'Áo thun xanh dương', sales: 100},
//    {product: 'Áo ba lỗ trắng', sales: 200},
//    {product: 'Áo khoác dù chống nước', sales: 150},
//];
//// Populate table
//const table = document.getElementById('statsTable');
//productData.forEach(data => {
//    const row = table.insertRow(-1);
//    const cell1 = row.insertCell(0);
//    const cell2 = row.insertCell(1);
//    cell1.textContent = data.product;
//    cell2.textContent = data.sales;
//});
//// Create chart
//const ctx = document.getElementById('productChart').getContext('2d');
//new Chart(ctx, {
//    type: 'bar',
//    data: {
//        labels: productData.map(data => data.product),
//        datasets: [{
//                label: 'Sales',
//                data: productData.map(data => data.sales),
//                backgroundColor: 'rgba(75, 192, 192, 0.2)',
//                borderColor: 'rgba(75, 192, 192, 1)',
//                borderWidth: 1
//            }]
//    },
//    options: {
//        scales: {
//            y: {
//                beginAtZero: true
//            }
//        }
//    }
//});

