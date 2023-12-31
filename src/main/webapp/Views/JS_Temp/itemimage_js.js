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



/*Item Image*/
function handleAddItemImage() {
  var addItemImage = document.getElementById("itemImage_addTrigger");
  var modal = document.getElementById("add-itemImage");
  var btnClose = document.querySelector(".clsAddItemImage");
  addItemImage.addEventListener("click", function () {
    modal.classList.add("active");
    console.log(1);
  });
  btnClose.addEventListener("click", function () {
    modal.classList.remove("active");
  });
}
handleAddItemImage();

function handleUpdateItemImage() {
  var updateItemImage = document.querySelectorAll(".btnUpdateItemImage");
  var modal = document.getElementById("update-itemImage");
  var btnClose = document.querySelector(".clsUpdateItemImage");

  updateItemImage.forEach(function (item) {
    item.addEventListener("click", () => {
      modal.style.display = "block";

      var id=item.getAttribute("data-itemImageID");
      var itemID = item.getAttribute("data-itemImageItemID");
      var imageURL = item.getAttribute("data-itemImageURL");

      document.getElementById("update_itemImageID").value=id;
      document.getElementById("update_itemImageURL").value = imageURL;

      /*Combobox CustomerID*/
      var update_itemImage_selectElement = document.getElementById(
          "update_itemImageItemID"
      );
      for (var i = 0; i < update_itemImage_selectElement.options.length; i++) {
        var option = update_itemImage_selectElement.options[i];
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
handleUpdateItemImage();


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

function addItemImage() {

  let add_itemImageItemID = document.getElementById("add_itemImageItemID").value;
  let add_itemImageURL = document.getElementById("add_itemImageURL").value;

  if (add_itemImageItemID == null || add_itemImageItemID == "" || add_itemImageURL== null || add_itemImageURL == "") {
    alert("Không thể để dữ liệu trống");
  } else {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageItemImageServlet",
      data: {
        action: "addItemImage",
        add_itemImageItemID:add_itemImageItemID,
        add_itemImageURL:add_itemImageURL,
      }, headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
      success: function (data) {

        data = JSON.parse(data);
        if (data.success === true) {

          alert("Thêm hình ảnh sản phẩm thành công");
          location.href = adminManagerContextPath + "/ManageItemImageServlet";

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

function deleteItemImage(itemImageID) {
  // Trả về true nếu người dùng đã xác nhận, ngược lại trả về false
  if (confirm('Bạn có chắc chắn xóa không?')) {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageItemImageServlet",
      data: {
        action: "deleteItemImage",
        itemImageID: itemImageID,
      },
      success: function (data) {
        data = JSON.parse(data);
        if (data.success === true) {
          alert("Xóa hình ảnh sản phẩm thành công");
          location.href = adminManagerContextPath + "/ManageItemImageServlet";
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

function updateItemImage() {
  let update_itemImageID=document.getElementById("update_itemImageID").value;
  let update_itemImageItemID = document.getElementById("update_itemImageItemID").value;
  let update_itemImageURL = document.getElementById("update_itemImageURL").value;

  if (update_itemImageItemID == null || update_itemImageItemID == "" || update_itemImageURL== null || update_itemImageURL == "") {
    alert("Không thể để dữ liệu trống");
  } else {
    $.ajax({
      type: "POST",
      url: adminManagerContextPath + "/ManageItemImageServlet",
      data: {
        action: "updateItemImage",
        update_itemImageID:update_itemImageID,
        update_itemImageItemID:update_itemImageItemID,
        update_itemImageURL:update_itemImageURL,
      }, headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
      success: function (data) {

        data = JSON.parse(data);
        if (data.success === true) {

          alert("Cập nhật hình ảnh sản phẩm thành công");
          refreshItemImage();

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

function searchAndSortItemImage() {
  let itemImageSearchType = document.getElementById("itemImageSearchType").value;
  let itemImageInputSearch = document.getElementById("itemImageInputSearch").value;
  let itemImageSortType = document.getElementById("itemImageSortType").value;

  if (itemImageSearchType == null || itemImageSearchType == "" || itemImageInputSearch == null || itemImageInputSearch == "" ||
      itemImageSortType == null || itemImageSortType == "") {
    alert("Không thể để dữ liệu trống");
    return false;
  }
     if (itemImageSearchType == "id" || itemImageSearchType == "itemID") {
          var num = parseFloat(itemImageInputSearch);
          if (Number.isInteger(num)) {
               return true;
          } else {
              alert("Dữ liệu điền vào không hợp lệ");
              return false;
          }
      }
    return true;

}

function refreshItemImage() {
  location.href = adminManagerContextPath + "/ManageItemImageServlet";
}

function itemImageToExcel() {
  let table = document.getElementById("tableItemImage");

  let dataToSend = [];

  // Lặp qua các dòng của bảng và thu thập thông tin từ mỗi ô cột
  for (let i = 1; i < table.rows.length; i++) {
    let currentRow = table.rows[i];

    // Thu thập thông tin từ các ô cột trong dòng
    let id = currentRow.cells[0].textContent;
    let productID = currentRow.cells[1].textContent;
    let imageURL = currentRow.cells[2].textContent;

    // Tạo đối tượng chứa thông tin từ dòng hiện tại
    let ImagesData = {
      id: id,
      productID: productID,
      imageURL:imageURL,
    };

    // Thêm đối tượng vào mảng dataToSend
    dataToSend.push(ImagesData);
  }

  const workbook = XLSX.utils.book_new();
  const sheet = XLSX.utils.json_to_sheet(dataToSend);
  XLSX.utils.book_append_sheet(workbook, sheet, "Item Image");

  XLSX.writeFile(workbook, "itemImage.xlsx");
}