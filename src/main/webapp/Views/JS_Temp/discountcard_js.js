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


/*Discount Card*/
function handleAddDiscountCard() {
    var addDiscountCard = document.getElementById("discountCard_addTrigger");
    var modal = document.getElementById("add-discountCard");
    var btnClose = document.querySelector(".clsAddDiscountCard");
    addDiscountCard.addEventListener("click", function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener("click", function () {
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

            var id = item.getAttribute("data-discountCardID");
            var customerID = item.getAttribute("data-discountCardCustomerID");
            var name = item.getAttribute("data-discountCardName");
            var discountPercentage = item.getAttribute("data-discountCardPercentage");

            document.getElementById("update_discountID").value = id;
            document.getElementById("update_discountCardName").value = name;
            document.getElementById("update_discountCardPercentage").value = discountPercentage;

            /*Combobox CustomerID*/
            var update_discountCard_selectElement = document.getElementById(
                "update_discountCardCustomerID"
            );
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
    btnClose.addEventListener("click", function () {
        modal.style.display = "none";
    });
}

handleUpdateDiscountCard();


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

function addDiscountCard() {

    let add_discountCardName = document.getElementById("add_discountCardName").value;
    let add_discountCardPercentage = document.getElementById("add_discountCardPercentage").value;
    let add_discountCardCustomerID = document.getElementById("add_discountCardCustomerID").value;

    if (add_discountCardName == null || add_discountCardName == "" || add_discountCardPercentage == null || add_discountCardPercentage == ""
        || add_discountCardCustomerID == null || add_discountCardCustomerID == "") {
        alert("Không thể để dữ liệu trống");
    } else {
        var num = parseFloat(update_discountCardPercentage);
        if(Number.isInteger(num)){
            $.ajax({
                type: "POST",
                url: adminManagerContextPath + "/ManageDiscountCardServlet",
                data: {
                    action: "addDiscountCard",
                    add_discountCardName: add_discountCardName,
                    add_discountCardDiscountPercentage: add_discountCardPercentage,
                    add_discountCardCustomerID: add_discountCardCustomerID,
                }, headers: {
                    "X-Requested-With": "XMLHttpRequest",
                },
                success: function (data) {

                    data = JSON.parse(data);
                    if (data.success === true) {

                        alert("Thêm thẻ khuyến mãi thành công");
                        location.href = adminManagerContextPath + "/ManageDiscountCardServlet";

                    } else {
                        alert("Có lỗi xảy trong hệ thống");
                    }
                },
                error: function (error) {
                    console.log("error :>> ", error);
                },
            });

        }else{
            alert("Dữ liệu phần trăm giảm không phải số nguyên");
            return;
        }
    }
}

function deleteDiscountCard(discountCardID) {
    // Trả về true nếu người dùng đã xác nhận, ngược lại trả về false
    if (confirm('Bạn có chắc chắn xóa không?')) {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageDiscountCardServlet",
            data: {
                action: "deleteDiscountCard",
                discountCardID: discountCardID,
            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.success === true) {
                    alert("Xóa thẻ khuyến mãi thành công");
                    location.href = adminManagerContextPath + "/ManageDiscountCardServlet";

                } else {
                    alert("Có lỗi xảy trong hệ thống");
                }
            },
            error: function (error) {
                console.log("error :>> ", error);
            },
        });
    } else {
        alert("a")
    }
}

function updateDiscountCard() {
    let update_discountCardID = document.getElementById("update_discountID").value;
    let update_discountCardName = document.getElementById("update_discountCardName").value;
    let update_discountCardPercentage = document.getElementById("update_discountCardPercentage").value;
    let update_discountCardCustomerID = document.getElementById("update_discountCardCustomerID").value;

    if (update_discountCardName == null || update_discountCardName == "" || update_discountCardPercentage == null || update_discountCardPercentage == ""
        || update_discountCardCustomerID == null || update_discountCardCustomerID == "") {
        alert("Không thể để dữ liệu trống");
    } else {
        var num = parseFloat(update_discountCardPercentage);
        if (Number.isInteger(num)) {
            $.ajax({
                type: "POST",
                url: adminManagerContextPath + "/ManageDiscountCardServlet",
                data: {
                    action: "updateDiscountCard",
                    update_discountCardID: update_discountCardID,
                    update_discountCardName: update_discountCardName,
                    update_discountCardDiscountPercentage: update_discountCardPercentage,
                    update_discountCardCustomerID: update_discountCardCustomerID,
                }, headers: {
                    "X-Requested-With": "XMLHttpRequest",
                },
                success: function (data) {

                    data = JSON.parse(data);
                    if (data.success === true) {

                        alert("Cập nhật thẻ khuyến mãi thành công");
                        refreshDiscountCard();

                    } else {
                        alert("Có lỗi xảy trong hệ thống");
                    }
                },
                error: function (error) {
                    console.log("error :>> ", error);
                },
            });
        }else{
            alert("Dữ liệu phần trăm giảm không phải số nguyên");
            return;
        }
    }
}

function searchAndSortDiscountCard() {
    let discountCardSearchType = document.getElementById("discountCardSearchType").value;
    let discountCardInputSearch = document.getElementById("discountCardInputSearch").value;
    let discountCardSortType = document.getElementById("discountCardSortType").value;

    if (discountCardSearchType == null || discountCardSearchType == "" || discountCardInputSearch == null || discountCardInputSearch == "" ||
        discountCardSortType == null || discountCardSortType == "") {
        alert("Không thể để dữ liệu trống");
        return false;
    }
    if (discountCardSearchType == "id" || discountCardSearchType == "customerID") {
        var num = parseFloat(permissionInputSearch);
        if (Number.isInteger(num)) {
            return true;
        } else {
            alert("Dữ liệu điền vào không hợp lệ");
            return false;
        }
    }
    return true;
}

function refreshDiscountCard() {
    location.href = adminManagerContextPath + "/ManageDiscountCardServlet";
}