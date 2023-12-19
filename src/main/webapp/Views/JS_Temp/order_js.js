let btnSearch = document.querySelectorAll(".btnsearchbox"),
    boxSearch = document.querySelectorAll(".inputsearch");

function removeActiveinputSearch() {
    btnSearch.forEach((item) => {
        item.innerHTML = "Tim kiếm";
    });
    boxSearch.forEach((item, index) => {
        item.classList.remove("active");
    });
}

function showtable() {
    const btntabs = document.querySelectorAll(".accordion-button"),
        tables = document.querySelectorAll(".table-data");

    function removeActive() {
        tables.forEach(function (item) {
            item.classList.remove("active");
        });
    }

    btntabs.forEach(function (item) {
        item.addEventListener("click", function (e) {
            e.preventDefault();
            removeActive();
            item.classList.add("active");
            let typebtn = item.getAttribute("data-type");
            tables.forEach(function (item) {
                let typeItem = item.getAttribute("data-type");
                if (typeItem === typebtn) {
                    item.classList.add("active");
                }
            });
        });
    });
}

showtable();


/*Order*/
function handleAddOrder() {
    var addOrder = document.getElementById("order_addTrigger");
    var modal = document.getElementById("add-order");
    var btnClose = document.querySelector(".clsAddOrder");
    addOrder.addEventListener("click", function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener("click", function () {
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

            var id = item.getAttribute("data-orderID");
            var customerID = item.getAttribute("data-orderCustomerID");
            var discountCardID = item.getAttribute("data-orderDiscountCardID");
            var total = item.getAttribute("data-orderTotal");
            var datePurchase = item.getAttribute("data-orderDatePurchase");
            var address = item.getAttribute("data-orderAddress");
            var orderStatusID = item.getAttribute("data-orderOrderStatusID");
            var note = item.getAttribute("data-orderNote");
            var email = item.getAttribute("data-orderEmail");

            document.getElementById("update_orderID").value = id;
            document.getElementById("update_orderTotal").value = total;
            var date = new Date(datePurchase);
            document.getElementById("update_orderDatePurchase").value = date.toISOString().split('T')[0];
            document.getElementById("update_orderAddress").value = address;
            document.getElementById("update_orderNote").value = note;
            document.getElementById("update_orderEmail").value = email;

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

            /*Combobox OrderStatusID*/
            update_order_selectElement = document.getElementById("update_orderOrderStatusID");
            for (var i = 0; i < update_order_selectElement.options.length; i++) {
                var option = update_order_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === orderStatusID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }

            /*Combobox DiscountCardID*/
            update_order_selectElement = document.getElementById(
                "update_orderDiscountCardID"
            );
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
    btnClose.addEventListener("click", function () {
        modal.style.display = "none";
    });
}

handleUpdateOrder();

function scrollPageHeader() {
    let header = document.querySelector(".header"),
        menuleft = document.querySelector(".left .accordion");
    let scrollY = window.scrollY;
    if (scrollY > header.clientHeight) {
        header.classList.add("scroll");
        menuleft.classList.add("scroll");
    } else {
        header.classList.remove("scroll");
        menuleft.classList.remove("scroll");
    }
}

function scrollHeaderTable() {
    let headertable = document.querySelector(".table-data.active .header-table"),
        header = document.querySelector(".header");
    let scrollY = window.scrollY;
    if (scrollY > header.clientHeight) {
        headertable.classList.add("scroll");
    } else {
        headertable.classList.remove("scroll");
    }
}

document.addEventListener("scroll", function () {
    scrollPageHeader();
    scrollHeaderTable();
});

function showSearchbox() {
    console.log("boxSearch :>> ", boxSearch);
    console.log(btnSearch);

    btnSearch.forEach((item) => {
        item.addEventListener("click", function () {
            var inputSearch = item.nextElementSibling;
            if (inputSearch.classList.contains("active")) {
                inputSearch.classList.remove("active");
                item.innerHTML = "Tìm kiếm ";
            } else {
                removeActiveinputSearch();

                inputSearch.classList.add("active");
                item.innerHTML = "Đóng";
            }
        });
    });
}

showSearchbox();

function orderToExcel() {
    let table = document.getElementById("tableOrder");

    let dataToSend = [];

    for (let i = 1; i < table.rows.length; i++) {
        let currentRow = table.rows[i];

        let id = currentRow.cells[0].textContent;
        let customerID = currentRow.cells[1].textContent;
        let discountCard = currentRow.cells[2].textContent;
        let totalCost = currentRow.cells[3].textContent;
        let datePurchase = currentRow.cells[4].textContent;
        let orderStatus = currentRow.cells[5].textContent;
        let address = currentRow.cells[6].textContent;
        let note = currentRow.cells[7].textContent;

        let permissionData = {
            id: id,
            customerID: customerID,
            discountCard: discountCard,
            totalCost: totalCost,
            datePurchase: datePurchase,
            orderStatus: orderStatus,
            address: address,
            note: note,
        };

        dataToSend.push(permissionData);
    }

    const workbook = XLSX.utils.book_new();
    const sheet = XLSX.utils.json_to_sheet(dataToSend);
    XLSX.utils.book_append_sheet(workbook, sheet, "Order");

    XLSX.writeFile(workbook, "Order.xlsx");
}

function addOrder() {

    let add_orderCustomerID=document.getElementById("add_orderCustomerID").value;
    let add_orderDiscountCardID=document.getElementById("add_orderDiscountCardID").value;
    let add_orderDatePurchase=document.getElementById("add_orderDatePurchase").value;
    let add_orderOrderStatusID=document.getElementById("add_orderOrderStatusID").value;
    let add_orderAddress=document.getElementById("add_orderAddress").value;
    let add_orderEmail=document.getElementById("add_orderEmail").value;
    let add_orderNote=document.getElementById("add_orderNote").value;

    if (add_orderDatePurchase==null || add_orderDatePurchase == "" || add_orderAddress == null || add_orderAddress == ""
    || add_orderEmail == null || add_orderEmail == "" || add_orderNote == null || add_orderNote == "") {
        alert("Không thể để dữ liệu trống");
    } else {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageOrderServlet",
            data: {
                action: "addOrder",
                add_orderCustomerID:add_orderCustomerID,
                add_orderNote:add_orderNote,
                add_orderAddress:add_orderAddress,
                add_orderEmail:add_orderEmail,
                add_orderDiscountCardID:add_orderDiscountCardID,
                add_orderDatePurchase:add_orderDatePurchase,
                add_orderOrderStatusID:add_orderOrderStatusID,
            }, headers: {
                "X-Requested-With": "XMLHttpRequest",
            },
            success: function (data) {

                data = JSON.parse(data);
                if (data.success === true) {

                    alert("Thêm hóa đơn thành công");
                    location.href = adminManagerContextPath + "/ManageOrderServlet";

                } else {
                    alert("Có lỗi xảy trong hệ thống");
                }
            },
            error: function (error) {
                console.log("error :>> ", error);
            },
        });
    }
}

function deleteOrder(orderID) {
    // Trả về true nếu người dùng đã xác nhận, ngược lại trả về false
    if (confirm('Bạn có chắc chắn xóa không?')) {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageOrderServlet",
            data: {
                action: "deleteOrder",
                orderID: orderID,
            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.success === true) {
                    alert("Xóa hóa đơn thành công");
                    location.href = adminManagerContextPath + "/ManageOrderServlet";

                } else {
                    alert("Có lỗi xảy trong hệ thống");
                }
            },
            error: function (error) {
                console.log("error :>> ", error);
            },
        });
    }
}

