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


/*Item*/
function handleAddItem() {
    var addProducts = document.getElementById("item_addTrigger");
    var modal = document.getElementById("add-products");
    var btnClose = document.querySelector(".clsaddproduct");
    addProducts.addEventListener("click", function () {
        modal.classList.add("active");
        console.log(1);
    });
    btnClose.addEventListener("click", function () {
        modal.classList.remove("active");
    });
}

handleAddItem();

function handleUpdateItem() {
    var updateItem = document.querySelectorAll(".btnUpdateItem");
    var modal = document.getElementById("update-products");
    var btnClose = document.querySelector(".clsupdateproduct");
    console.log(btnClose);

    updateItem.forEach(function (item) {
        item.addEventListener("click", () => {
            modal.style.display = "block";

            var itemID = item.getAttribute("data-itemID");
            var itemName = item.getAttribute("data-itemName");
            var itemTypeID = item.getAttribute("data-itemItemTypeID");
            var itemCollectionID = item.getAttribute("data-itemItemCollectionID");
            var itemMaterialID = item.getAttribute("data-itemItemMaterialID");
            var isNew = item.getAttribute("data-itemIsNew");
            var isHot = item.getAttribute("data-itemIsHot");
            var price = item.getAttribute("data-itemPrice");
            var yearProduce = item.getAttribute("data-itemYearProduce");
            var description = item.getAttribute("data-itemDescription");

            document.getElementById("update_itemID").value = itemID;
            document.getElementById("update_itemName").value = itemName;
            document.getElementById("update_itemIsNew").checked = isNew === "1";
            document.getElementById("update_itemIsHot").checked = isHot === "1";
            document.getElementById("update_itemPrice").value = price;
            document.getElementById("update_itemYearProduce").value = yearProduce;
            document.getElementById("update_itemDescription").value=description;

            //          Combobox ItemType
            var update_itemType_selectElement = document.getElementById(
                "update_itemItemTypeID"
            );
            for (var i = 0; i < update_itemType_selectElement.options.length; i++) {
                var option = update_itemType_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === itemTypeID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }

            //          Combobox ItemCollection
            var update_itemCollection_selectElement = document.getElementById(
                "update_itemItemCollectionID"
            );
            for (var i = 0; i < update_itemCollection_selectElement.options.length; i++) {
                var option = update_itemCollection_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === itemCollectionID) {
                    // Đánh dấu tùy chọn là "selected"
                    option.selected = true;
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
                }
            }

            //            Combobox ItemMaterial
            var update_itemMaterial_selectElement = document.getElementById(
                "update_itemItemMaterialID"
            );
            for (var i = 0; i < update_itemMaterial_selectElement.options.length; i++) {
                var option = update_itemMaterial_selectElement.options[i];
                // So sánh giá trị tùy chọn với giá trị cần chọn
                if (option.value === itemMaterialID) {
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

handleUpdateItem();


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


function addItem() {

    let add_itemName=document.getElementById("add_itemName").value;
    let add_itemItemTypeID=document.getElementById("add_itemItemTypeID").value;
    let add_itemItemCollectionID=document.getElementById("add_itemItemCollectionID").value;
    let add_itemItemMaterialID=document.getElementById("add_itemItemMaterialID").value;
    let add_itemIsNew=document.getElementById("add_itemIsNew").checked ? 1:0 ;
    let add_itemIsHot=document.getElementById("add_itemIsHot").checked ? 1:0;
    let add_itemPrice=document.getElementById("add_itemPrice").value;
    let add_itemYearProduce=document.getElementById("add_itemYearProduce").value;
    let add_itemDescription=document.getElementById("add_itemDescription").value;

    if (add_itemName == null || add_itemName == "" || add_itemPrice == null || add_itemPrice == "" || add_itemYearProduce==null
    || add_itemYearProduce == "" || add_itemDescription == null || add_itemDescription == "") {
        alert("Không thể để dữ liệu trống");
    } else {
        var num = parseFloat(add_itemYearProduce);
        if(Number.isInteger(num)){
            $.ajax({
                type: "POST",
                url: adminManagerContextPath + "/ManageItemServlet",
                data: {
                    action: "addItem",
                    add_itemName:add_itemName,
                    add_itemItemTypeID:add_itemItemTypeID,
                    add_itemItemCollectionID:add_itemItemCollectionID,
                    add_itemItemMaterialID:add_itemItemMaterialID,
                    add_itemIsHot:add_itemIsHot,
                    add_itemIsNew:add_itemIsNew,
                    add_itemPrice:add_itemPrice,
                    add_itemYearProduce:add_itemYearProduce,
                    add_itemDescription:add_itemDescription,
                }, headers: {
                    "X-Requested-With": "XMLHttpRequest",
                },
                success: function (data) {

                    data = JSON.parse(data);
                    if (data.success === true) {

                        alert("Thêm sản phẩm thành công");
                        location.href = adminManagerContextPath + "/ManageItemServlet";

                    } else {
                        alert("Có lỗi xảy trong hệ thống");
                    }
                },
                error: function (error) {
                    console.log("error :>> ", error);
                },
            });
        }else{
            alert("Kiểu dữ liệu của năm sản xuất không hợp lệ");
        }
    }
}

function deleteItem(itemID) {
    // Trả về true nếu người dùng đã xác nhận, ngược lại trả về false
    if (confirm('Bạn có chắc chắn xóa không?')) {
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/ManageItemServlet",
            data: {
                action: "deleteItem",
                itemID: itemID,
            },
            success: function (data) {
                console.log(data);
                data = JSON.parse(data);
                if (data.success === true) {
                    alert("Xóa sản phẩm thành công");
                    location.href = adminManagerContextPath + "/ManageItemServlet";

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

function updateItem() {
    let update_itemID=document.getElementById("update_itemID").value;
    let update_itemName=document.getElementById("update_itemName").value;
    let update_itemItemTypeID=document.getElementById("update_itemItemTypeID").value;
    let update_itemItemCollectionID=document.getElementById("update_itemItemCollectionID").value;
    let update_itemItemMaterialID=document.getElementById("update_itemItemMaterialID").value;
    let update_itemIsNew=document.getElementById("update_itemIsNew").checked ? 1:0 ;
    let update_itemIsHot=document.getElementById("update_itemIsHot").checked ? 1:0;
    let update_itemPrice=document.getElementById("update_itemPrice").value;
    let update_itemYearProduce=document.getElementById("update_itemYearProduce").value;
    let update_itemDescription=document.getElementById("update_itemDescription").value;

    if (update_itemName == null || update_itemName == "" || update_itemPrice == null || update_itemPrice == "" || update_itemYearProduce==null
        || update_itemYearProduce == "" || update_itemDescription == null || update_itemDescription == "") {
        alert("Không thể để dữ liệu trống");
    } else {
        var num = parseFloat(update_itemYearProduce);
        if(Number.isInteger(num)){
            $.ajax({
                type: "POST",
                url: adminManagerContextPath + "/ManageItemServlet",
                data: {
                    action: "updateItem",
                    update_itemID: update_itemID,
                    update_itemName:update_itemName,
                    update_itemItemTypeID:update_itemItemTypeID,
                    update_itemItemCollectionID:update_itemItemCollectionID,
                    update_itemItemMaterialID:update_itemItemMaterialID,
                    update_itemIsHot:update_itemIsHot,
                    update_itemIsNew:update_itemIsNew,
                    update_itemPrice:update_itemPrice,
                    update_itemYearProduce:update_itemYearProduce,
                    update_itemDescription:update_itemDescription,
                }, headers: {
                    "X-Requested-With": "XMLHttpRequest",
                },
                success: function (data) {

                    data = JSON.parse(data);
                    if (data.success === true) {

                        alert("Cập nhật sản phẩm thành công");
                        refreshItem();

                    } else {
                        alert("Có lỗi xảy trong hệ thống");
                    }
                },
                error: function (error) {
                    console.log("error :>> ", error);
                },
            });
        }else{
            alert("Kiểu dữ liệu của năm sản xuất không hợp lệ");
        }

    }
}

function searchAndSortItem() {
    let itemSearchType = document.getElementById("itemSearchType").value;
    let itemInputSearch = document.getElementById("itemInputSearch").value;
    let itemSortType = document.getElementById("itemSortType").value;

    if (itemSearchType == null || itemSearchType == "" || itemInputSearch == null || itemInputSearch == "" ||
        itemSortType == null || itemSortType == "") {
        alert("Không thể để dữ liệu trống");
        return false;
    }

    if (itemSearchType == "id") {
        var num = parseFloat(itemInputSearch);
        if (Number.isInteger(num)) {
            return true;
        } else {
            alert("Dữ liệu điền vào không hợp lệ");
            return false;
        }
    }else if(itemSearchType == "lowerPrice" || itemSearchType == "higherPrice"){
        if(isNaN(itemInputSearch)){
            return true;
        }else{
            alert("Dữ liệu điền vào không hợp lệ");
            return false;
        }
    }
    return true;
}

function refreshItem() {
    location.href = adminManagerContextPath + "/ManageItemServlet";
}

function itemToExcel() {
    let table = document.getElementById("tableItem");

    let dataToSend = [];

    // Loop through the rows of the table and gather information from each column cell
    for (let i = 0; i < table.rows.length; i++) {
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
        let description=currentRow.cells[9].textContent;

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
            description:description,
        };

        // Add the object to the dataToSend array
        dataToSend.push(productData);
    }

    const workbook = XLSX.utils.book_new();
    const sheet = XLSX.utils.json_to_sheet(dataToSend);
    XLSX.utils.book_append_sheet(workbook, sheet, "Products");

    XLSX.writeFile(workbook, "products.xlsx");
}