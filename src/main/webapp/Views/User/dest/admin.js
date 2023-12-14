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
  };

  $.ajax({
    type: "POST",
    url: adminManagerContextPath + "/AdminServlet",
    data: {
      action: "addCustomer",
      customer: JSON.stringify(customer),
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

function deleteCustomer(id, action, tableID) {
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

function customerToExcel(tableID) {
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
function accountToExcel(tableID) {
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

  let item = {
    name: itemName,
    itemType: { id: itemTypeID },
    itemCollection: { id: itemCollectionID },
    itemMaterial: { id: itemMaterialID },
    is_new: isNew,
    is_hot: isHot,
    price: itemPrice,
    year_produce: itemYearProduce,
  };

  $.ajax({
    type: "POST",
    url: "${pageContext.request.contextPath}/AdminManagerServlet",
    data: {
      action: "addItem",
      item: JSON.stringify(item),
    },
    headers: {
      "X-Requested-With": "XMLHttpRequest",
    },
    success: function (data) {
      if (data.success === true) {
        let item = data.item;
        let table = document.getElementById("tableItem"); // replace 'yourTableId' with your actual table id

        table.innerHTML +=
          "<tr>\n" +
          "  <td>" +
          item.id +
          "</td>\n" +
          "  <td>" +
          item.name +
          "</td>\n" +
          "  <td>" +
          item.itemType.id +
          "</td>\n" +
          "  <td>" +
          (item.itemCollection ? item.itemCollection.id : "") +
          "</td>\n" +
          "  <td>" +
          item.itemMaterial.id +
          "</td>\n" +
          "  <td>" +
          (item.is_new ? "Mới" : "") +
          "</td>\n" +
          "  <td>" +
          (item.is_hot ? "Hot" : "") +
          "</td>\n" +
          "  <td>" +
          item.price +
          "</td>\n" +
          "  <td>" +
          item.year_produce +
          "</td>\n" +
          "  <td>\n" +
          "    <div class='flex-center grpbtn'>\n" +
          "      <form action='${pageContext.request.contextPath}/AdminManagerServlet' method='post' onsubmit='handleDelete()'>\n" +
          "        <button class='btnHD btnDel' type='submit'>Xóa</button>\n" +
          "        <input type='hidden' name='itemID' value='" +
          item.id +
          "'/>\n" +
          "        <input type='hidden' name='action' value='item_btnDelete'/>\n" +
          "      </form>\n" +
          "      <button class='btnHD btnUpdateItem' data-itemID='" +
          item.id +
          "' data-itemName='" +
          item.name +
          "' data-itemTypeID='" +
          item.itemType.id +
          "' data-itemCollectionID='" +
          (item.itemCollection ? item.itemCollection.id : "") +
          "' data-itemMaterialID='" +
          item.itemMaterial.id +
          "' data-itemIsNew='" +
          item.is_new +
          "' data-itemIsHot='" +
          item.is_hot +
          "' data-itemPrice='" +
          item.price +
          "' data-itemYearProduce='" +
          item.year_produce +
          "'>Sửa</button>\n" +
          "      <input type='hidden' name='itemID' value='" +
          item.id +
          "'/>\n" +
          "    </div>\n" +
          "  </td>\n" +
          "</tr>";

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

///Discount
