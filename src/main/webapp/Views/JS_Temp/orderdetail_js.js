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

function addOrderDetail() {

    let add_orderDetailOrderID = document.getElementById("add_orderDetailOrderID").value;
    let add_orderDetailStockItemID = document.getElementById("add_orderDetailStockItemID").value;
    let add_orderDetailAmount = document.getElementById("add_orderDetailAmount").value;

    if (add_orderDetailAmount == null || add_orderDetailAmount == "") {
        alert("Không thể để dữ liệu trống");
    } else {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageOrderDetailServlet",
            data: {
                action: "addOrderDetail",
                add_orderDetailOrderID: add_orderDetailOrderID,
                add_orderDetailStockItemID: add_orderDetailStockItemID,
                add_orderDetailAmount: add_orderDetailAmount,
            }, headers: {
                "X-Requested-With": "XMLHttpRequest",
            },
            success: function (data) {

                data = JSON.parse(data);
                if (data.outOfStock === true) {
                    alert("Số lượng hàng đặt vượt quá số lượng tồn kho vui lòng chọn lại");
                } else if (data.success === true) {

                    alert("Thêm chi tiết đơn hàng thành công");
                    location.href = adminManagerContextPath + "/ManageOrderDetailServlet";

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

function deleteOrderDetail(orderDetailID) {
    // Trả về true nếu người dùng đã xác nhận, ngược lại trả về false
    if (confirm('Bạn có chắc chắn xóa không?')) {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageOrderDetailServlet",
            data: {
                action: "deleteOrderDetail",
                orderDetailID: orderDetailID,
            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.outOfStock === true) {
                    alert("Số lượng hàng đặt vượt quá số lượng tồn kho vui lòng chọn lại");
                } else if (data.success === true) {
                    alert("Xóa chi tiết hóa đơn thành công");
                    location.href = adminManagerContextPath + "/ManageOrderDetailServlet";

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

function updateOrderDetail() {
    let update_orderDetailID = document.getElementById("update_orderDetailID").value;
    let update_orderDetailOrderID = document.getElementById("update_orderDetailOrderID").value;
    let update_orderDetailStockItemID = document.getElementById("update_orderDetailStockItemID").value;
    let update_orderDetailAmount = document.getElementById("update_orderDetailAmount").value;
    let update_orderDetailTotal = document.getElementById("update_orderDetailTotal").value;

    if (update_orderDetailAmount == null || update_orderDetailAmount == "") {
        alert("Không thể để dữ liệu trống");
    } else {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageOrderDetailServlet",
            data: {
                action: "updateOrderDetail",
                update_orderDetailAmount: update_orderDetailAmount,
                update_orderDetailID: update_orderDetailID,
                update_orderDetailOrderID: update_orderDetailOrderID,
                update_orderDetailStockItemID: update_orderDetailStockItemID,
                update_orderDetailTotal: update_orderDetailTotal,
            }, headers: {
                "X-Requested-With": "XMLHttpRequest",
            },
            success: function (data) {

                data = JSON.parse(data);
                if (data.outOfStock === true) {
                    alert("Số lượng hàng đặt vượt quá số lượng tồn kho vui lòng chọn lại");
                } else if (data.success === true) {

                    alert("Cập nhật chi tiết hóa đơn thành công");
                    refreshOrderDetail();

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

function searchAndSortOrderDetail() {
    let orderDetailSearchType = document.getElementById("orderDetailSearchType").value;
    let orderDetailInputSearch = document.getElementById("orderDetailInputSearch").value;
    let orderDetailSortType = document.getElementById("orderDetailSortType").value;

    if (orderDetailSearchType == null || orderDetailSearchType == "" || orderDetailInputSearch == null || orderDetailInputSearch == "" ||
        orderDetailSortType == null || orderDetailSortType == "") {
        alert("Không thể để dữ liệu trống");
        return false;
    }
    if (orderDetailSortType == "id") {
        var num = parseFloat(orderDetailInputSearch);
        if (Number.isInteger(num)) {
            return true;
        } else {
            alert("Dữ liệu điền vào không hợp lệ");
            return false;
        }
    }
    return true;

}

function refreshOrderDetail() {
    location.href = adminManagerContextPath + "/ManageOrderDetailServlet";
}

