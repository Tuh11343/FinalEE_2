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



/*Item Collection*/
function handleAddCart() {
  var addCart = document.getElementById("cart_addTrigger");
  var modal = document.getElementById("add-cart");
  var btnClose = document.querySelector(".clsAddCart");
  addCart.addEventListener("click", function () {
    modal.classList.add("active");
    console.log(1);
  });
  btnClose.addEventListener("click", function () {
    modal.classList.remove("active");
  });
}
handleAddCart();

function handleUpdateCart() {
  var updateCart = document.querySelectorAll(".btnUpdateCart");
  var modal = document.getElementById("update-cart");
  var btnClose = document.querySelector(".clsUpdateCart");

  updateCart.forEach(function (item) {
    item.addEventListener("click", () => {
      modal.style.display = "block";

      var id=item.getAttribute("data-cartID");
      var stockItemID = item.getAttribute("data-stockItemID");
      var customerID = item.getAttribute("data-customerID");
      var amount = item.getAttribute("data-amount");

      document.getElementById("update_cartID").value=id;
      document.getElementById("update_cartStockItemID").value = stockItemID;
      document.getElementById("update_cartCustomerID").value = customerID;
      document.getElementById("update_cartAmount").value = amount;
    });
  });
  btnClose.addEventListener("click", function () {
    modal.style.display = "none";
  });
}
handleUpdateCart();


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

function addCart() {

  let add_cartStockItemID = document.getElementById("add_cartStockItemID").value;
  let add_cartCustomerID = document.getElementById("add_cartCustomerID").value;
  let add_cartAmount = document.getElementById("add_Amount").value;

  if (add_cartStockItemID == null || add_cartStockItemID == ""||add_cartCustomerID == null || add_cartCustomerID == ""||add_cartAmount == null || add_cartAmount == "") {
    alert("Không thể để dữ liệu trống");
  } else {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageCartServlet",
      data: {
        action: "addCart",
        add_cartStockItemID:add_cartStockItemID,
        add_cartCustomerID:add_cartCustomerID,
        add_cartAmount:add_cartAmount,
      }, headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
      success: function (data) {

        data = JSON.parse(data);
        if (data.success === true) {

          alert("Thêm giỏ hàng thành công");
          location.href = adminManagerContextPath + "/ManageCartServlet";

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

function deleteCart(cartID) {
  // Trả về true nếu người dùng đã xác nhận, ngược lại trả về false
  if (confirm('Bạn có chắc chắn xóa không?')) {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageCartServlet",
      data: {
        action: "deleteCart",
        cartID: cartID,
      },
      success: function (data) {
        data = JSON.parse(data);
        if (data.success === true) {
          alert("Xóa giỏ hàng thành công");
          location.href = adminManagerContextPath + "/ManageCartServlet";

        } else {
          alert("Có lỗi xảy trong hệ thống");
        }
      },
      error: function (error) {
        console.log("error :>> ", error);
      },
    });
  } else {
  }
}

function updateCart() {
  let update_cartID=document.getElementById("update_cartID").value;
  let update_cartStockItemID=document.getElementById("update_cartStockItemID").value ;
  let update_cartCustomerID=document.getElementById("update_cartCustomerID").value ;
  let update_cartAmountID=document.getElementById("update_cartAmount").value;

  if (update_cartID==null||update_cartID==""||update_cartStockItemID == null || update_cartStockItemID == ""||update_cartCustomerID == null || update_cartCustomerID == ""||update_cartAmount == null || update_cartAmount == "") {
    alert("Không thể để dữ liệu trống");
  }  else {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageCartServlet",
      data: {
        action: "updateCart",
        update_cartStockItemID:update_cartStockItemID,
        update_cartCustomerID:update_cartCustomerID,
        update_cartAmount:update_cartAmount,
      }, headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
      success: function (data) {

        data = JSON.parse(data);
        if (data.success === true) {

          alert("Cập nhật giỏ hàng thành công");
          refreshItemCollection();

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

function searchAndSortCart() {
  let cartSearchType = document.getElementById("cartSearchType").value;
  let cartInputSearch = document.getElementById("cartInputSearch").value;
  let cartSortType = document.getElementById("cartSortType").value;

  if (cartSearchType == null || cartSearchType == "" || cartInputSearch == null || cartInputSearch == "" ||
      cartSortType == null || cartSortType == "") {
    alert("Không thể để dữ liệu trống");
    return false;
  } else {
    return true;
  }
}

function refreshCart() {
  location.href = adminManagerContextPath + "/ManageCartServlet";
}

function cartToExcel() {
  let table = document.getElementById("tableCart");

  let dataToSend = [];

  // Lặp qua các dòng của bảng và thu thập thông tin từ mỗi ô cột
  for (let i = 0; i < table.rows.length; i++) {
    let currentRow = table.rows[i];

    // Thu thập thông tin từ các ô cột trong dòng
    let id = currentRow.cells[0].textContent;
    let stockItemID = currentRow.cells[1].textContent;
    let customerID = currentRow.cells[2].textContent;
    let amount = currentRow.cells[3].textContent;

    // Tạo đối tượng chứa thông tin từ dòng hiện tại
    let cartData = {
      id: id,
      stockItemID:stockItemID,

    };

    // Thêm đối tượng vào mảng dataToSend
    dataToSend.push(cartData);
  }

  const workbook = XLSX.utils.book_new();
  const sheet = XLSX.utils.json_to_sheet(dataToSend);
  XLSX.utils.book_append_sheet(workbook, sheet, "Cart");

  XLSX.writeFile(workbook, "CartProduct.xlsx");
}