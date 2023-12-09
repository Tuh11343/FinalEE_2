function removeShow() {
    const modal = document.querySelector(".modal");

    modal.classList.remove("show");
}

function submitForm(formId) {
    var form = document.getElementById(formId);
    form.submit();
}

function logInAjaxRequest() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    $.ajax({
        type: "POST",
        url: contextPath + "/HeaderServlet",
        data: {
            action: "signIn",
            signInName: email,
            signInPassword: password,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === 1) {
                document.cookie = "signInAccountID=" + data.accountID + "; path=/";

                if (data.accountPermission === 1) {
                    window.location.href = contextPath + "/AdminManagerServlet";
                } else {
                    location.reload();
                }
            } else if (data.success === 0) {
            }
        },
        error: function (error) {
            console.log("error :>> ", error);
        },
    });
}

function signUpAjaxRequest() {
    const customerName = document.getElementById("name-regis").value;
    const email = document.getElementById("email-regis").value;
    const phoneNumber = document.getElementById("phone-regis").value;
    const address = document.getElementById("address-regis").value;
    const password = document.getElementById("password-regis").value;
    const rePassword = document.getElementById("re-password").value;

    $.ajax({
        type: "POST",
        url: contextPath + "/HeaderServlet",
        data: {
            action: "signUp",
            email: email,
            customerName: customerName,
            phoneNumber: phoneNumber,
            address: address,
            password: password,
            rePassword: rePassword,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            removeShow();

            if (data.systemError === 1) {
                console.log("System Error");
                alert("Loi he thong");
            } else if (data.success === 1) {
                //Announce
                alert("Đăng kí thành công");
                //hiển thị thông báo
            } else if (data.passwordIncorrect === 1) {
                console.log("Password not correct");
                alert("Mat khau khong dung");
            }
        },
    });
}

function productsSoldByMonthAjaxRequest(selectedDateID, action, tableID) {
    var selectedDate = document.getElementById(selectedDateID).value;

    // Tạo đối tượng Date từ giá trị ngày
    var date = new Date(selectedDate);

    // Lấy tháng và năm
    var month = date.getMonth() + 1; // Tháng bắt đầu từ 0 nên cần cộng thêm 1
    var year = date.getFullYear();

    console.log("Month" + month + " " + "year:" + year);

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminManagerServlet",
        data: {
            action: action,
            month: month,
            year: year,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            $("#" + tableID)
                .find("tr:gt(0)")
                .remove();

            switch (tableID) {
                case "productsTable": {
                    for (var i = 0; i < data.length; i++) {
                        var row =
                            "<tr><td>" +
                            data[i].itemID +
                            "</td><td>" +
                            data[i].productsSold +
                            "</td></tr>";
                        $("#" + tableID).append(row);
                    }
                    break;
                }
                case "productsSoldByYearTable": {
                    for (var i = 0; i < data.length; i++) {
                        var row =
                            "<tr><td>" +
                            data[i].itemID +
                            "</td><td>" +
                            data[i].productsSold +
                            "</td></tr>";
                        $("#" + tableID).append(row);
                    }
                    break;
                }
                case "totalProductsSoldByMonthTable": {
                    for (var i = 0; i < data.length; i++) {
                        var row =
                            "<tr><td>" +
                            data[i].itemID +
                            "</td><td>" +
                            data[i].productsSold +
                            "</td></tr>";
                        $("#" + tableID).append(row);
                    }
                    break;
                }
                case "totalProductsSoldByYearTable": {
                    for (var i = 0; i < data.length; i++) {
                        var row =
                            "<tr><td>" +
                            data[i].itemID +
                            "</td><td>" +
                            data[i].productsSold +
                            "</td></tr>";
                        $("#" + tableID).append(row);
                    }
                    break;
                }
                case "recentFiveMonthRevenueTable": {
                    for (var i = 0; i < data.length; i++) {
                        var row =
                            "<tr><td>" + data[i].month + "</td><td>" + data[i].revenue + "</td></tr>";
                        $("#" + tableID).append(row);
                    }
                    break;
                }
                case "monthRevenueTable": {
                    for (var i = 0; i < data.length; i++) {
                        var row =
                            "<tr><td>" + data[i].month + "</td><td>" + data[i].revenue + "</td></tr>";
                        $("#" + tableID).append(row);
                    }
                    break;
                }
            }
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function selectTedtable() {
    const selectIndex = document.getElementById("type-select"),
        tableType = document.querySelectorAll(".typetable");
    console.log(selectIndex.value);
    tableType.forEach(function (item) {
        let itemtable = item.getAttribute(`data-type`);
        if (selectIndex.value === itemtable) {
            tableType.forEach(function (item) {
                item.style.display = "none";
            });
            item.style.display = "block";
        }
    });
}


function initData() {

    console.log(1);
    let cartList = JSON.parse(localStorage.getItem("YOUR_CART"));
    let data = JSON.stringify(cartList);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", contextPath + "/CartServlet", true);
    xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
    xhr.send(data);

    xhr.onload = function () {
        if (xhr.status === 200) {
            window.location.href = contextPath + "/CartServlet?cartList="+data;
        } else {
            alert("Có lỗi xảy ra: " + xhr.status);
        }
    };
    /*if(cartList!==null){
      let totalCost = 0;
      for(let i = 0; i < cartList.length; i++) {
        let saleCost;
        let sale=cartList[i].stockItem.item.sale;

        if(sale!==null){

          saleCost=cartList[i].stockItem.item.price * (1 - sale.sale_percentage/100);
          totalCost+=saleCost * cartList[i].amount;
          let formattedSaleCost = saleCost.toLocaleString('en-US');
          console.log(formattedSaleCost);

        }else{

          totalCost += cartList[i].stockItem.price * cartList[i].amount;

        }
        let formattedTotalCost = totalCost.toLocaleString('en-US');
        document.getElementById("totalCost").innerText = formattedTotalCost + " VNĐ";

      }
    }*/
}

function renderCartNologin(){
    let dataCart = JSON.parse(localStorage.getItem('YOUR_CART'));
    console.log(dataCart);

    dataCart.forEach(function (item){
        console.log(item);

    })
    let cartDisplay = document.querySelector('')
}


renderCartNologin();