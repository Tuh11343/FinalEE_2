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
function handleAddItemCollection() {
  var addItemCollection = document.getElementById("itemCollection_addTrigger");
  var modal = document.getElementById("add-itemCollection");
  var btnClose = document.querySelector(".clsAddItemCollection");
  addItemCollection.addEventListener("click", function () {
    modal.classList.add("active");
    console.log(1);
  });
  btnClose.addEventListener("click", function () {
    modal.classList.remove("active");
  });
}
handleAddItemCollection();

function handleUpdateItemCollection() {
  var updateItemCollection = document.querySelectorAll(".btnUpdateItemCollection");
  var modal = document.getElementById("update-itemCollection");
  var btnClose = document.querySelector(".clsUpdateItemCollection");

  updateItemCollection.forEach(function (item) {
    item.addEventListener("click", () => {
      modal.style.display = "block";

      var id=item.getAttribute("data-itemCollectionID");
      var name = item.getAttribute("data-itemCollectionName");

      document.getElementById("update_itemCollectionID").value=id;
      document.getElementById("update_itemCollectionName").value = name;
    });
  });
  btnClose.addEventListener("click", function () {
    modal.style.display = "none";
  });
}
handleUpdateItemCollection();


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

function addItemCollection() {

  let add_itemCollectionName = document.getElementById("add_itemCollectionName").value;

  if (add_itemCollectionName == null || add_itemCollectionName == "") {
    alert("Không thể để dữ liệu trống");
  } else {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageItemCollectionServlet",
      data: {
        action: "addItemCollection",
        add_itemCollectionName:add_itemCollectionName,
      }, headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
      success: function (data) {

        data = JSON.parse(data);
        if (data.success === true) {

          alert("Thêm bộ sưu tập thành công");
          location.href = adminManagerContextPath + "/ManageItemCollectionServlet";

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

function deleteItemCollection(itemCollectionID) {
  // Trả về true nếu người dùng đã xác nhận, ngược lại trả về false
  if (confirm('Bạn có chắc chắn xóa không?')) {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageItemCollectionServlet",
      data: {
        action: "deleteItemCollection",
        itemCollectionID: itemCollectionID,
      },
      success: function (data) {
        data = JSON.parse(data);
        if (data.success === true) {
          alert("Xóa bộ sưu tập thành công");
          location.href = adminManagerContextPath + "/ManageItemCollectionServlet";

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

function updateItemCollection() {
  let update_itemCollectionID=document.getElementById("update_itemCollectionID").value;
  let update_itemCollectionName=document.getElementById("update_itemCollectionName").value;

  if (update_itemCollectionName == null || update_itemCollectionName == "") {
    alert("Không thể để dữ liệu trống");
  }  else {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageItemCollectionServlet",
      data: {
        action: "updateItemCollection",
        update_itemCollectionID:update_itemCollectionID,
        update_itemCollectionName:update_itemCollectionName,
      }, headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
      success: function (data) {

        data = JSON.parse(data);
        if (data.success === true) {

          alert("Cập nhật bộ sưu tập thành công");
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

function searchAndSortItemCollection() {
  let itemCollectionSearchType = document.getElementById("itemCollectionSearchType").value;
  let itemCollectionInputSearch = document.getElementById("itemCollectionInputSearch").value;
  let itemCollectionSortType = document.getElementById("itemCollectionSortType").value;

  if (itemCollectionSearchType == null || itemCollectionSearchType == "" || itemCollectionInputSearch == null || itemCollectionInputSearch == "" ||
      itemCollectionSortType == null || itemCollectionSortType == "") {
    alert("Không thể để dữ liệu trống");
    return false;
  } else {
    return true;
  }
}

function refreshItemCollection() {
  location.href = adminManagerContextPath + "/ManageItemCollectionServlet";
}