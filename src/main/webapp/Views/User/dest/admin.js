
function customerToExcel(tableID) {
    let table = document.getElementById("tableCustomer");

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
/*function accountToExcel() {
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
}*/

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

function collectionToExcel() {
    let table = document.getElementById("tableCollection");

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

function materialToExcel() {
    let table = document.getElementById("tableMaterial");

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

function searchAndSortItemMaterial() {
    let itemMaterialInputSearch = document.getElementById("itemMaterialInputSearch").value;
    let itemMaterialSortType = document.getElementById("itemMaterialSortType").value;
    let itemMaterialSearchType = document.getElementById("itemMaterialSearchType").value;

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "searchAndSortItemMaterial",
            itemMaterialInputSearch: itemMaterialInputSearch,
            itemMaterialSortType: itemMaterialSortType,
            itemMaterialSearchType: itemMaterialSearchType,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let table = document.getElementById("tableCollection");
                table.innerHTML = "";

                let itemMaterial = data.itemMaterial;
                for (let i = 0; i < itemMaterial.length; i++) {
                    let itemmaterial = itemMaterial[i];
                    table.innerHTML +=
                        "<tr>\n" +
                        "    <td>" +
                        itemmaterial.id +
                        "</td>\n" +
                        "    <td>" +
                        itemmaterial.name +
                        "</td>\n" +
                        "    <td>\n" +
                        '        <div class="flex-center grpbtn">\n' +
                        '            <button class="btnHD btnUpdateItemMaterial" data-itemMaterialID="' +
                        itemmaterial.id +
                        '" data-itemMaterialName="' +
                        itemmaterial.name +
                        '">Sửa</button>\n' +
                        '            <input type="hidden" name="itemMaterialID" value="' +
                        itemmaterial.id +
                        '"/>\n' +
                        "        </div>\n" +
                        "    </td>\n" +
                        "</tr>";
                }
            } else console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
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
            customerID: customerID,
            name: name,
            discountPercentage: percentage,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let discountCard = data.discountCard;
                let table = document.getElementById("tableDiscount"); // Replace with the actual ID of your table

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
    let table = document.getElementById("tableDiscount"); // thay doi cái nau de có bảng discount

    let discountCard = {
        id: id,
        name: name,
        discount_percentage: percentage,
    };

    $.ajax({
        type: "POST",
        url: "pageContext.request.contextPath}/AdminManagerServlet",
        data: {
            action: "updateDiscountCard",
            id: id,
            name: name,
            discount_percentage: percentage,
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

/*function discountCardToExcel() {
    let table = document.getElementById("tableDiscountCard");

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
}*/

function searchAndSortDiscount() {
    let discountCardInputSearch = document.getElementById("discountCardInputSearch").value;
    let discountCardSortType = document.getElementById("discountCardSortType").value;
    let discountCardSearchType = document.getElementById("discountCardSearchType").value;

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "searchAndSortDiscount",
            discountCardInputSearch: discountCardInputSearch,
            discountCardSortType: discountCardSortType,
            discountCardSearchType: discountCardSearchType,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let table = document.getElementById("tableDiscount");
                table.innerHTML = "";

                let discountCard = data.discountCard;
                for (let i = 0; i < discountCard.length; i++) {
                    let discountCard = discountCard[i];
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
                }
            } else console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

//SaleProduct
function addSale() {
    let itemID = document.getElementById("add_saleItemID").value;
    let name = document.getElementById("add_saleName").value;
    let onSale = document.getElementById("add_saleOnSale").checked;
    let percentage = document.getElementById("add_salePercentage").value;

    let sale = {
        item: itemID,
        name: name,
        on_sale: onSale,
        sale_percentage: percentage,
    };

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "addSale",
            item: itemID,
            name: name,
            on_sale: onSale,
            sale_percentage: percentage,
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
        url: "pageContext.request.contextPath}/AdminManagerServlet",
        data: {
            action: "updateSale",
            id: id,
            item: name,
            on_sale: onSale,
            sale_percentage: percentage,
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
    let table = document.getElementById("tableSale");

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

function searchAndSortSale() {
    let saleInputSearch = document.getElementById("saleInputSearch").value;
    let saleSortType = document.getElementById("saleSortType").value;
    let saleSearchType = document.getElementById("saleSearchType").value;

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "searchAndSortSale",
            discountCardInputSearch: discountCardInputSearch,
            discountCardSortType: discountCardSortType,
            discountCardSearchType: discountCardSearchType,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let table = document.getElementById("tableSale");
                table.innerHTML = "";

                let saleList = data.saleList;
                for (let i = 0; i < saleList.length; i++) {
                    let sale = saleList[i];
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
                }
            } else console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
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
        url: adminManagerContextPath+"/AdminManagerServlet",
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

function searchAndSortItemType() {
    let itemTypeInputSearch = document.getElementById("itemTypeInputSearch").value;
    let itemTypeSortType = document.getElementById("itemTypeSortType").value;
    let itemTypeSearchType = document.getElementById("itemTypeSearchType").value;

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "searchAndSortItemType",
            itemTypeInputSearch: itemTypeInputSearch,
            itemTypeSortType: itemTypeSortType,
            itemTypeSearchType: itemTypeSearchType,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let table = document.getElementById("tableStockItem");
                table.innerHTML = "";

                let itemTypeList = data.itemTypeList;
                for (let i = 0; itemTypeList.length; i++) {
                    let addedItemType = itemTypeList[i];
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
                }
            } else console.log("WTF");
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
                table.innerHTML +=
                    "<tr>\n" +
                    "                        <td>" +
                    stockItem.id +
                    "</td>\n" +
                    "                        <td>" +
                    stockItem.item.id +
                    "</td>\n" +
                    "                        <td>" +
                    stockItem.color +
                    "</td>\n" +
                    "                        <td>" +
                    stockItem.size +
                    "</td>\n" +
                    "                        <td>" +
                    stockItem.amount +
                    "</td>\n" +
                    "                        <td>\n" +
                    '                            <div class="flex-center grpbtn">\n' +
                    "\n" +
                    '                                    <a class="btnHD btnDel" onclick="handleDelete(' +
                    stockItem.id +
                    ",'deleteStockItem','tableStockItem')\"\n" +
                    '                                       type="submit">Xóa</a>\n' +
                    "\n" +
                    "                                <button\n" +
                    '                                        class="btnHD btnUpdateStockItem"\n' +
                    "                                        data-stockItemID=" +
                    stockItem.id +
                    "\n" +
                    "                                        data-stockItemItemID=" +
                    stockItem.item_id +
                    "\n" +
                    "                                        data-stockItemColor=" +
                    stockItem.color +
                    "\n" +
                    "                                        data-stockItemSize=" +
                    stockItem.size +
                    "\n" +
                    "                                        data-stockItemAmount=" +
                    stockItem.amount +
                    "\n" +
                    "                                >\n" +
                    "                                    Sửa\n" +
                    "                                </button>\n" +
                    "\n" +
                    "                            </div>\n" +
                    "                        </td>\n" +
                    "                    </tr>";

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
    let table = document.getElementById("tableStockItem");

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
                    table.innerHTML +=
                        "<tr>\n" +
                        "                        <td>" +
                        stockItem.id +
                        "</td>\n" +
                        "                        <td>" +
                        stockItem.item.id +
                        "</td>\n" +
                        "                        <td>" +
                        stockItem.color +
                        "</td>\n" +
                        "                        <td>" +
                        stockItem.size +
                        "</td>\n" +
                        "                        <td>" +
                        stockItem.amount +
                        "</td>\n" +
                        "                        <td>\n" +
                        '                            <div class="flex-center grpbtn">\n' +
                        "\n" +
                        '                                    <a class="btnHD btnDel" onclick="handleDelete(' +
                        stockItem.id +
                        ",'deleteStockItem','tableStockItem')\"\n" +
                        '                                       type="submit">Xóa</a>\n' +
                        "\n" +
                        "                                <button\n" +
                        '                                        class="btnHD btnUpdateStockItem"\n' +
                        "                                        data-stockItemID=" +
                        stockItem.id +
                        "\n" +
                        "                                        data-stockItemItemID=" +
                        stockItem.item_id +
                        "\n" +
                        "                                        data-stockItemColor=" +
                        stockItem.color +
                        "\n" +
                        "                                        data-stockItemSize=" +
                        stockItem.size +
                        "\n" +
                        "                                        data-stockItemAmount=" +
                        stockItem.amount +
                        "\n" +
                        "                                >\n" +
                        "                                    Sửa\n" +
                        "                                </button>\n" +
                        "\n" +
                        "                            </div>\n" +
                        "                        </td>\n" +
                        "                    </tr>";
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
                table.innerHTML +=
                    "<tr>\n" +
                    "                        <td>" +
                    permission.id +
                    "</td>\n" +
                    "                        <td>" +
                    permission.name +
                    "</td>\n" +
                    "                        <td>" +
                    permission.level +
                    "</td>\n" +
                    "                        <td>\n" +
                    '                            <div class="flex-center grpbtn">\n' +
                    '                                    <a class="btnHD btnDel" onclick="handleDelete(' +
                    permission.id +
                    ",'deleteStockItem','tableStockItem')\" type=\"submit\">Xóa</a>\n" +
                    "                                <button\n" +
                    '                                        class="btnHD btnUpdatePermission"\n' +
                    '                                        data-permissionID="' +
                    permission.id +
                    '"\n' +
                    '                                        data-permissionName="' +
                    permission.name +
                    '"\n' +
                    '                                        data-permissionLevel="' +
                    permission.level +
                    '"\n' +
                    "                                >\n" +
                    "                                    Sửa\n" +
                    "                                </button>\n" +
                    "                            </div>\n" +
                    "                        </td>\n" +
                    "                    </tr>";
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
    let table = document.getElementById("tablePermission");

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
                    table.innerHTML +=
                        "<tr>\n" +
                        "                        <td>" +
                        permission.id +
                        "</td>\n" +
                        "                        <td>" +
                        permission.name +
                        "</td>\n" +
                        "                        <td>" +
                        permission.level +
                        "</td>\n" +
                        "                        <td>\n" +
                        '                            <div class="flex-center grpbtn">\n' +
                        '                                    <a class="btnHD btnDel" onclick="handleDelete(' +
                        permission.id +
                        ",'deleteStockItem','tableStockItem')\" type=\"submit\">Xóa</a>\n" +
                        "                                <button\n" +
                        '                                        class="btnHD btnUpdatePermission"\n' +
                        '                                        data-permissionID="' +
                        permission.id +
                        '"\n' +
                        '                                        data-permissionName="' +
                        permission.name +
                        '"\n' +
                        '                                        data-permissionLevel="' +
                        permission.level +
                        '"\n' +
                        "                                >\n" +
                        "                                    Sửa\n" +
                        "                                </button>\n" +
                        "                            </div>\n" +
                        "                        </td>\n" +
                        "                    </tr>";
                }
            } else console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

//order
function addOrder() {
    let orderCustomerID = document.getElementById("add_orderCustomerID").value;
    let orderDiscountCardID = document.getElementById("add_orderDiscountCardID").value;
    let orderTotal = document.getElementById("add_orderTotal").value;
    let orderDatePurchase = document.getElementById("add_orderDatePurchase").value;
    let orderStatus = document.getElementById("add_orderStatus").value;
    let orderAddress = document.getElementById("add_orderAddress").value;
    let orderNote = document.getElementById("add_orderNote").value;
    let table = document.getElementById("tableOrder");

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "addOrder",
            orderCustomerID: orderCustomerID,
            orderDiscountCardID: orderDiscountCardID,
            orderTotal: orderTotal,
            orderDatePurchase: orderDatePurchase,
            orderStatus: orderStatus,
            orderAddress: orderAddress,
            orderNote: orderNote,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let order = data.order;

                table.innerHTML +=
                    "<tr>\n" +
                    "    <td>" +
                    order.id +
                    "</td>\n" +
                    "    <td>" +
                    order.customer.id +
                    "</td>\n" +
                    "    <td>" +
                    order.discountCard.id +
                    "</td>\n" +
                    "    <td>" +
                    order.total +
                    "</td>\n" +
                    "    <td>" +
                    order.date_purchase +
                    "</td>\n" +
                    "    <td>" +
                    order.order_status +
                    "</td>\n" +
                    "    <td>" +
                    order.address +
                    "</td>\n" +
                    "    <td>" +
                    order.note +
                    "</td>\n" +
                    "    <td>\n" +
                    '        <div class="flex-center grpbtn">\n' +
                    '            <a class="btnHD btnDel" onclick="handleDelete(' +
                    order.id +
                    ",'deleteOrder','tableOrder')\"\n" +
                    '               type="submit">Xóa</a>\n' +
                    '            <button class="btnHD btnUpdateOrder"\n' +
                    '                    data-orderID="' +
                    order.id +
                    '"\n' +
                    '                    data-orderCustomerID="' +
                    order.customer.id +
                    '"\n' +
                    '                    data-orderTotal="' +
                    order.total +
                    '"\n' +
                    '                    data-orderDatePurchase="' +
                    order.date_purchase +
                    '"\n' +
                    '                    data-orderDiscountCardID="' +
                    order.discountCard.id +
                    '"\n' +
                    '                    data-orderStatus="' +
                    order.order_status +
                    '"\n' +
                    '                    data-orderAddress="' +
                    order.address +
                    '"\n' +
                    '                    data-orderNote="' +
                    order.note +
                    '"\n' +
                    "            >\n" +
                    "                Sửa\n" +
                    "            </button>\n" +
                    "        </div>\n" +
                    "    </td>\n" +
                    "</tr>";

                alert("Thêm thông tin hóa đơn thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function updateOrder() {
    let orderID = document.getElementById("update_orderID").value;
    let customerID = document.getElementById("update_orderCustomerID").value;
    let discountCardID = document.getElementById("update_orderDiscountCardID").value;
    let total = document.getElementById("update_orderTotal").value;
    let datePurchase = document.getElementById("update_orderDatePurchase").value;
    let status = document.getElementById("update_orderStatus").value;
    let address = document.getElementById("update_orderAddress").value;
    let note = document.getElementById("update_orderNote").value;

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "updateOrder",
            orderID: orderID,
            customerID: customerID,
            discountCardID: discountCardID,
            total: total,
            datePurchase: datePurchase,
            status: status,
            address: address,
            note: note,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let updatedOrder = data.order;

                // Update table row
                let table = document.getElementById("tableOrder");
                for (let i = 0, row; (row = table.rows[i]); i++) {
                    let idCell = row.cells[0];
                    if (idCell.textContent == updatedOrder.id) {
                        row.cells[1].textContent = updatedOrder.customer.id;
                        row.cells[2].textContent = updatedOrder.discountCard.id;
                        row.cells[3].textContent = updatedOrder.total;
                        row.cells[4].textContent = updatedOrder.date_purchase;
                        row.cells[5].textContent = updatedOrder.order_status;
                        row.cells[6].textContent = updatedOrder.address;
                        row.cells[7].textContent = updatedOrder.note;
                        break;
                    }
                }

                // Close the update form
                // document.querySelector(".clsUpdateOrder").click();

                alert("Cập nhật đơn hàng thành công");
            } else {
                console.log("WTF");
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

/*function orderToExcel() {
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
}*/

function searchAndSortOrder() {
    let orderInputSearch = document.getElementById("orderInputSearch").value;
    let orderSortType = document.getElementById("orderSortType").value;
    let orderSearchType = document.getElementById("orderSearchType").value;

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "searchAndSortPermission",
            orderInputSearch: orderInputSearch,
            orderSortType: orderSortType,
            orderSearchType: orderSearchType,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === true) {
                let table = document.getElementById("tableOrder");
                table.innerHTML = "";

                let orderList = data.orderList;
                for (let i = 0; i < orderList.length; i++) {
                    let order = orderList[i];
                    table.innerHTML +=
                        "<tr>\n" +
                        "    <td>" +
                        order.id +
                        "</td>\n" +
                        "    <td>" +
                        order.customer.id +
                        "</td>\n" +
                        "    <td>" +
                        order.discountCard.id +
                        "</td>\n" +
                        "    <td>" +
                        order.total +
                        "</td>\n" +
                        "    <td>" +
                        order.date_purchase +
                        "</td>\n" +
                        "    <td>" +
                        order.order_status +
                        "</td>\n" +
                        "    <td>" +
                        order.address +
                        "</td>\n" +
                        "    <td>" +
                        order.note +
                        "</td>\n" +
                        "    <td>\n" +
                        '        <div class="flex-center grpbtn">\n' +
                        '            <a class="btnHD btnDel" onclick="handleDelete(' +
                        order.id +
                        ",'deleteOrder','tableOrder')\"\n" +
                        '               type="submit">Xóa</a>\n' +
                        '            <button class="btnHD btnUpdateOrder"\n' +
                        '                    data-orderID="' +
                        order.id +
                        '"\n' +
                        '                    data-orderCustomerID="' +
                        order.customer.id +
                        '"\n' +
                        '                    data-orderTotal="' +
                        order.total +
                        '"\n' +
                        '                    data-orderDatePurchase="' +
                        order.date_purchase +
                        '"\n' +
                        '                    data-orderDiscountCardID="' +
                        order.discountCard.id +
                        '"\n' +
                        '                    data-orderStatus="' +
                        order.order_status +
                        '"\n' +
                        '                    data-orderAddress="' +
                        order.address +
                        '"\n' +
                        '                    data-orderNote="' +
                        order.note +
                        '"\n' +
                        "            >\n" +
                        "                Sửa\n" +
                        "            </button>\n" +
                        "        </div>\n" +
                        "    </td>\n" +
                        "</tr>";
                }
            } else console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

