
/*Customer*/
function addCustomer() {
    let name = document.getElementById("add_customerName").value;
    let phoneNumber = document.getElementById("add_customerPhoneNumber").value;
    let email = document.getElementById("add_customerEmail").value;
    let address = document.getElementById("add_customerAddress").value;
    let table = document.getElementById("tableCustomer");

    let customer = {
        name: name,
        phone_number: phoneNumber,
        email: email,
        address: address,
    }; //khong dun toi

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "addCustomer",
            name: name,
            phone_number: phoneNumber,
            email: email,
            address: address,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let customer = data.customer;
                table.innerHTML +=
                    "<tr>\n" +
                    "            <td>" +
                    customer.id +
                    "</td>\n" +
                    "            <td>" +
                    customer.name +
                    "</td>\n" +
                    "            <td>" +
                    customer.phone_number +
                    "</td>\n" +
                    "            <td>" +
                    customer.email +
                    "</td>\n" +
                    "            <td>" +
                    customer.address +
                    "</td>\n" +
                    "            <td>\n" +
                    '              <div class="flex-center grpbtn">\n' +
                    '                <a class="btnHD btnDel" type="submit" onclick="deleteCustomer(' +
                    customer.id +
                    ')">Xóa</a>\n' +
                    "                <button\n" +
                    '                        class="btnHD btnUpdateCustomer"\n' +
                    "                        data-customerID=" +
                    customer.id +
                    "\n" +
                    "                        data-customerName=" +
                    customer.name +
                    "\n" +
                    "                        data-customerPhoneNumber=" +
                    customer.phone_number +
                    "\n" +
                    "                        data-customerEmail=" +
                    customer.email +
                    "\n" +
                    "                        data-customerAddress=" +
                    customer.address +
                    "\n" +
                    "                >\n" +
                    "                  Sửa\n" +
                    "                </button>\n" +
                    "              </div>\n" +
                    "            </td>\n" +
                    "          </tr>";
                alert("Thêm khách hàng thành công");
            } else console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function updateCustomer() {
    let id = document.getElementById("update_customerID").value;
    let name = document.getElementById("update_customerName").value;
    let phoneNumber = document.getElementById("update_customerPhoneNumber").value;
    let email = document.getElementById("update_customerEmail").value;
    let address = document.getElementById("update_customerAddress").value;
    let table = document.getElementById("tableCustomer");

    let customer = {
        id: id,
        name: name,
        phone_number: phoneNumber,
        email: email,
        address: address,
    };

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "updateCustomer",
            customer: JSON.stringify(customer),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let customer = data.customer;

                for (let i = 0, row; (row = table.rows[i]); i++) {
                    // Lặp qua từng dòng trong bảng
                    let idCell = row.cells[0]; // Giả sử 'customer.id' là ô đầu tiên trong mỗi dòng
                    if (idCell.textContent === id) {
                        row.cells[1].textContent = customer.name; // Cập nhật tên
                        row.cells[2].textContent = customer.phone_number; // Cập nhật số điện thoại
                        row.cells[3].textContent = customer.email; // Cập nhật email
                        row.cells[4].textContent = customer.address; // Cập nhật địa chỉ
                        break; // Thoát khỏi vòng lặp sau khi cập nhật
                    }
                }
                alert("Cập nhật khách hàng thành công");
            } else console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function handleDelete(id, action, tableID) {
    let confirmationResult = false;
    confirmationResult = confirm("Bạn có chắc chắn muốn xóa?");
    if (confirmationResult) {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/AdminServlet",
            data: {
                action: action,
                id: id,
            },
            headers: {
                "X-Requested-With": "XMLHttpRequest",
            },
            success: function (data) {
                if (data.success === true) {
                    let table = document.getElementById(tableID);

                    for (let i = 0, row; (row = table.rows[i]); i++) {
                        let idCell = row.cells[0]; // Giả sử 'customer.id' là ô đầu tiên trong mỗi dòng
                        if (idCell.textContent == id) {
                            table.deleteRow(i);
                            alert("Xóa thành công");
                        }
                    }
                } else console.log("WTF");
            },
            error: function (error) {
                console.log("error:" + error);
            },
        });
    }
}

function customerToExcel() {
    let table = document.getElementById(tableID);

    let dataToSend = [];

    // Lặp qua các dòng của bảng và thu thập thông tin từ mỗi ô cột
    for (let i = 1; i < table.rows.length; i++) {
        let currentRow = table.rows[i];

        // Thu thập thông tin từ các ô cột trong dòng
        let id = currentRow.cells[0].textContent;
        let name = currentRow.cells[1].textContent;
        let phoneNumber = currentRow.cells[2].textContent;
        let email = currentRow.cells[3].textContent;
        let address = currentRow.cells[4].textContent;

        // Tạo đối tượng chứa thông tin từ dòng hiện tại
        let customerData = {
            id: id,
            name: name,
            phone_number: phoneNumber,
            email: email,
            address: address,
        };

        // Thêm đối tượng vào mảng dataToSend
        dataToSend.push(customerData);
    }

    const workbook = XLSX.utils.book_new();
    const sheet = XLSX.utils.json_to_sheet(dataToSend);
    XLSX.utils.book_append_sheet(workbook, sheet, "Customers");

    XLSX.writeFile(workbook, "customers.xlsx");
}

function searchAndSortCustomer() {
    let customerInputSearch = document.getElementById("customerInputSearch").value;
    let customerSortType = document.getElementById("customerSortType").value;
    let customerSearchType = document.getElementById("customerSearchType").value;

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "searchAndSortCustomer",
            customerInputSearch: customerInputSearch,
            customerSortType: customerSortType,
            customerSearchType: customerSearchType,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let table = document.getElementById("tableCustomer");
                table.innerHTML = "";

                let customerList = data.customerList;
                for (let i = 0; customerList.length; i++) {
                    let customer = customerList[i];
                    table.innerHTML +=
                        "<tr>\n" +
                        "            <td>" +
                        customer.id +
                        "</td>\n" +
                        "            <td>" +
                        customer.name +
                        "</td>\n" +
                        "            <td>" +
                        customer.phone_number +
                        "</td>\n" +
                        "            <td>" +
                        customer.email +
                        "</td>\n" +
                        "            <td>" +
                        customer.address +
                        "</td>\n" +
                        "            <td>\n" +
                        '              <div class="flex-center grpbtn">\n' +
                        '                <a class="btnHD btnDel" type="submit" onclick="deleteCustomer(' +
                        customer.id +
                        ')">Xóa</a>\n' +
                        "                <button\n" +
                        '                        class="btnHD btnUpdateCustomer"\n' +
                        "                        data-customerID=" +
                        customer.id +
                        "\n" +
                        "                        data-customerName=" +
                        customer.name +
                        "\n" +
                        "                        data-customerPhoneNumber=" +
                        customer.phone_number +
                        "\n" +
                        "                        data-customerEmail=" +
                        customer.email +
                        "\n" +
                        "                        data-customerAddress=" +
                        customer.address +
                        "\n" +
                        "                >\n" +
                        "                  Sửa\n" +
                        "                </button>\n" +
                        "              </div>\n" +
                        "            </td>\n" +
                        "          </tr>";
                }
            } else console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

