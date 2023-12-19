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



/*Sale*/
function handleAddSale() {
  var addSale = document.getElementById("sale_addTrigger");
  var modal = document.getElementById("add-sale");
  var btnClose = document.querySelector(".clsAddSale");
  addSale.addEventListener("click", function () {
    modal.classList.add("active");
    console.log(1);
  });
  btnClose.addEventListener("click", function () {
    modal.classList.remove("active");
  });
}
handleAddSale();

function handleUpdateSale() {
  var updateSale = document.querySelectorAll(".btnUpdateSale");
  var modal = document.getElementById("update-sale");
  var btnClose = document.querySelector(".clsUpdateSale");

  updateSale.forEach(function (item) {
    item.addEventListener("click", () => {
      modal.style.display = "block";

      var id=item.getAttribute("data-saleID");
      var itemID = item.getAttribute("data-saleItemID");
      var name = item.getAttribute("data-saleName");
      var onSale = item.getAttribute("data-saleOnSale");
      var salePercentage = item.getAttribute("data-salePercentage");

      document.getElementById("update_saleID").value=id;
      document.getElementById("update_saleName").value = name;
      document.getElementById("update_saleOnSale").checked = onSale === "1";
      document.getElementById("update_salePercentage").value = salePercentage;

      /*Combobox ItemID*/
      var update_sale_selectElement = document.getElementById("update_saleItemID");
      for (var i = 0; i < update_sale_selectElement.options.length; i++) {
        var option = update_sale_selectElement.options[i];
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
handleUpdateSale();


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

function addSale() {

  let add_saleName = document.getElementById("add_saleName").value;
  let add_salePercentage=document.getElementById("add_salePercentage").value;
  let add_saleOnSale=document.getElementById("add_saleOnSale").checked ? 1:0;
  let add_saleItemID=document.getElementById("add_saleItemID").value;

  if (add_saleName == null || add_saleName == "" || add_salePercentage == null || add_salePercentage == "" ||
  add_saleOnSale == null || add_saleOnSale == "" || add_saleItemID == null || add_saleItemID == "") {
    alert("Không thể để dữ liệu trống");
  } else {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageSaleServlet",
      data: {
        action: "addSale",
        add_saleName:add_saleName,
        add_salePercentage:add_salePercentage,
        add_saleOnSale:add_saleOnSale,
        add_saleItemID:add_saleItemID,
      }, headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
      success: function (data) {

        data = JSON.parse(data);
        if (data.success === true) {

          alert("Thêm sản phẩm giảm giá thành công");
          location.href = adminManagerContextPath + "/ManageSaleServlet";

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

function deleteSale(saleID) {
  // Trả về true nếu người dùng đã xác nhận, ngược lại trả về false
  if (confirm('Bạn có chắc chắn xóa không?')) {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageSaleServlet",
      data: {
        action: "deleteSale",
        saleID: saleID,
      },
      success: function (data) {
        data = JSON.parse(data);
        if (data.success === true) {
          alert("Xóa giảm giá sản phẩm thành công");
          location.href = adminManagerContextPath + "/ManageSaleServlet";

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

function updateSale() {
  let update_saleID=document.getElementById("update_saleID").value;
  let update_saleName = document.getElementById("update_saleName").value;
  let update_salePercentage=document.getElementById("update_salePercentage").value;
  let update_saleOnSale=document.getElementById("update_saleOnSale").checked ? 1:0;
  let update_saleItemID=document.getElementById("update_saleItemID").value;

  if (update_saleName == null || update_saleName == "" || update_salePercentage == null || update_salePercentage == "" ||
      update_saleOnSale == null || update_saleOnSale == "" || update_saleItemID == null || update_saleItemID == "") {
    alert("Không thể để dữ liệu trống");
  } else {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageSaleServlet",
      data: {
        action: "updateSale",
        update_sale:update_saleID,
        update_saleName:update_saleName,
        update_salePercentage:update_salePercentage,
        update_saleOnSale:update_saleOnSale,
        update_saleItemID:update_saleItemID,
      }, headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
      success: function (data) {

        data = JSON.parse(data);
        if (data.success === true) {

          alert("Cập nhật giảm giá sản phẩm thành công");
          refreshSale();

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

function searchAndSortSale() {
  let saleSearchType = document.getElementById("saleSearchType").value;
  let saleInputSearch = document.getElementById("saleInputSearch").value;
  let saleSortType = document.getElementById("saleSortType").value;

  if (saleSearchType == null || saleSearchType == "" || saleInputSearch == null || saleInputSearch == "" ||
      saleSortType == null || saleSortType == "") {
    alert("Không thể để dữ liệu trống");
    return false;
  }


    return true;

}

function refreshSale() {
  location.href = adminManagerContextPath + "/ManageSaleServlet";
}