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



/*Account*/
function handleAddAccount() {
  var addUser = document.getElementById("account_addTrigger");
  var modal = document.getElementById("modal-add-account");
  var btnClose = document.querySelector(".clsadduser");
  addUser.addEventListener("click", function () {
    modal.classList.add("active");
  });
  btnClose.addEventListener("click", function () {
    modal.classList.remove("active");
  });
}
handleAddAccount();

function handleUpdateAccount() {
  var updateUser = document.querySelectorAll(".btnUpdateUser");
  var modal = document.getElementById("update-user");

  updateUser.forEach(function (item) {
    item.addEventListener("click", () => {
      modal.style.display = "block";

      var accountID=item.getAttribute("data-accountID");
      var accountCustomerID = item.getAttribute("data-accountCustomerID");
      var accountPermissionID = item.getAttribute("data-accountPermissionID");
      var accountName = item.getAttribute("data-accountName");
      var accountPass = item.getAttribute("data-accountPassword");


      document.getElementById("update_accountID").value=accountID;
      document.getElementById("update_accountName").value = accountName;
      document.getElementById("update_accountPassword").value = accountPass;

      var update_customer_selectElement = document.getElementById(
          "update_accountCustomerID"
      );
      for (var i = 0; i < update_customer_selectElement.options.length; i++) {
        var option = update_customer_selectElement.options[i];
        // So sánh giá trị tùy chọn với giá trị cần chọn
        if (option.value === accountCustomerID) {
          // Đánh dấu tùy chọn là "selected"
          option.selected = true;
          break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
        }
      }

      var update_permission_selectElement = document.getElementById(
          "update_accountPermissionID"
      );
      for (var i = 0; i < update_permission_selectElement.options.length; i++) {
        var option = update_permission_selectElement.options[i];
        // So sánh giá trị tùy chọn với giá trị cần chọn
        if (option.value === accountPermissionID) {
          // Đánh dấu tùy chọn là "selected"
          option.selected = true;
          break; // Thoát khỏi vòng lặp sau khi tìm thấy giá trị cần chọn
        }
      }
    });
  });
  function closeModal() {
    modal.style.display = "none";
  }
  var closeBtn = document.getElementsByClassName("close")[0];
  closeBtn.addEventListener("click", closeModal);
}
handleUpdateAccount();

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