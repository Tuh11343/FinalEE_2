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


/*Item Material*/
function handleAddItemMaterial() {
    var addItemMaterial = document.getElementById("itemMaterial_addTrigger");
    var modal = document.getElementById("add-itemMaterial");
    var btnClose = document.querySelector(".clsAddItemMaterial");
    addItemMaterial.addEventListener("click", function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener("click", function () {
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

            var id = item.getAttribute("data-itemMaterialID");
            var name = item.getAttribute("data-itemMaterialName");

            document.getElementById("update_itemMaterialID").value = id;
            document.getElementById("update_itemMaterialName").value = name;
        });
    });
    btnClose.addEventListener("click", function () {
        modal.style.display = "none";
    });
}

handleUpdateItemMaterial();


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


function addItemMaterial() {

    let add_itemMaterialName = document.getElementById("add_itemMaterialName").value;

    if (add_itemMaterialName == null || add_itemMaterialName == "") {
        alert("Không thể để dữ liệu trống");
    } else {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageItemMaterialServlet",
            data: {
                action: "addItemMaterial",
                add_itemMaterialName: add_itemMaterialName,
            }, headers: {
                "X-Requested-With": "XMLHttpRequest",
            },
            success: function (data) {

                data = JSON.parse(data);
                if (data.success === true) {

                    alert("Thêm vật liệu sản phẩm thành công");
                    location.href = adminManagerContextPath + "/ManageItemMaterialServlet";

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

function deleteItemMaterial(itemMaterialID) {
    // Trả về true nếu người dùng đã xác nhận, ngược lại trả về false
    if (confirm('Bạn có chắc chắn xóa không?')) {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageItemMaterialServlet",
            data: {
                action: "deleteItemMaterial",
                itemMaterialID: itemMaterialID,
            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.success === true) {
                    alert("Xóa vật liệu sản phẩm thành công");
                    location.href = adminManagerContextPath + "/ManageItemMaterialServlet";

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

function updateItemMaterial() {
    let update_itemMaterialID = document.getElementById("update_itemMaterialID").value;
    let update_itemMaterialName = document.getElementById("update_itemMaterialName").value;

    if (update_itemMaterialName == null || update_itemMaterialName == "") {
        alert("Không thể để dữ liệu trống");
    } else {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageItemMaterialServlet",
            data: {
                action: "updateItemMaterial",
                update_itemMaterialID: update_itemMaterialID,
                update_itemMaterialName: update_itemMaterialName,
            }, headers: {
                "X-Requested-With": "XMLHttpRequest",
            },
            success: function (data) {

                data = JSON.parse(data);
                if (data.success === true) {

                    alert("Cập nhật vật liệu sản phẩm thành công");
                    refreshItemMaterial();

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

function searchAndSortItemMaterial() {
    let itemMaterialSearchType = document.getElementById("itemMaterialSearchType").value;
    let itemMaterialInputSearch = document.getElementById("itemMaterialInputSearch").value;
    let itemMaterialSortType = document.getElementById("itemMaterialSortType").value;

    if (itemMaterialSearchType == null || itemMaterialSearchType == "" || itemMaterialInputSearch == null || itemMaterialInputSearch == "" ||
        itemMaterialSortType == null || itemMaterialSortType == "") {
        alert("Không thể để dữ liệu trống");
        return false;
    }
    if (itemMaterialSearchType == "id") {
        var num = parseFloat(itemMaterialInputSearch);
        if (Number.isInteger(num)) {
            return true;
        } else {
            alert("Dữ liệu điền vào không hợp lệ");
            return false;
        }
    }
    return true;

}

function refreshItemMaterial() {
    location.href = adminManagerContextPath + "/ManageItemMaterialServlet";
}

function itemMaterialToExcel() {
    let table = document.getElementById("tableItemMaterial");

    let dataToSend = [];

    // Lặp qua các dòng của bảng và thu thập thông tin từ mỗi ô cột
    for (let i = 0; i < table.rows.length; i++) {
        let currentRow = table.rows[i];

        // Thu thập thông tin từ các ô cột trong dòng
        let id = currentRow.cells[0].textContent;
        let name = currentRow.cells[1].textContent;

        // Tạo đối tượng chứa thông tin từ dòng hiện tại
        let materialData = {
            id: id,
            name: name,
        };

        // Thêm đối tượng vào mảng dataToSend
        dataToSend.push(materialData);
    }

    const workbook = XLSX.utils.book_new();
    const sheet = XLSX.utils.json_to_sheet(dataToSend);
    XLSX.utils.book_append_sheet(workbook, sheet, "Nguyên liệu");

    XLSX.writeFile(workbook, "ItemMaterial.xlsx");
}