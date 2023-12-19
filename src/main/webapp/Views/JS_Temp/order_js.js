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
            var orderStatus = item.getAttribute("data-orderStatus");
            var note = item.getAttribute("data-orderNote");
            var email = item.getAttribute("data-orderEmail");

            document.getElementById("update_orderID").value = id;
            document.getElementById("update_orderTotal").value = total;
            document.getElementById("update_orderDatePurchase").value = datePurchase;
            document.getElementById("update_orderAddress").value = address;
            document.getElementById("update_orderStatus").value = orderStatus;
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

            /*Combobox DiscountCardID*/
            var update_order_selectElement = document.getElementById(
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



function refreshOrder() {
    location.href = adminManagerContextPath + "/ManageOrderServlet";
}