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


/*Item Type*/
function handleAddItemType() {
    var addItemType = document.getElementById("itemType_addTrigger");
    var modal = document.getElementById("add-itemType");
    var btnClose = document.querySelector(".clsAddItemType");
    addItemType.addEventListener("click", function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener("click", function () {
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

            var id = item.getAttribute("data-itemTypeID");
            var name = item.getAttribute("data-itemTypeName");

            document.getElementById("update_itemTypeID").value = id;
            document.getElementById("update_itemTypeName").value = name;
        });
    });
    btnClose.addEventListener("click", function () {
        modal.style.display = "none";
    });
}

handleUpdateItemType();


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

function addItemType() {

    let add_itemTypeName = document.getElementById("add_itemTypeName").value;

    if (add_itemTypeName == null || add_itemTypeName == "") {
        alert("Không thể để dữ liệu trống");
    } else {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageItemTypeServlet",
            data: {
                action: "addItemType",
                add_itemTypeName: add_itemTypeName,
            }, headers: {
                "X-Requested-With": "XMLHttpRequest",
            },
            success: function (data) {

                data = JSON.parse(data);
                if (data.success === true) {

                    alert("Thêm loại sản phẩm thành công");
                    location.href = adminManagerContextPath + "/ManageItemTypeServlet";

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

function deleteItemType(itemTypeID) {
    // Trả về true nếu người dùng đã xác nhận, ngược lại trả về false
    if (confirm('Bạn có chắc chắn xóa không?')) {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageItemTypeServlet",
            data: {
                action: "deleteItemType",
                itemTypeID: itemTypeID,
            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.success === true) {
                    alert("Xóa loại sản phẩm thành công");
                    location.href = adminManagerContextPath + "/ManageItemTypeServlet";

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

function updateItemType() {
    let update_itemTypeID = document.getElementById("update_itemTypeID").value;
    let update_itemTypeName = document.getElementById("update_itemTypeName").value;

    if (update_itemTypeName == null || update_itemTypeName == "") {
        alert("Không thể để dữ liệu trống");
    } else {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageItemTypeServlet",
            data: {
                action: "updateItemType",
                update_itemTypeID: update_itemTypeID,
                update_itemTypeName: update_itemTypeName,
            }, headers: {
                "X-Requested-With": "XMLHttpRequest",
            },
            success: function (data) {

                data = JSON.parse(data);
                if (data.success === true) {

                    alert("Cập nhật loại sản phẩm thành công");
                    refreshItemType();

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

function searchAndSortItemType() {
    let itemTypeSearchType = document.getElementById("itemTypeSearchType").value;
    let itemTypeInputSearch = document.getElementById("itemTypeInputSearch").value;
    let itemTypeSortType = document.getElementById("itemTypeSortType").value;

    if (itemTypeSearchType == null || itemTypeSearchType == "" || itemTypeInputSearch == null || itemTypeInputSearch == "" ||
        itemTypeSortType == null || itemTypeSortType == "") {
        alert("Không thể để dữ liệu trống");
        return false;
    }
    if (itemTypeSearchType == "id") {
        var num = parseFloat(itemTypeInputSearch);
        if (Number.isInteger(num)) {
            return true;
        } else {
            alert("Dữ liệu điền vào không hợp lệ");
            return false;
        }
    }
    return true;

}

function refreshItemType() {
    location.href = adminManagerContextPath + "/ManageItemTypeServlet";
}

function itemTypeToExcel() {
    let table = document.getElementById("tableItemType");

    let dataToSend = [];

    // Lặp qua các dòng của bảng và thu thập thông tin từ mỗi ô cột
    for (let i = 1; i < table.rows.length; i++) {
        let currentRow = table.rows[i];

        // Thu thập thông tin từ các ô cột trong dòng
        let id = currentRow.cells[0].textContent;
        let itemType = currentRow.cells[1].textContent;

        // Tạo đối tượng chứa thông tin từ dòng hiện tại
        let ItemTypeData = {
            id: id,
            name: itemType,
        };

        // Thêm đối tượng vào mảng dataToSend
        dataToSend.push(ItemTypeData);
    }

    const workbook = XLSX.utils.book_new();
    const sheet = XLSX.utils.json_to_sheet(dataToSend);
    XLSX.utils.book_append_sheet(workbook, sheet, "ItemType");

    XLSX.writeFile(workbook, "itemtype.xlsx");
}