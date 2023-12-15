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


/*StockItem*/
function handleAddStockItem() {
  var addStockItem = document.getElementById("stockItem_addTrigger");
  var modal = document.getElementById("add-stockItem");
  var btnClose = document.querySelector(".clsAddStockItem");
  addStockItem.addEventListener("click", function () {
    modal.classList.add("active");
    console.log(1);
  });
  btnClose.addEventListener("click", function () {
    modal.classList.remove("active");
  });
}
handleAddStockItem();

function handleUpdateStockItem() {
  var updateStockItem = document.querySelectorAll(".btnUpdateStockItem");
  var modal = document.getElementById("update-stockItem");
  var btnClose = document.querySelector(".clsUpdateStockItem");

  updateStockItem.forEach(function (item) {
    item.addEventListener("click", () => {
      modal.style.display = "block";

      var id = item.getAttribute("data-stockItemID");
      var itemID = item.getAttribute("data-stockItemItemID");
      var color = item.getAttribute("data-stockItemColor");
      var size = item.getAttribute("data-stockItemSize");
      var amount = item.getAttribute("data-stockItemAmount");

      document.getElementById("update_stockItemID").value=id;
      document.getElementById("update_customerID").value= id;
      document.getElementById("update_stockItemColor").value = color;
      document.getElementById("update_stockItemSize").value = size;
      document.getElementById("update_stockItemAmount").value = amount;

      /*Combobox ItemID*/
      var update_stockItem_selectElement = document.getElementById(
          "update_stockItemItemID"
      );
      for (var i = 0; i < update_stockItem_selectElement.options.length; i++) {
        var option = update_stockItem_selectElement.options[i];
        // So sánh giá trị tùy chọn với giá trị cần chọn
        if (option.value === itemID) {
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
handleUpdateStockItem();


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