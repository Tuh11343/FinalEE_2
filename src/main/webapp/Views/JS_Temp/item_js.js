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

      var itemID=item.getAttribute("data-itemID");
      var itemName = item.getAttribute("data-itemName");
      var itemTypeID = item.getAttribute("data-itemTypeID");
      var itemCollectionID = item.getAttribute("data-itemCollectionID");
      var itemMaterialID = item.getAttribute("data-itemMaterialID");
      var isNew = item.getAttribute("data-itemIsNew");
      var isHot = item.getAttribute("data-itemIsHot");
      var price = item.getAttribute("data-itemPrice");
      var yearProduce = item.getAttribute("data-itemYearProduce");

      document.getElementById("update_itemID").value=itemID;
      document.getElementById("update_itemNameID").value = itemName;
      document.getElementById("update_itemIsNewID").checked = isNew === "1";
      document.getElementById("update_itemIsHotID").checked = isHot === "1";
      document.getElementById("update_itemPriceID").value = price;
      document.getElementById("update_itemYearProduceID").value = yearProduce;

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