function updateOrder() {
    let update_orderID=document.getElementById("update_orderID").value;
    let update_orderCustomerID=document.getElementById("update_orderCustomerID").value;
    let update_orderDiscountCardID=document.getElementById("update_orderDiscountCardID").value;
    let update_orderDatePurchase=document.getElementById("update_orderDatePurchase").value;
    let update_orderOrderStatusID=document.getElementById("update_orderOrderStatusID").value;
    let update_orderAddress=document.getElementById("update_orderAddress").value;
    let update_orderEmail=document.getElementById("update_orderEmail").value;
    let update_orderNote=document.getElementById("update_orderNote").value;
    let update_orderTotal=document.getElementById("update_orderTotal").value;

    if (update_orderDatePurchase==null || update_orderDatePurchase == "" || update_orderAddress == null || update_orderAddress == ""
        || update_orderEmail == null || update_orderEmail == "" || update_orderNote == null || update_orderNote == "") {
        alert("Không thể để dữ liệu trống");
    }else {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageOrderServlet",
            data: {
                action: "updateOrder",
                update_orderID:update_orderID,
                update_orderCustomerID:update_orderCustomerID,
                update_orderNote:update_orderNote,
                update_orderAddress:update_orderAddress,
                update_orderEmail:update_orderEmail,
                update_orderDiscountCardID:update_orderDiscountCardID,
                update_orderDatePurchase:update_orderDatePurchase,
                update_orderOrderStatusID:update_orderOrderStatusID,
                update_orderTotal:update_orderTotal,
            }, headers: {
                "X-Requested-With": "XMLHttpRequest",
            },
            success: function (data) {

                data = JSON.parse(data);
                if (data.success === true) {

                    alert("Cập nhật chi tiết hóa đơn thành công");
                    refreshOrder();

                } else {
                    alert("Có lỗi xảy trong hệ thống");
                }
            },
            error: function (error) {
                console.log("error :>> ", error);
            },
        });
    }
}

function searchAndSortOrder() {
    let orderSearchType = document.getElementById("orderSearchType").value;
    let orderInputSearch = document.getElementById("orderInputSearch").value;
    let orderSortType = document.getElementById("orderSortType").value;

    if (orderSearchType == null || orderSearchType == "" || orderInputSearch == null || orderInputSearch == "" ||
        orderSortType == null || orderSortType == "") {
        alert("Không thể để dữ liệu trống");
        return false;
    }

    if (orderSearchType == "id" || orderSearchType == "customerID") {
        var num = parseFloat(orderInputSearch);
        if (Number.isInteger(num)) {
            return true;
        } else {
            alert("Dữ liệu điền vào không hợp lệ");
            return false;
        }
    }
    return true;

}

function refreshOrder() {
    location.href = adminManagerContextPath + "/ManageOrderServlet";
}