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

function addStockItem() {

  let add_stockItemColor=document.getElementById("add_stockItemColor").value;
  let add_stockItemSize=document.getElementById("add_stockItemSize").value;
  let add_stockItemAmount=document.getElementById("add_stockItemAmount").value;
  let add_stockItemItemID=document.getElementById("add_stockItemItemID").value;

  if (add_stockItemItemID==null || add_stockItemItemID == "" || add_stockItemSize==null || add_stockItemSize == ""
  || add_stockItemAmount==null || add_stockItemAmount == "" || add_stockItemColor == null || add_stockItemColor == "") {
    alert("Không thể để dữ liệu trống");
  } else {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageStockItemServlet",
      data: {
        action: "addStockItem",
        add_stockItemItemID:add_stockItemColor,
        add_stockItemSize:add_stockItemSize,
        add_stockItemColor:add_stockItemColor,
        add_stockItemAmount:add_stockItemAmount,
      }, headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
      success: function (data) {

        data = JSON.parse(data);
        if (data.success === true) {

          alert("Thêm thông tin sản phẩm thành công");
          location.href = adminManagerContextPath + "/ManageStockItemServlet";

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

function deleteStockItem(stockItemID) {
  // Trả về true nếu người dùng đã xác nhận, ngược lại trả về false
  if (confirm('Bạn có chắc chắn xóa không?')) {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageStockItemServlet",
      data: {
        action: "deleteStockItem",
        stockItemID: stockItemID,
      },
      success: function (data) {
        data = JSON.parse(data);
        if (data.success === true) {
          alert("Xóa thông tin sản phẩm thành công");
          location.href = adminManagerContextPath + "/ManageStockItemServlet";

        } else {
          alert("Có lỗi xảy trong hệ thống");
        }
      },
      error: function (error) {
        console.log("error :>> ", error);
      },
    });
  } else {
    alert("a")
  }
}

function updateStockItem() {
  let update_stockItemID= document.getElementById("update_stockItemID").value;
  let update_stockItemColor=document.getElementById("update_stockItemColor").value;
  let update_stockItemSize=document.getElementById("update_stockItemSize").value;
  let update_stockItemAmount=document.getElementById("update_stockItemAmount").value;
  let update_stockItemItemID=document.getElementById("update_stockItemItemID").value;

  if (update_stockItemItemID==null || update_stockItemItemID == "" || update_stockItemSize==null || update_stockItemSize == ""
      || update_stockItemAmount==null || update_stockItemAmount == "" || update_stockItemColor == null || update_stockItemColor == "") {
    alert("Không thể để dữ liệu trống");
  } else {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageStockItemServlet",
      data: {
        action: "updateStockItem",
        update_stockItemID:update_stockItemID,
        update_stockItemItemID:update_stockItemColor,
        update_stockItemSize:update_stockItemSize,
        update_stockItemColor:update_stockItemColor,
        update_stockItemAmount:update_stockItemAmount,
      }, headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
      success: function (data) {

        data = JSON.parse(data);
        if (data.success === true) {

          alert("Cập nhật thông tin sản phẩm thành công");
          refreshStockItem();

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

function searchAndSortStockItem() {
  let stockItemSearchType = document.getElementById("stockItemSearchType").value;
  let stockItemInputSearch = document.getElementById("stockItemInputSearch").value;
  let stockItemSortType = document.getElementById("stockItemSortType").value;

  if (stockItemSearchType == null || stockItemSearchType == "" || stockItemInputSearch == null || stockItemInputSearch == "" ||
      stockItemSortType == null || stockItemSortType == "") {
    alert("Không thể để dữ liệu trống");
    return false;
  } else {
    return true;
  }
}

function refreshStockItem() {
  location.href = adminManagerContextPath + "/ManageStockItemServlet";
}