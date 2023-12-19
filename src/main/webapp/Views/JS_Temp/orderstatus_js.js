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


/*Order Status*/
function handleAddOrderStatus() {
  var addOrderStatus = document.getElementById("orderStatus_addTrigger");
  var modal = document.getElementById("add-orderStatus");
  var btnClose = document.querySelector(".clsAddOrderStatus");
  addOrderStatus.addEventListener("click", function () {
    modal.classList.add("active");
    console.log(1);
  });
  btnClose.addEventListener("click", function () {
    modal.classList.remove("active");
  });
}
handleAddOrderStatus();
function handleUpdateOrderStatus() {
  var updateOrderStatus = document.querySelectorAll(".btnUpdateOrderStatus");
  var modal = document.getElementById("update-orderStatus");
  var btnClose = document.querySelector(".clsOrderStatus");

  updateOrderStatus.forEach(function (item) {
    item.addEventListener("click", () => {
      modal.style.display = "block";

      var id=item.getAttribute("data-orderStatusID");
      var name = item.getAttribute("data-orderStatusName");

      document.getElementById("update_orderStatusID").value=id;
      document.getElementById("update_orderStatusName").value = name;
    });
  });
  btnClose.addEventListener("click", function () {
    modal.style.display = "none";
  });
}
handleUpdateOrderStatus();


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

function addOrderStatus() {

  let add_orderStatusName = document.getElementById("add_orderStatusName").value;

  if (add_orderStatusName == null || add_orderStatusName == "") {
    alert("Không thể để dữ liệu trống");
  } else {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageOrderStatusServlet",
      data: {
        action: "addOrderStatus",
        add_orderStatusName:add_orderStatusName,
      }, headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
      success: function (data) {

        data = JSON.parse(data);
        if (data.success === true) {

          alert("Thêm tình trạng đơn hàng thành công");
          location.href = adminManagerContextPath + "/ManageOrderStatusServlet";

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

function deleteOrderStatus(orderStatusID) {
  // Trả về true nếu người dùng đã xác nhận, ngược lại trả về false
  if (confirm('Bạn có chắc chắn xóa không?')) {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageOrderStatusServlet",
      data: {
        action: "deleteOrderStatus",
        orderStatusID: orderStatusID,
      },
      success: function (data) {
        data = JSON.parse(data);
        if (data.success === true) {
          alert("Xóa tình trạng đơn hàng thành công");
          location.href = adminManagerContextPath + "/ManageOrderStatusServlet";

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

function updateOrderStatus() {
  let update_orderStatusID=document.getElementById("update_orderStatusID").value;
  let update_orderStatusName=document.getElementById("update_orderStatusName").value;

  if (update_orderStatusName == null || update_orderStatusName == "") {
    alert("Không thể để dữ liệu trống");
  }  else {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageOrderStatusServlet",
      data: {
        action: "updateOrderStatus",
        update_orderStatusID:update_orderStatusID,
        update_orderStatusName:update_orderStatusName,
      }, headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
      success: function (data) {

        data = JSON.parse(data);
        if (data.success === true) {

          alert("Cập nhật tình trạng đơn hàng thành công");
          refreshOrderStatus();

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

function searchAndSortOrderStatus() {
  let orderStatusSearchType = document.getElementById("orderStatusSearchType").value;
  let orderStatusInputSearch = document.getElementById("orderStatusInputSearch").value;
  let orderStatusSortType = document.getElementById("orderStatusSortType").value;

  if (orderStatusSearchType == null || orderStatusSearchType == "" || orderStatusInputSearch == null || orderStatusInputSearch == "" ||
      orderStatusSortType == null || orderStatusSortType == "") {
    alert("Không thể để dữ liệu trống");
    return false;
  }
     if (orderStatusSortType == "id") {
          var num = parseFloat(orderStatusInputSearch);
          if (Number.isInteger(num)) {
               return true;
          } else {
              alert("Dữ liệu điền vào không hợp lệ");
              return false;
          }
      }
    return true;

}

function refreshOrderStatus() {
  location.href = adminManagerContextPath + "/ManageOrderStatusServlet";
}