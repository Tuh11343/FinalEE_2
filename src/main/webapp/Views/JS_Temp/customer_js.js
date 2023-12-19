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



/*Customer*/
function handleAddCustomer() {
  var addCustomer = document.getElementById("customer_addTrigger");
  var modal = document.getElementById("add-customer");
  var btnClose = document.querySelector(".clsAddCustomer");
  addCustomer.addEventListener("click", function () {
    modal.classList.add("active");
    console.log(1);
  });
  btnClose.addEventListener("click", function () {
    modal.classList.remove("active");
  });
}
handleAddCustomer();

function handleUpdateCustomer() {
  var updateCustomer = document.querySelectorAll(".btnUpdateCustomer");
  var modal = document.getElementById("update-customers");
  var btnClose = document.querySelector(".clsUpdateCustomer");

  updateCustomer.forEach(function (item) {
    item.addEventListener("click", () => {
      modal.style.display = "block";

      var id=item.getAttribute("data-customerID");
      var name = item.getAttribute("data-customerName");
      var phoneNumber = item.getAttribute("data-customerPhoneNumber");
      var email = item.getAttribute("data-customerEmail");
      var address = item.getAttribute("data-customerAddress");

      console.log(name + phoneNumber + email + address);

      document.getElementById("update_customerID").value=id;
      document.getElementById("update_customerName").value = name;
      document.getElementById("update_customerPhoneNumber").value = phoneNumber;
      document.getElementById("update_customerEmail").value = email;
      document.getElementById("update_customerAddress").value = address;
    });
  });
  btnClose.addEventListener("click", function () {
    modal.style.display = "none";
  });
}
handleUpdateCustomer();


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

function addCustomer() {

  let add_customerName = document.getElementById("add_customerName").value;
  let add_customerPhoneNumber = document.getElementById("add_customerPhoneNumber").value;
  let add_customerEmail = document.getElementById("add_customerEmail").value;
  let add_customerAddress = document.getElementById("add_customerAddress").value;

  if (add_customerName == null || add_customerName == "" || add_customerPhoneNumber == null || add_customerPhoneNumber == ""
      || add_customerEmail == null || add_customerEmail == "" || add_customerAddress == null || add_customerAddress == "") {
    alert("Không thể để dữ liệu trống");
  } else {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageCustomerServlet",
      data: {
        action: "addCustomer",
        add_customerName: add_customerName,
        add_customerPhoneNumber: add_customerPhoneNumber,
        add_customerEmail: add_customerEmail,
        add_customerAddress: add_customerAddress,
      }, headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
      success: function (data) {

        data = JSON.parse(data);
        if (data.success === true) {

          alert("Thêm khách hàng thành công");
          location.href = adminManagerContextPath + "/ManageCustomerServlet";

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

function deleteCustomer(customerID) {
  // Trả về true nếu người dùng đã xác nhận, ngược lại trả về false
  if (confirm('Bạn có chắc chắn xóa không?')) {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageCustomerServlet",
      data: {
        action: "deleteCustomer",
        customerID: customerID,
      },
      success: function (data) {
        data = JSON.parse(data);
        if (data.success === true) {
          alert("Xóa khách hàng thành công");
          location.href = adminManagerContextPath + "/ManageCustomerServlet";

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

function updateCustomer(){
  let update_customerID = document.getElementById("update_customerID").value;
  let update_customerName = document.getElementById("update_customerName").value;
  let update_customerPhoneNumber = document.getElementById("update_customerPhoneNumber").value;
  let update_customerEmail = document.getElementById("update_customerEmail").value;
  let update_customerAddress = document.getElementById("update_customerAddress").value;

  if (update_customerName == null || update_customerName == "" || update_customerPhoneNumber == null || update_customerPhoneNumber == ""
      || update_customerEmail == null || update_customerEmail == "" || update_customerAddress == null || update_customerAddress == "") {
    alert("Không thể để dữ liệu trống");
  }else {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageCustomerServlet",
      data: {
        action: "updateCustomer",
        update_customerID:update_customerID,
        update_customerName: update_customerName,
        update_customerPhoneNumber: update_customerPhoneNumber,
        update_customerEmail: update_customerEmail,
        update_customerAddress: update_customerAddress,
      }, headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
      success: function (data) {

        data = JSON.parse(data);
        if (data.success === true) {

          alert("Cập nhật khách hàng thành công");
          refreshCustomer();

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

function searchAndSortCustomer(){
  let customerSearchType=document.getElementById("customerSearchType").value;
  let customerInputSearch=document.getElementById("customerInputSearch").value;
  let customerSortType= document.getElementById("customerSortType").value;

  if (customerSearchType == null || customerSearchType == "" || customerInputSearch == null || customerInputSearch == "" ||
      customerSortType == null || customerSortType == "") {
    alert("Không thể để dữ liệu trống");
    return false;
  }
     if (customerSortType == "id") {
          var num = parseFloat(customerInputSearch);
          if (Number.isInteger(num)) {
               return true;
          } else {
              alert("Dữ liệu điền vào không hợp lệ");
              return false;
          }
      }
    return true;

}

function refreshCustomer(){
  location.href=adminManagerContextPath+"/ManageCustomerServlet";
}