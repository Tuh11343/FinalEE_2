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
            document.getElementById("update_itemIsNewID").checked=(isNew==="1");
            document.getElementById("update_itemIsHotID").checked = (isHot==="1");
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