//Account
function addAccount() {
    let name = document.getElementById("accountNameID").value;
    let password = document.getElementById("accountPasswordID").value;
    let permission = document.getElementById("label_permissionID").value;
    let cusID = document.getElementById("add_accountCustomerID").value;

    let account = {
        name: name,
        paswword: password,
        permission: permission,
        cusID: cusID,
    };

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "addAccount",
            account: JSON.stringify(account),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let account = data.account;
                table.innerHTML +=
                    "<tr>\n" +
                    "                        <td>" +
                    account.id +
                    "</td>\n" +
                    "                        <td>" +
                    account.permission.id +
                    "</td>\n" +
                    "                        <td>" +
                    account.customer.id +
                    "</td>\n" +
                    "                        <td>" +
                    account.name +
                    "</td>\n" +
                    "                        <td>" +
                    account.password +
                    "</td>\n" +
                    "                        <td>\n" +
                    '                            <div class="flex-center grpbtn">\n' +
                    "                                <form\n" +
                    '                                        action="${pageContext.request.contextPath}/AdminManagerServlet"\n' +
                    '                                        method="post"\n' +
                    '                                        onsubmit="handleDelete(' + account.id + ',\'deleteAccount\',\'tableAccount\')"\n' +
                    "                                >\n" +
                    '                                    <button class="btnHD btnDel" type="submit" onclick="deleteCustomer(' +
                    account.id +
                    ')">Xóa</button>\n' +
                    '                                    <input type="hidden" name="accountID" value="' +
                    account.id +
                    '"/>\n' +
                    '                                    <input type="hidden" name="action" value="account_btnDelete"/>\n' +
                    "                                </form>\n" +
                    "                                <button\n" +
                    '                                        class="btnHD btnUpdateUser"\n' +
                    '                                        id="account_updateTrigger"\n' +
                    '                                        data-customerID="' +
                    account.customer.id +
                    '"\n' +
                    '                                        data-permissionID="' +
                    account.permission.id +
                    '"\n' +
                    '                                        data-accountName="' +
                    account.name +
                    '"\n' +
                    '                                        data-accountPassword="' +
                    account.password +
                    '"\n' +
                    "                                >\n" +
                    "                                    Sửa\n" +
                    "                                </button>\n" +
                    '                                <input type="hidden" name="accountID" value="' +
                    account.id +
                    '"/>\n' +
                    "                            </div>\n" +
                    "                        </td>\n" +
                    "                    </tr>";
                alert("Thêm tài khoản thành công");
            } else {
                console.log("WTF");
            }
        },

        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function updateAccount() {
    let id = document.getElementById("update_accountNameID").value;
    let password = document.getElementById("update_accountPasswordID").value;
    let permissionId = document.getElementById("update_label_permissionID").value;
    let customerId = document.getElementById("update_label_customerID").value;
    let table = document.getElementById("tableAccount");

    let account = {
        id: id,
        password: password,
        permission: {
            id: permissionId,
        },
        customer: {
            id: customerId,
        },
    };

    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/AdminManagerServlet",
        data: {
            action: "updateAccount",
            account: JSON.stringify(account),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let account = data.account;

                for (let i = 0, row; (row = table.rows[i]); i++) {
                    // Loop through each row in the table
                    let idCell = row.cells[0]; // Assume 'account.id' is the first cell in each row
                    if (idCell.textContent === id) {
                        row.cells[1].textContent = account.permission.id; // Update permission ID
                        row.cells[2].textContent = account.customer.id; // Update customer ID
                        row.cells[3].textContent = account.name; // Update account name
                        row.cells[4].textContent = account.password; // Update account password
                        break; // Exit the loop after updating
                    }
                }
                alert("Cập nhật tài khoản thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function accountToExcel() {
    let table = document.getElementById(tableID);

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
            permission: {
                id: permissionId,
            },
            customer: {
                id: customerId,
            },
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

function searchAndSortAccount() {
    let accountInputSearch = document.getElementById("accountInputSearch").value;
    let accountSortType = document.getElementById("accountSortType").value;
    let accountSearchType = document.getElementById("accountSearchType").value;

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "searchAndSortAccount",
            accountInputSearch: accountInputSearch,
            accountSortType: accountSortType,
            accountSearchType: accountSearchType,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let table = document.getElementById("tableAccount");
                table.innerHTML = "";

                let account = data.account;
                table.innerHTML +=
                    "<tr>\n" +
                    "                        <td>" +
                    account.id +
                    "</td>\n" +
                    "                        <td>" +
                    account.permission.id +
                    "</td>\n" +
                    "                        <td>" +
                    account.customer.id +
                    "</td>\n" +
                    "                        <td>" +
                    account.name +
                    "</td>\n" +
                    "                        <td>" +
                    account.password +
                    "</td>\n" +
                    "                        <td>\n" +
                    '                            <div class="flex-center grpbtn">\n' +
                    "                                <form\n" +
                    '                                        action="${pageContext.request.contextPath}/AdminManagerServlet"\n' +
                    '                                        method="post"\n' +
                    '                                        onsubmit="handleDelete()"\n' +
                    "                                >\n" +
                    '                                    <button class="btnHD btnDel" type="submit" onclick="deleteCustomer(' +
                    account.id +
                    ')">Xóa</button>\n' +
                    '                                    <input type="hidden" name="accountID" value="' +
                    account.id +
                    '"/>\n' +
                    '                                    <input type="hidden" name="action" value="account_btnDelete"/>\n' +
                    "                                </form>\n" +
                    "                                <button\n" +
                    '                                        class="btnHD btnUpdateUser"\n' +
                    '                                        id="account_updateTrigger"\n' +
                    '                                        data-customerID="' +
                    account.customer.id +
                    '"\n' +
                    '                                        data-permissionID="' +
                    account.permission.id +
                    '"\n' +
                    '                                        data-accountName="' +
                    account.name +
                    '"\n' +
                    '                                        data-accountPassword="' +
                    account.password +
                    '"\n' +
                    "                                >\n" +
                    "                                    Sửa\n" +
                    "                                </button>\n" +
                    '                                <input type="hidden" name="accountID" value="' +
                    account.id +
                    '"/>\n' +
                    "                            </div>\n" +
                    "                        </td>\n" +
                    "                    </tr>";
                alert("Thêm tài khoản thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

//Item
function addItem() {
    let itemName = document.getElementById("add_itemNameID").value;
    let itemTypeID = document.getElementById("add_label_itemTypeID").value;
    let itemCollectionID = document.getElementById("add_label_itemCollectionID").value;
    let itemMaterialID = document.getElementById("add_label_itemMaterialID").value;
    let isNew = document.getElementById("add_label_isNewID").checked ? 1 : 0;
    let isHot = document.getElementById("add_label_isHotID").checked ? 1 : 0;
    let itemPrice = document.getElementById("add_itemPriceID").value;
    let itemYearProduce = document.getElementById("add_itemYearProduceID").value;

    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/AdminManagerServlet",
        data: {
            action: "addItem",
            name: itemName,
            itemTypeID: itemTypeID,
            itemCollectionID: itemCollectionID,
            itemMaterialID: itemMaterialID,
            is_new: isNew,
            is_hot: isHot,
            itemPrice: itemPrice,
            year_produce: itemYearProduce,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let item = data.item;
                let table = document.getElementById("tableItem"); // replace 'yourTableId' with your actual table id

                table.innerHTML += '<tr>\n' +
                    '                        <td>' + item.id + '</td>\n' +
                    '                        <td>' + item.name + '</td>\n' +
                    '                        <td>' + item.itemType.id + '</td>\n' +
                    '                        <td>' + item.itemCollection.id + '</td>\n' +
                    '                        <td>' + item.itemMaterial.id + '</td>\n' +
                    '\n' +
                    '                        <c:choose>\n' +
                    '                            <c:when test="${' + item.is_new + '==1}">\n' +
                    '                                <td>Mới</td>\n' +
                    '                            </c:when>\n' +
                    '                            <c:otherwise>\n' +
                    '                                <td></td>\n' +
                    '                            </c:otherwise>\n' +
                    '                        </c:choose>\n' +
                    '\n' +
                    '                        <c:choose>\n' +
                    '                            <c:when test="${' + item.is_hot + '==1}">\n' +
                    '                                <td>Hot</td>\n' +
                    '                            </c:when>\n' +
                    '                            <c:otherwise>\n' +
                    '                                <td></td>\n' +
                    '                            </c:otherwise>\n' +
                    '                        </c:choose>\n' +
                    '\n' +
                    '                        <td>' + item.price + '</td>\n' +
                    '                        <td>' + item.year_produce + '</td>\n' +
                    '                        <td>\n' +
                    '                            <div class="flex-center grpbtn">\n' +
                    '                                    <a class="btnHD btnDel" onclick="handleDelete(' + item.id + ',\'deleteItem\',\'tableItem\')" type="submit">Xóa</a>\n' +
                    '                                <button\n' +
                    '                                        class="btnHD btnUpdateItem"\n' +
                    '                                        data-itemID="' + item.id + '"\n' +
                    '                                        data-itemName="' + item.name + '"\n' +
                    '                                        data-itemTypeID="' + item.itemType.id + '"\n' +
                    '                                        data-itemCollectionID="' + item.itemCollection.id + '"\n' +
                    '                                        data-itemMaterialID="' + item.itemMaterial.id + '"\n' +
                    '                                        data-itemIsNew="' + item.is_new + '"\n' +
                    '                                        data-itemIsHot="' + item.is_hot + '"\n' +
                    '                                        data-itemPrice="' + item.price + '"\n' +
                    '                                        data-itemYearProduce="' + item.year_produce + '"\n' +
                    '                                >\n' +
                    '                                    Sửa\n' +
                    '                                </button>\n' +
                    '                            </div>\n' +
                    '                        </td>\n' +
                    '                    </tr>';

                alert("Thêm sản phẩm thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function updateItem() {
    let id = document.getElementById("update_itemNameID").value;
    let itemTypeID = document.getElementById("update_label_itemTypeID").value;
    let itemCollectionID = document.getElementById("update_label_itemCollectionID").value;
    let itemMaterialID = document.getElementById("update_label_itemMaterialID").value;
    let isNew = document.getElementById("update_itemIsNewID").checked ? 1 : 0;
    let isHot = document.getElementById("update_itemIsHotID").checked ? 1 : 0;
    let itemPrice = document.getElementById("update_itemPriceID").value;
    let itemYearProduce = document.getElementById("update_itemYearProduceID").value;
    let table = document.getElementById("tableItem"); // replace 'tableItemId' with your actual table id

    let item = {
        id: id,
        itemType: itemTypeID,
        itemCollection: itemCollectionID,
        itemMaterial: itemMaterialID,
        is_new: isNew,
        is_hot: isHot,
        price: itemPrice,
        year_produce: itemYearProduce,
    };

    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/AdminManagerServlet",
        data: {
            action: "updateItem",
            item: JSON.stringify(item),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let item = data.item;

                for (let i = 0, row; (row = table.rows[i]); i++) {
                    // Loop through each row in the table
                    let idCell = row.cells[0]; // Assume 'item.id' is the first cell in each row
                    if (idCell.textContent === id) {
                        row.cells[1].textContent = item.name; // Update item name
                        row.cells[2].textContent = item.itemType.id; // Update item type ID
                        row.cells[3].textContent = item.itemCollection.id; // Update item collection ID
                        row.cells[4].textContent = item.itemMaterial.id; // Update item material ID
                        row.cells[5].textContent = item.is_new === 1 ? "Mới" : ""; // Update is new
                        row.cells[6].textContent = item.is_hot === 1 ? "Hot" : ""; // Update is hot
                        row.cells[7].textContent = item.price; // Update item price
                        row.cells[8].textContent = item.year_produce; // Update year produce
                        break; // Exit the loop after updating
                    }
                }
                alert("Cập nhật sản phẩm thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function itemToExcel() {
    let table = document.getElementById("tableItem");

    let dataToSend = [];

    // Loop through the rows of the table and gather information from each column cell
    for (let i = 1; i < table.rows.length; i++) {
        let currentRow = table.rows[i];

        // Gather information from the column cells in the current row
        let id = currentRow.cells[0].textContent;
        let productName = currentRow.cells[1].textContent;
        let productType = currentRow.cells[2].textContent;
        let collection = currentRow.cells[3].textContent;
        let material = currentRow.cells[4].textContent;
        let isNewProduct = currentRow.cells[5].textContent;
        let isHotProduct = currentRow.cells[6].textContent;
        let price = currentRow.cells[7].textContent;
        let manufacturingYear = currentRow.cells[8].textContent;

        // Create an object containing information from the current row
        let productData = {
            id: id,
            productName: productName,
            productType: productType,
            collection: collection,
            material: material,
            is_new: isNewProduct,
            is_hot: isHotProduct,
            price: price,
            year_produce: manufacturingYear,
        };

        // Add the object to the dataToSend array
        dataToSend.push(productData);
    }

    const workbook = XLSX.utils.book_new();
    const sheet = XLSX.utils.json_to_sheet(dataToSend);
    XLSX.utils.book_append_sheet(workbook, sheet, "Products");

    XLSX.writeFile(workbook, "products.xlsx");
}

function searchAndSortItem() {
    let itemInputSearch = document.getElementById("itemInputSearch").value;
    let itemSortType = document.getElementById("itemSortType").value;
    let itemSearchType = document.getElementById("itemSearchType").value;

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "searchAndSortItem",
            itemInputSearch: itemInputSearch,
            itemSortType: itemSortType,
            itemSearchType: itemSearchType,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let table = document.getElementById("tableItem");
                table.innerHTML = "";

                let itemList = data.itemList;
                for (let i = 0; i < itemList.length; i++) {
                    let item = itemList[i];
                    table.innerHTML += '<tr>\n' +
                        '                        <td>' + item.id + '</td>\n' +
                        '                        <td>' + item.name + '</td>\n' +
                        '                        <td>' + item.itemType.id + '</td>\n' +
                        '                        <td>' + item.itemCollection.id + '</td>\n' +
                        '                        <td>' + item.itemMaterial.id + '</td>\n' +
                        '\n' +
                        '                        <c:choose>\n' +
                        '                            <c:when test="${' + item.is_new + '==1}">\n' +
                        '                                <td>Mới</td>\n' +
                        '                            </c:when>\n' +
                        '                            <c:otherwise>\n' +
                        '                                <td></td>\n' +
                        '                            </c:otherwise>\n' +
                        '                        </c:choose>\n' +
                        '\n' +
                        '                        <c:choose>\n' +
                        '                            <c:when test="${' + item.is_hot + '==1}">\n' +
                        '                                <td>Hot</td>\n' +
                        '                            </c:when>\n' +
                        '                            <c:otherwise>\n' +
                        '                                <td></td>\n' +
                        '                            </c:otherwise>\n' +
                        '                        </c:choose>\n' +
                        '\n' +
                        '                        <td>' + item.price + '</td>\n' +
                        '                        <td>' + item.year_produce + '</td>\n' +
                        '                        <td>\n' +
                        '                            <div class="flex-center grpbtn">\n' +
                        '                                    <a class="btnHD btnDel" onclick="handleDelete(' + item.id + ',\'deleteItem\',\'tableItem\')" type="submit">Xóa</a>\n' +
                        '                                <button\n' +
                        '                                        class="btnHD btnUpdateItem"\n' +
                        '                                        data-itemID="' + item.id + '"\n' +
                        '                                        data-itemName="' + item.name + '"\n' +
                        '                                        data-itemTypeID="' + item.itemType.id + '"\n' +
                        '                                        data-itemCollectionID="' + item.itemCollection.id + '"\n' +
                        '                                        data-itemMaterialID="' + item.itemMaterial.id + '"\n' +
                        '                                        data-itemIsNew="' + item.is_new + '"\n' +
                        '                                        data-itemIsHot="' + item.is_hot + '"\n' +
                        '                                        data-itemPrice="' + item.price + '"\n' +
                        '                                        data-itemYearProduce="' + item.year_produce + '"\n' +
                        '                                >\n' +
                        '                                    Sửa\n' +
                        '                                </button>\n' +
                        '                            </div>\n' +
                        '                        </td>\n' +
                        '                    </tr>';
                }
            } else console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}


///Collection- bo suu tap
function addItemCollection() {
    let name = document.getElementById("add_itemCollectionName").value;

    let itemCollection = {
        name: name,
    };

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "addItemCollection",
            itemCollection: JSON.stringify(itemCollection),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let itemCollection = data.itemCollection;
                let table = document.getElementById("yourTableId"); // Replace with the actual ID of your table

                table.innerHTML +=
                    "<tr>\n" +
                    "    <td>" +
                    itemCollection.id +
                    "</td>\n" +
                    "    <td>" +
                    itemCollection.name +
                    "</td>\n" +
                    "    <td>\n" +
                    '        <div class="flex-center grpbtn">\n' +
                    '            <button class="btnHD btnUpdateItemCollection" data-itemCollectionID="' +
                    itemCollection.id +
                    '" data-itemCollectionName="' +
                    itemCollection.name +
                    '">Sửa</button>\n' +
                    "        </div>\n" +
                    "    </td>\n" +
                    "</tr>";

                alert("Thêm bộ sưu tập thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function updateItemCollection(id) {
    let name = document.getElementById("update_itemCollectionName").value;
    let table = document.getElementById("tableItemCollection"); // Replace with the actual ID of your table

    let itemCollection = {
        id: id,
        name: name,
    };

    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/AdminManagerServlet",
        data: {
            action: "updateItemCollection",
            itemCollection: JSON.stringify(itemCollection),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let updatedItemCollection = data.itemCollection;

                for (let i = 0, row; (row = table.rows[i]); i++) {
                    // Loop through each row in the table
                    let idCell = row.cells[0]; // Assume 'itemCollection.id' is the first cell in each row
                    if (idCell.textContent === updatedItemCollection.id) {
                        row.cells[1].textContent = updatedItemCollection.name; // Update item collection name
                        break; // Exit the loop after updating
                    }
                }
                alert("Cập nhật bộ sưu tập thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function collectionToExcel() {
    let table = document.getElementById(tableID);

    let dataToSend = [];

    // Lặp qua các dòng của bảng và thu thập thông tin từ mỗi ô cột
    for (let i = 1; i < table.rows.length; i++) {
        let currentRow = table.rows[i];

        // Thu thập thông tin từ các ô cột trong dòng
        let id = currentRow.cells[0].textContent;
        let name = currentRow.cells[1].textContent;

        // Tạo đối tượng chứa thông tin từ dòng hiện tại
        let collectionData = {
            id: id,
            name: name,
        };

        // Thêm đối tượng vào mảng dataToSend
        dataToSend.push(collectionData);
    }

    const workbook = XLSX.utils.book_new();
    const sheet = XLSX.utils.json_to_sheet(dataToSend);
    XLSX.utils.book_append_sheet(workbook, sheet, "Bộ sưu tập");

    XLSX.writeFile(workbook, "collection.xlsx");
}


//nguyen lieu- item material
function addItemMaterial() {
    let name = document.getElementById("add_itemMaterialName").value;

    let itemMaterial = {
        name: name,
    };

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "addItemMaterial",
            itemMaterial: JSON.stringify(itemMaterial),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let itemMaterial = data.itemMaterial;
                let table = document.getElementById("yourTableId"); // Replace with the actual ID of your table

                table.innerHTML +=
                    "<tr>\n" +
                    "    <td>" +
                    itemMaterial.id +
                    "</td>\n" +
                    "    <td>" +
                    itemMaterial.name +
                    "</td>\n" +
                    "    <td>\n" +
                    '        <div class="flex-center grpbtn">\n' +
                    '            <button class="btnHD btnUpdateItemMaterial" data-itemMaterialID="' +
                    itemMaterial.id +
                    '" data-itemMaterialName="' +
                    itemMaterial.name +
                    '">Sửa</button>\n' +
                    '            <input type="hidden" name="itemMaterialID" value="' +
                    itemMaterial.id +
                    '"/>\n' +
                    "        </div>\n" +
                    "    </td>\n" +
                    "</tr>";

                alert("Thêm vật liệu sản phẩm thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function updateItemMaterial(id) {
    let name = document.getElementById("update_itemMaterialName").value;
    let table = document.getElementById("tableItemMaterial"); // nho thay doi cho phu hop voi table

    let itemMaterial = {
        id: id,
        name: name,
    };

    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/AdminManagerServlet",
        data: {
            action: "updateItemMaterial",
            itemMaterial: JSON.stringify(itemMaterial),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let updatedItemMaterial = data.itemMaterial;

                for (let i = 0, row; (row = table.rows[i]); i++) {
                    // Loop through each row in the table
                    let idCell = row.cells[0]; // Assume 'itemMaterial.id' is the first cell in each row
                    if (idCell.textContent === updatedItemMaterial.id) {
                        row.cells[1].textContent = updatedItemMaterial.name; // Update item material name
                        break; // Exit the loop after updating
                    }
                }
                alert("Cập nhật vật liệu sản phẩm thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function materialToExcel() {
    let table = document.getElementById(tableID);

    let dataToSend = [];

    // Lặp qua các dòng của bảng và thu thập thông tin từ mỗi ô cột
    for (let i = 1; i < table.rows.length; i++) {
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

//Discount
function addDiscountCard() {
    let customerID = document.getElementById("add_discountCardCustomerID").value;
    let name = document.getElementById("add_discountCardName").value;
    let percentage = document.getElementById("add_discountCardPercentage").value;

    let discountCard = {
        customerID: customerID,
        name: name,
        discountPercentage: percentage,
    };

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "addDiscountCard",
            discountCard: JSON.stringify(discountCard),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let discountCard = data.discountCard;
                let table = document.getElementById("yourTableId"); // Replace with the actual ID of your table

                table.innerHTML +=
                    "<tr>\n" +
                    "    <td>" +
                    discountCard.id +
                    "</td>\n" +
                    "    <td>" +
                    discountCard.customer.id +
                    "</td>\n" +
                    "    <td>" +
                    discountCard.name +
                    "</td>\n" +
                    "    <td>" +
                    discountCard.discount_percentage +
                    "</td>\n" +
                    "    <td>\n" +
                    '        <div class="flex-center grpbtn">\n' +
                    '            <button class="btnHD btnUpdateDiscountCard" data-discountCardID="' +
                    discountCard.id +
                    '" data-discountCardCustomerID="' +
                    discountCard.customer.id +
                    '" data-discountCardName="' +
                    discountCard.name +
                    '" data-discountCardPercentage="' +
                    discountCard.discount_percentage +
                    '">Sửa</button>\n' +
                    '            <input type="hidden" name="discountCardID" value="' +
                    discountCard.id +
                    '"/>\n' +
                    "        </div>\n" +
                    "    </td>\n" +
                    "</tr>";

                alert("Thêm thẻ khuyến mãi thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function updateDiscountCard() {
    let id = document.getElementById("update_discountCardCustomerID").value;
    let name = document.getElementById("update_discountCardName").value;
    let percentage = document.getElementById("update_discountCardPercentage").value;
    let table = document.getElementById("yourTableId"); // thay doi cái nau de có bảng discount

    let discountCard = {
        id: id,
        name: name,
        discount_percentage: percentage,
    };

    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/AdminManagerServlet",
        data: {
            action: "updateDiscountCard",
            discountCard: JSON.stringify(discountCard),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let updatedDiscountCard = data.discountCard;

                for (let i = 0, row; (row = table.rows[i]); i++) {
                    // Loop through each row in the table
                    let idCell = row.cells[0]; // Assume 'discountCard.id' is the first cell in each row
                    if (idCell.textContent === updatedDiscountCard.id) {
                        row.cells[1].textContent = updatedDiscountCard.customer.id; // Update customer ID
                        row.cells[2].textContent = updatedDiscountCard.name; // Update discount card name
                        row.cells[3].textContent = updatedDiscountCard.discount_percentage; // Update discount percentage
                        break; // Exit the loop after updating
                    }
                }
                alert("Cập nhật thẻ khuyến mãi thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function discountCardToExcel() {
    let table = document.getElementById(tableID);

    let dataToSend = [];

    // Lặp qua các dòng của bảng và thu thập thông tin từ mỗi ô cột
    for (let i = 1; i < table.rows.length; i++) {
        let currentRow = table.rows[i];

        // Thu thập thông tin từ các ô cột trong dòng
        let id = currentRow.cells[0].textContent;
        let cus = currentRow.cells[1].textContent;
        let name = currentRow.cells[2].textContent;
        let discount = currentRow.cells[3].textContent;

        // Tạo đối tượng chứa thông tin từ dòng hiện tại
        let discountData = {
            id: id,
            cus: cus,
            name: name,
            discount: discount,
        };

        // Thêm đối tượng vào mảng dataToSend
        dataToSend.push(discountData);
    }

    const workbook = XLSX.utils.book_new();
    const sheet = XLSX.utils.json_to_sheet(dataToSend);
    XLSX.utils.book_append_sheet(workbook, sheet, "Nguyên liệu");

    XLSX.writeFile(workbook, "ItemMaterial.xlsx");
}

//SaleProduct
function addSale() {
    let itemID = document.getElementById("add_saleItemID").value;
    let name = document.getElementById("add_saleName").value;
    let onSale = document.getElementById("add_saleOnSale").checked;
    let percentage = document.getElementById("add_salePercentage").value;

    let sale = {
        item: {
            id: itemID,
        },
        name: name,
        on_sale: onSale,
        sale_percentage: percentage,
    };

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "addSale",
            sale: JSON.stringify(sale),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let sale = data.sale;
                table.innerHTML +=
                    "<tr>\n" +
                    "    <td>" +
                    sale.id +
                    "</td>\n" +
                    "    <td>" +
                    sale.item.id +
                    "</td>\n" +
                    "    <td>" +
                    sale.name +
                    "</td>\n" +
                    "    <td>" +
                    sale.on_sale +
                    "</td>\n" +
                    "    <td>" +
                    sale.sale_percentage +
                    "</td>\n" +
                    "    <td>\n" +
                    '        <div class="flex-center grpbtn">\n' +
                    "            <button\n" +
                    '                    class="btnHD btnUpdateSale"\n' +
                    '                    data-saleID="' +
                    sale.id +
                    '"\n' +
                    '                    data-saleItemID="' +
                    sale.item.id +
                    '"\n' +
                    '                    data-saleName="' +
                    sale.name +
                    '"\n' +
                    '                    data-saleOnSale="' +
                    sale.on_sale +
                    '"\n' +
                    '                    data-salePercentage="' +
                    sale.sale_percentage +
                    '"\n' +
                    "            >\n" +
                    "                Sửa\n" +
                    "            </button>\n" +
                    '            <input type="hidden" name="saleID" value="' +
                    sale.id +
                    '"/>\n' +
                    "        </div>\n" +
                    "    </td>\n" +
                    "</tr>";

                alert("Thêm sản phẩm sale thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function updateSale() {
    let id = document.getElementById("update_saleItemID").value;
    let name = document.getElementById("update_saleName").value;
    let onSale = document.getElementById("update_saleOnSale").checked;
    let percentage = document.getElementById("update_salePercentage").value;
    let table = document.getElementById("yourTableId"); // Replace with the actual ID of your table

    let sale = {
        id: id,
        name: name,
        on_sale: onSale,
        sale_percentage: percentage,
    };

    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/AdminManagerServlet",
        data: {
            action: "updateSale",
            sale: JSON.stringify(sale),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let updatedSale = data.sale;

                for (let i = 0, row; (row = table.rows[i]); i++) {
                    // Loop through each row in the table
                    let idCell = row.cells[0]; // Assume 'sale.id' is the first cell in each row
                    if (idCell.textContent === updatedSale.id) {
                        // Update the row cells based on the server response
                        row.cells[1].textContent = updatedSale.item.id; // Update item ID
                        row.cells[2].textContent = updatedSale.name; // Update sale name
                        row.cells[3].textContent = updatedSale.on_sale; // Update sale on_sale
                        row.cells[4].textContent = updatedSale.sale_percentage; // Update sale percentage
                        break; // Exit the loop after updating
                    }
                }
                alert("Cập nhật sản phẩm sale thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function saleToExcel() {
    let table = document.getElementById(tableID);

    let dataToSend = [];

    // Lặp qua các dòng của bảng và thu thập thông tin từ mỗi ô cột
    for (let i = 1; i < table.rows.length; i++) {
        let currentRow = table.rows[i];

        // Thu thập thông tin từ các ô cột trong dòng
        let id = currentRow.cells[0].textContent;
        let productID = currentRow.cells[1].textContent;
        let nameSale = currentRow.cells[2].textContent;
        let qty = currentRow.cells[3].textContent;
        let discount = currentRow.cells[5].textContent;

        // Tạo đối tượng chứa thông tin từ dòng hiện tại
        let saleData = {
            id: id,
            productID: productID,
            nameSale: nameSale,
            qty: qty,
            discount: discount,
        };

        // Thêm đối tượng vào mảng dataToSend
        dataToSend.push(saleData);
    }

    const workbook = XLSX.utils.book_new();
    const sheet = XLSX.utils.json_to_sheet(dataToSend);
    XLSX.utils.book_append_sheet(workbook, sheet, "Sale");

    XLSX.writeFile(workbook, "SaleProduct.xlsx");
}



//imageProduct
function addItemImage() {
    let itemID = document.getElementById("add_itemImageItemID").value;
    let imageURL = document.getElementById("add_itemImageURL").value;
    let table = document.getElementById("yourTableId"); // Replace with the actual ID of your table

    let itemImage = {
        item: {
            id: itemID,
        },
        image_url: imageURL,
    };

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "addItemImage",
            itemImage: JSON.stringify(itemImage),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let addedItemImage = data.itemImage;

                table.innerHTML +=
                    "<tr>\n" +
                    "                        <td>" +
                    addedItemImage.id +
                    "</td>\n" +
                    "                        <td>" +
                    addedItemImage.item.id +
                    "</td>\n" +
                    "                        <td>" +
                    addedItemImage.image_url +
                    "</td>\n" +
                    "                        <td>\n" +
                    '                            <div class="flex-center grpbtn">\n' +
                    "                                <button\n" +
                    '                                        class="btnHD btnUpdateItemImage"\n' +
                    '                                        data-itemImageID="' +
                    addedItemImage.id +
                    '"\n' +
                    '                                        data-itemImageItemID="' +
                    addedItemImage.item.id +
                    '"\n' +
                    '                                        data-itemImageURL="' +
                    addedItemImage.image_url +
                    '"\n' +
                    "                                >\n" +
                    "                                    Sửa\n" +
                    "                                </button>\n" +
                    '                                <input type="hidden" name="itemImageID" value="' +
                    addedItemImage.id +
                    '"/>\n' +
                    "                            </div>\n" +
                    "                        </td>\n" +
                    "                    </tr>";
                alert("Thêm hình ảnh sản phẩm thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function updateItemImage() {
    let id = document.getElementById("update_itemImageID").value;
    let itemID = document.getElementById("update_itemImageItemID").value;
    let imageURL = document.getElementById("update_itemImageURL").value;
    let table = document.getElementById("yourTableId"); // Replace with the actual ID of your table

    let itemImage = {
        id: id,
        item: {
            id: itemID,
        },
        image_url: imageURL,
    };

    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/AdminManagerServlet",
        data: {
            action: "updateItemImage",
            itemImage: JSON.stringify(itemImage),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let updatedItemImage = data.itemImage;

                for (let i = 0, row; (row = table.rows[i]); i++) {
                    // Loop through each row in the table
                    let idCell = row.cells[0]; // Assume 'itemImage.id' is the first cell in each row
                    if (idCell.textContent === id) {
                        row.cells[1].textContent = updatedItemImage.item.id; // Update item ID
                        row.cells[2].textContent = updatedItemImage.image_url; // Update image URL
                        break; // Exit the loop after updating
                    }
                }
                alert("Cập nhật hình ảnh sản phẩm thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function imagesToExcel() {
    let table = document.getElementById(tableID);

    let dataToSend = [];

    // Lặp qua các dòng của bảng và thu thập thông tin từ mỗi ô cột
    for (let i = 1; i < table.rows.length; i++) {
        let currentRow = table.rows[i];

        // Thu thập thông tin từ các ô cột trong dòng
        let id = currentRow.cells[0].textContent;
        let productID = currentRow.cells[1].textContent;
        let nameIamge = currentRow.cells[2].textContent;

        // Tạo đối tượng chứa thông tin từ dòng hiện tại
        let ImagesData = {
            id: id,
            productID: productID,
            nameIamge: nameIamge,
        };

        // Thêm đối tượng vào mảng dataToSend
        dataToSend.push(ImagesData);
    }

    const workbook = XLSX.utils.book_new();
    const sheet = XLSX.utils.json_to_sheet(dataToSend);
    XLSX.utils.book_append_sheet(workbook, sheet, "Sale");

    XLSX.writeFile(workbook, "SaleProduct.xlsx");
}

function searchAndSortItemImage() {
    let imageInputSearch = document.getElementById("imageInputSearch").value;
    let imageSortType = document.getElementById("imageSortType").value;
    let imageSearchType = document.getElementById("imageSearchType").value;

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "searchAndSortItemImage",
            imageInputSearch: imageInputSearch,
            imageSortType: imageSortType,
            imageSearchType: imageSearchType,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let table = document.getElementById("tableItemImage");
                table.innerHTML = "";

                // Assuming data.items is an array of item images
                data.items.forEach(function (itemImage) {
                    table.innerHTML +=
                        "<tr>\n" +
                        "                        <td>" +
                        itemImage.id +
                        "</td>\n" +
                        "                        <td>" +
                        itemImage.item.id +
                        "</td>\n" +
                        "                        <td>" +
                        itemImage.image_url +
                        "</td>\n" +
                        "                        <td>\n" +
                        '                            <div class="flex-center grpbtn">\n' +
                        "                                <button\n" +
                        '                                        class="btnHD btnUpdateItemImage"\n' +
                        '                                        data-itemImageID="' +
                        itemImage.id +
                        '"\n' +
                        '                                        data-itemImageItemID="' +
                        itemImage.item.id +
                        '"\n' +
                        '                                        data-itemImageURL="' +
                        itemImage.image_url +
                        '"\n' +
                        "                                >\n" +
                        "                                    Sửa\n" +
                        "                                </button>\n" +
                        '                                <input type="hidden" name="itemImageID" value="' +
                        itemImage.id +
                        '"/>\n' +
                        "                            </div>\n" +
                        "                        </td>\n" +
                        "                    </tr>";
                });

                alert("Tìm và sắp xếp hình ảnh sản phẩm thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}



//itemType
function addItemType() {
    let itemTypeName = document.getElementById("add_itemTypeName").value;
    let table = document.getElementById("tableItemType"); // Replace with the actual ID of your table


    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "addItemType",
            name: itemTypeName,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let addedItemType = data.itemType;

                table.innerHTML +=
                    "<tr>\n" +
                    "                        <td>" +
                    addedItemType.id +
                    "</td>\n" +
                    "                        <td>" +
                    addedItemType.name +
                    "</td>\n" +
                    "                        <td>\n" +
                    '                            <div class="flex-center grpbtn">\n' +
                    "                                <button\n" +
                    '                                        class="btnHD btnUpdateItemType"\n' +
                    '                                        data-itemTypeID="' +
                    addedItemType.id +
                    '"\n' +
                    '                                        data-itemTypeName="' +
                    addedItemType.name +
                    '"\n' +
                    "                                >\n" +
                    "                                    Sửa\n" +
                    "                                </button>\n" +
                    '"/>\n' +
                    "                            </div>\n" +
                    "                        </td>\n" +
                    "                    </tr>";
                alert("Thêm loại sản phẩm thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function updateItemType() {
    let itemTypeID = document.getElementById("update_itemTypeID").value;
    let itemTypeName = document.getElementById("update_itemTypeName").value;
    let table = document.getElementById("yourTableId"); // Replace with the actual ID of your table

    let itemType = {
        id: itemTypeID,
        name: itemTypeName,
    };

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "updateItemType",
            itemType: JSON.stringify(itemType),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let updatedItemType = data.itemType;

                // Loop through each row in the table
                for (let i = 0, row; (row = table.rows[i]); i++) {
                    let idCell = row.cells[0]; // Assuming 'itemType.id' is the first cell in each row
                    if (idCell.textContent === itemTypeID) {
                        // Update the corresponding row
                        row.cells[1].textContent = updatedItemType.name; // Assuming 'itemType.name' is the second cell in each row
                        break;
                    }
                }

                alert("Cập nhật loại sản phẩm thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}



/*StockItem*/
function addStockItem() {

    let itemID = document.getElementById("add_stockItemItemID").value;
    let color = document.getElementById("add_stockItemColor").value;
    let size = document.getElementById("add_stockItemSize").value;
    let amount = document.getElementById("add_stockItemAmount").value;
    let table = document.getElementById("tableStockItem");

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "addStockItem",
            itemID: itemID,
            color: color,
            size: size,
            amount: amount,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            let stockItem = data.stockItem;

            if (data.success === true) {
                table.innerHTML += '<tr>\n' +
                    '                        <td>' + stockItem.id + '</td>\n' +
                    '                        <td>' + stockItem.item.id + '</td>\n' +
                    '                        <td>' + stockItem.color + '</td>\n' +
                    '                        <td>' + stockItem.size + '</td>\n' +
                    '                        <td>' + stockItem.amount + '</td>\n' +
                    '                        <td>\n' +
                    '                            <div class="flex-center grpbtn">\n' +
                    '\n' +
                    '                                    <a class="btnHD btnDel" onclick="handleDelete(' + stockItem.id + ',\'deleteStockItem\',\'tableStockItem\')"\n' +
                    '                                       type="submit">Xóa</a>\n' +
                    '\n' +
                    '                                <button\n' +
                    '                                        class="btnHD btnUpdateStockItem"\n' +
                    '                                        data-stockItemID=' + stockItem.id + '\n' +
                    '                                        data-stockItemItemID=' + stockItem.item_id + '\n' +
                    '                                        data-stockItemColor=' + stockItem.color + '\n' +
                    '                                        data-stockItemSize=' + stockItem.size + '\n' +
                    '                                        data-stockItemAmount=' + stockItem.amount + '\n' +
                    '                                >\n' +
                    '                                    Sửa\n' +
                    '                                </button>\n' +
                    '\n' +
                    '                            </div>\n' +
                    '                        </td>\n' +
                    '                    </tr>';

                alert("Thêm thông tin sản phẩm thành công");
            } else console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function updateStockItem() {
    let id = document.getElementById("update_stockItemItemID").value;
    let itemID = document.getElementById("add_stockItemItemID").value;
    let color = document.getElementById("add_stockItemColor").value;
    let size = document.getElementById("add_stockItemSize").value;
    let amount = document.getElementById("add_stockItemAmount").value;
    let table = document.getElementById("tableStockItem");

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "updateStockItem",
            id: id,
            itemID: itemID,
            color: color,
            size: size,
            amount: amount,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let stockItem = data.stockItem;

                for (let i = 0, row; (row = table.rows[i]); i++) {
                    // Loop through each row in the table
                    let idCell = row.cells[0]; // Assuming 'stockItem.id' is in the first cell of each row
                    if (idCell.textContent === stockItem.id) {
                        row.cells[1].textContent = stockItem.item.id; // Update item id
                        row.cells[2].textContent = stockItem.color; // Update color
                        row.cells[3].textContent = stockItem.size; // Update size
                        row.cells[4].textContent = stockItem.amount; // Update amount
                        break; // Exit the loop after updating
                    }
                }
                alert("Cập nhật thông tin sản phẩm thành công");
            } else console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function stockItemToExcel() {
    let table = document.getElementById(tableID);

    let dataToSend = [];

    for (let i = 1; i < table.rows.length; i++) {
        let currentRow = table.rows[i];

        let id = currentRow.cells[0].textContent;
        let itemID = currentRow.cells[1].textContent;
        let color = currentRow.cells[2].textContent;
        let size = currentRow.cells[3].textContent;
        let amount = currentRow.cells[4].textContent;

        let stockItemData = {
            id: id,
            itemID: itemID,
            color: color,
            size: size,
            amount: amount,
        };

        dataToSend.push(stockItemData);
    }

    const workbook = XLSX.utils.book_new();
    const sheet = XLSX.utils.json_to_sheet(dataToSend);
    XLSX.utils.book_append_sheet(workbook, sheet, "Customers");

    XLSX.writeFile(workbook, "stockItem.xlsx");
}

function searchAndSortStockItem() {
    let stockItemInputSearch = document.getElementById("stockItemInputSearch").value;
    let stockItemSortType = document.getElementById("stockItemSortType").value;
    let stockItemSearchType = document.getElementById("stockItemSearchType").value;

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "searchAndSortStockItem",
            stockItemInputSearch: stockItemInputSearch,
            stockItemSortType: stockItemSortType,
            stockItemSearchType: stockItemSearchType,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let table = document.getElementById("tableStockItem");
                table.innerHTML = "";

                let stockItemList = data.stockItemList;
                for (let i = 0; stockItemList.length; i++) {
                    let stockItem = stockItemList[i];
                    table.innerHTML += '<tr>\n' +
                        '                        <td>' + stockItem.id + '</td>\n' +
                        '                        <td>' + stockItem.item.id + '</td>\n' +
                        '                        <td>' + stockItem.color + '</td>\n' +
                        '                        <td>' + stockItem.size + '</td>\n' +
                        '                        <td>' + stockItem.amount + '</td>\n' +
                        '                        <td>\n' +
                        '                            <div class="flex-center grpbtn">\n' +
                        '\n' +
                        '                                    <a class="btnHD btnDel" onclick="handleDelete(' + stockItem.id + ',\'deleteStockItem\',\'tableStockItem\')"\n' +
                        '                                       type="submit">Xóa</a>\n' +
                        '\n' +
                        '                                <button\n' +
                        '                                        class="btnHD btnUpdateStockItem"\n' +
                        '                                        data-stockItemID=' + stockItem.id + '\n' +
                        '                                        data-stockItemItemID=' + stockItem.item_id + '\n' +
                        '                                        data-stockItemColor=' + stockItem.color + '\n' +
                        '                                        data-stockItemSize=' + stockItem.size + '\n' +
                        '                                        data-stockItemAmount=' + stockItem.amount + '\n' +
                        '                                >\n' +
                        '                                    Sửa\n' +
                        '                                </button>\n' +
                        '\n' +
                        '                            </div>\n' +
                        '                        </td>\n' +
                        '                    </tr>';
                }
            } else console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}



/*Permission*/
function addPermission() {
    let name = document.getElementById("add_permissionName").value;
    let level = document.getElementById("add_permissionLevel").value;
    let table = document.getElementById("tablePermission");

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "addPermission",
            name: name,
            level: level,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            let permission = data.permission;

            if (data.success === true) {
                table.innerHTML += '<tr>\n' +
                    '                        <td>' + permission.id + '</td>\n' +
                    '                        <td>' + permission.name + '</td>\n' +
                    '                        <td>' + permission.level + '</td>\n' +
                    '                        <td>\n' +
                    '                            <div class="flex-center grpbtn">\n' +
                    '                                    <a class="btnHD btnDel" onclick="handleDelete(' + permission.id + ',\'deleteStockItem\',\'tableStockItem\')" type="submit">Xóa</a>\n' +
                    '                                <button\n' +
                    '                                        class="btnHD btnUpdatePermission"\n' +
                    '                                        data-permissionID="' + permission.id + '"\n' +
                    '                                        data-permissionName="' + permission.name + '"\n' +
                    '                                        data-permissionLevel="' + permission.level + '"\n' +
                    '                                >\n' +
                    '                                    Sửa\n' +
                    '                                </button>\n' +
                    '                            </div>\n' +
                    '                        </td>\n' +
                    '                    </tr>';
                alert("Thêm quyền thành công");
            } else console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function updatePermission() {
    let id = document.getElementById("update_permissionID").value;
    let name = document.getElementById("update_permissionName").value;
    let level = document.getElementById("update_permissionLevel").value;
    let table = document.getElementById("tablePermission");

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "updatePermission",
            id: id,
            name: name,
            level: level,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let permission = data.permission;

                for (let i = 0, row; (row = table.rows[i]); i++) {
                    let idCell = row.cells[0];
                    if (idCell.textContent === permission.id) {
                        row.cells[1].textContent = permission.name;
                        row.cells[2].textContent = permission.level;
                        break;
                    }
                }
                alert("Cập nhật quyền thành công");
            } else console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function permissionToExcel() {
    let table = document.getElementById(tableID);

    let dataToSend = [];

    for (let i = 1; i < table.rows.length; i++) {
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

function searchAndSortPermission() {
    let permissionInputSearch = document.getElementById("permissionInputSearch").value;
    let permissionSortType = document.getElementById("permissionSortType").value;
    let permissionSearchType = document.getElementById("permissionSearchType").value;

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "searchAndSortPermission",
            permissionInputSearch: permissionInputSearch,
            permissionSortType: permissionSortType,
            permissionSearchType: permissionSearchType,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let table = document.getElementById("tablePermission");
                table.innerHTML = "";

                let permissionList = data.permissionList;
                for (let i = 0; i < permissionList.length; i++) {
                    let permission = permissionList[i];
                    table.innerHTML += '<tr>\n' +
                        '                        <td>' + permission.id + '</td>\n' +
                        '                        <td>' + permission.name + '</td>\n' +
                        '                        <td>' + permission.level + '</td>\n' +
                        '                        <td>\n' +
                        '                            <div class="flex-center grpbtn">\n' +
                        '                                    <a class="btnHD btnDel" onclick="handleDelete(' + permission.id + ',\'deleteStockItem\',\'tableStockItem\')" type="submit">Xóa</a>\n' +
                        '                                <button\n' +
                        '                                        class="btnHD btnUpdatePermission"\n' +
                        '                                        data-permissionID="' + permission.id + '"\n' +
                        '                                        data-permissionName="' + permission.name + '"\n' +
                        '                                        data-permissionLevel="' + permission.level + '"\n' +
                        '                                >\n' +
                        '                                    Sửa\n' +
                        '                                </button>\n' +
                        '                            </div>\n' +
                        '                        </td>\n' +
                        '                    </tr>';
                }
            } else console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}


