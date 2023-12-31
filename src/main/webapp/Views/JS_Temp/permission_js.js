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


/*Permission*/
function handleAddPermission() {
    var addPermission = document.getElementById("permission_addTrigger");
    var modal = document.getElementById("add-permission");
    var btnClose = document.querySelector(".clsAddPermission");
    addPermission.addEventListener("click", function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener("click", function () {
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

            var id = item.getAttribute("data-permissionID");
            var name = item.getAttribute("data-permissionName");
            var level = item.getAttribute("data-permissionLevel");

            document.getElementById("update_permissionID").value = id;
            document.getElementById("update_permissionName").value = name;
            document.getElementById("update_permissionLevel").value = level;
        });
    });
    btnClose.addEventListener("click", function () {
        modal.style.display = "none";
    });
}

handleUpdatePermission();


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

function addPermission() {

    let add_permissionName = document.getElementById("add_permissionName").value;
    let add_permissionLevel = document.getElementById("add_permissionLevel").value;

    if (add_permissionName == null || add_permissionName == "" || add_permissionLevel == null || add_permissionLevel == "") {
        alert("Không thể để dữ liệu trống");
    } else {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManagePermissionServlet",
            data: {
                action: "addPermission",
                add_permissionName: add_permissionName,
                add_permissionLevel: add_permissionLevel,
            }, headers: {
                "X-Requested-With": "XMLHttpRequest",
            },
            success: function (data) {

                data = JSON.parse(data);
                if (data.success === true) {

                    alert("Thêm quyền thành công");
                    location.href = adminManagerContextPath + "/ManagePermissionServlet";

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

function deletePermission(permissionID) {
    // Trả về true nếu người dùng đã xác nhận, ngược lại trả về false
    if (confirm('Bạn có chắc chắn xóa không?')) {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManagePermissionServlet",
            data: {
                action: "deletePermission",
                permissionID: permissionID,
            },
            success: function (data) {
                console.log(data);
                data = JSON.parse(data);
                if (data.success === true) {
                    alert("Xóa quyền thành công");
                    location.href = adminManagerContextPath + "/ManagePermissionServlet";

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

function updatePermission() {
    let update_permissionID = document.getElementById("update_permissionID").value;
    let update_permissionName = document.getElementById("update_permissionName").value;
    let update_permissionLevel = document.getElementById("update_permissionLevel").value;

    if (update_permissionName == null || update_permissionName == "" || update_permissionLevel == null || update_permissionLevel == "") {
        alert("Không thể để dữ liệu trống");
    } else {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManagePermissionServlet",
            data: {
                action: "updatePermission",
                update_permissionID: update_permissionID,
                update_permissionName: update_permissionName,
                update_permissionLevel: update_permissionLevel,
            }, headers: {
                "X-Requested-With": "XMLHttpRequest",
            },
            success: function (data) {

                data = JSON.parse(data);
                if (data.success === true) {

                    alert("Cập nhật quyền thành công");
                    refreshPermission();

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

function searchAndSortPermission() {
    let permissionSearchType = document.getElementById("permissionSearchType").value;
    let permissionInputSearch = document.getElementById("permissionInputSearch").value;
    let permissionSortType = document.getElementById("permissionSortType").value;

    if (permissionSearchType == null || permissionSearchType == "" || permissionInputSearch == null || permissionInputSearch == "" ||
        permissionSortType == null || permissionSortType == "") {
        alert("Không thể để dữ liệu trống");
        return false;
    }

    if (permissionSearchType == "id") {
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

function refreshPermission() {
    location.href = adminManagerContextPath + "/ManagePermissionServlet";
}

function permissionToExcel() {
    let table = document.getElementById("tablePermission");

    let dataToSend = [];

    for (let i = 0; i < table.rows.length; i++) {
        let currentRow = table.rows[i];

        let id = currentRow.cells[0].textContent;
        let name = currentRow.cells[1].textContent;
        let level = currentRow.cells[2].textContent;

        let permissionData = {
            id: id,
            name: name,
            level: level,
        };

        dataToSend.push(permissionData);
    }

    const workbook = XLSX.utils.book_new();
    const sheet = XLSX.utils.json_to_sheet(dataToSend);
    XLSX.utils.book_append_sheet(workbook, sheet, "Permissions");

    XLSX.writeFile(workbook, "permissions.xlsx");
}