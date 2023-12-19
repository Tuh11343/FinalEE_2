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

function addAccount() {

    let add_accountName = document.getElementById("add_accountName").value;
    let add_accountPassword = document.getElementById("add_accountPassword").value;
    let add_accountCustomerID = document.getElementById("add_accountCustomerID").value;
    let add_accountPermissionID = document.getElementById("add_accountPermissionID").value;

    if (add_accountName == null || add_accountName == "" || add_accountPassword == null || add_accountPassword == ""
        || add_accountPermissionID == null || add_accountPermissionID == "" || add_accountCustomerID == null || add_accountCustomerID == "") {
        alert("Không thể để dữ liệu trống");
    } else {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageAccountServlet",
            data: {
                action: "addAccount",
                add_accountName: add_accountName,
                add_accountPassword: add_accountPassword,
                add_accountCustomerID: add_accountCustomerID,
                add_accountPermissionID: add_accountPermissionID,
            }, headers: {
                "X-Requested-With": "XMLHttpRequest",
            },
            success: function (data) {

                data = JSON.parse(data);
                if (data.success === true) {

                    alert("Thêm tài khoản thành công");
                    location.href = adminManagerContextPath + "/ManageAccountServlet";

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

function deleteAccount(accountID) {
    // Trả về true nếu người dùng đã xác nhận, ngược lại trả về false
    if (confirm('Bạn có chắc chắn xóa không?')) {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageAccountServlet",
            data: {
                action: "deleteAccount",
                accountID: accountID,
            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.success === true) {
                    alert("Xóa tài khoản thành công");
                    location.href = adminManagerContextPath + "/ManageAccountServlet";

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

function updateAccount() {
    let update_accountID = document.getElementById("update_accountID").value;
    let update_accountName = document.getElementById("update_accountName").value;
    let update_accountPassword = document.getElementById("update_accountPassword").value;
    let update_accountCustomerID = document.getElementById("update_accountCustomerID").value;
    let update_accountPermissionID = document.getElementById("update_accountPermissionID").value;

    if (update_accountName == null || update_accountName == "" || update_accountPassword == null || update_accountPassword == ""
        || update_accountPermissionID == null || update_accountPermissionID == "" || update_accountCustomerID == null || update_accountCustomerID == "") {
        alert("Không thể để dữ liệu trống");
    } else {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageAccountServlet",
            data: {
                action: "updateAccount",
                update_accountID: update_accountID,
                update_accountName: update_accountName,
                update_accountPassword: update_accountPassword,
                update_accountCustomerID: update_accountCustomerID,
                update_accountPermissionID: update_accountPermissionID,
            }, headers: {
                "X-Requested-With": "XMLHttpRequest",
            },
            success: function (data) {

                data = JSON.parse(data);
                if (data.success === true) {

                    alert("Cập nhật tài khoản thành công");
                    refreshAccount();

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

function searchAndSortAccount() {
    let accountSearchType = document.getElementById("accountSearchType").value;
    let accountInputSearch = document.getElementById("accountInputSearch").value;
    let accountSortType = document.getElementById("accountSortType").value;

    if (accountSearchType == null || accountSearchType == "" || accountInputSearch == null || accountInputSearch == "" ||
        accountSortType == null || accountSortType == "") {
        alert("Không thể để dữ liệu trống");
        return false;
    }

    if (accountSearchType == "id" || accountSearchType == "customerID") {
        var num = parseFloat(accountInputSearch);
        if (Number.isInteger(num)) {
            return true;
        } else {
            alert("Dữ liệu điền vào không hợp lệ");
            return false;
        }
    } else if (accountSearchType == "lowerPrice" || accountSearchType == "higherPrice") {
        if (isNaN(accountInputSearch)) {
            return true;
        } else {
            alert("Dữ liệu điền vào không hợp lệ");
            return false;
        }
    }
    return true;

}

function refreshAccount() {
    location.href = adminManagerContextPath + "/ManageAccountServlet";
}

function accountToExcel() {
    let table = document.getElementById("tableAccount");

    let dataToSend = [];

    // Loop through the rows of the table and gather information from each column cell
    for (let i = 1; i < table.rows.length; i++) {
        let currentRow = table.rows[i];

        // Gather information from the column cells in the current row
        let id = currentRow.cells[0].textContent;
        let permissionId = currentRow.cells[1].textContent;
        let customerId = currentRow.cells[2].textContent;
        let name = currentRow.cells[3].textContent;
        let password = currentRow.cells[4].textContent;

        // Create an object containing information from the current row
        let accountData = {
            id: id,
            permission:  permissionId,
            customer: customerId,
            name: name,
            password: password,
        };

        // Add the object to the dataToSend array
        dataToSend.push(accountData);
    }

    const workbook = XLSX.utils.book_new();
    const sheet = XLSX.utils.json_to_sheet(dataToSend);
    XLSX.utils.book_append_sheet(workbook, sheet, "Accounts");

    XLSX.writeFile(workbook, "accounts.xlsx");
}

/*Account*/
function handleAddAccount() {
    var addUser = document.getElementById("account_addTrigger");
    var modal = document.getElementById("modal-add-account");
    var btnClose = document.querySelector(".clsadduser");
    addUser.addEventListener("click", function () {
        modal.classList.add("active");
    });
    btnClose.addEventListener("click", function () {
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

            var accountID = item.getAttribute("data-accountID");
            var accountCustomerID = item.getAttribute("data-accountCustomerID");
            var accountPermissionID = item.getAttribute("data-accountPermissionID");
            var accountName = item.getAttribute("data-accountName");
            var accountPass = item.getAttribute("data-accountPassword");


            document.getElementById("update_accountID").value = accountID;
            document.getElementById("update_accountName").value = accountName;
            document.getElementById("update_accountPassword").value = accountPass;

            var update_customer_selectElement = document.getElementById(
                "update_accountCustomerID"
            );
            for (var i = 0; i < update_customer_selectElement.options.length; i++) {
                var option = update_customer_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === accountCustomerID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }

            var update_permission_selectElement = document.getElementById(
                "update_accountPermissionID"
            );
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