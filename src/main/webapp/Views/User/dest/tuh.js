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
    let cartList = JSON.parse(localStorage.getItem("YOUR_CART"));

    $.ajax({
        type: "POST",
        url: contextPath + "/HeaderServlet",
        data: {
            action: "signIn",
            signInName: email,
            signInPassword: password,
            cartList: JSON.stringify(cartList),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if (data.success === 1) {
                if(data.accountPermission !== 1){
                    document.cookie = "signInAccountID=" + data.accountID + "; path=/";
                }

                if(data.deleteLocalCart===true){
                    localStorage.clear();
                }

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


function renderData() {

    let cartList = JSON.parse(localStorage.getItem("YOUR_CART"));

    if (cartList !== null) {
        var totalCost = 0;
        for (let i = 0; i < cartList.length; i++) {
            var saleCost;
            var sale = cartList[i].stockItem.item.sale;
            if (sale !== null) {
                saleCost = cartList[i].stockItem.item.price * (1 - sale.sale_percentage / 100);
                totalCost += saleCost * cartList[i].amount;
            } else {
                totalCost += cartList[i].stockItem.item.price * cartList[i].amount;
            }
        }
        let formattedTotalCost = totalCost.toLocaleString('en-US');
        console.log(document.getElementById("totalCost"));
        document.getElementById("totalCost").innerText = formattedTotalCost + " VNĐ";

        for(let i=0;i<cartList.length;i++){
            var qty;
            var totalItem=0;
            if(cartList[i].stockItem.item.sale!=null){
                var saleTemp = cartList[i].stockItem.item.sale
                var saleCostTemp = cartList[i].stockItem.item.price * (1 - saleTemp.sale_percentage / 100);
                var saveAmount=cartList[i].stockItem.item.price-saleCostTemp;
                totalItem=saleCostTemp*cartList[i].amount;

                qty='<p class="qty">\n' +
                    '<span>Số lượng <strong> '+cartList[i].amount+' </strong>\n' +
                    '* '+saleCostTemp.toLocaleString('en-US')+'</span>\n' +
                    '<span class="discount-price">(Tiết kiệm:\n' +
                    ''+saveAmount.toLocaleString('en-US')+')</span>\n' +
                    '</p>\n';
            }else{
                totalItem=cartList[i].stockItem.item.price*cartList[i].amount;
                qty='<p class="qty">\n'+
                    '<span>Số lượng <strong> '+cartList[i].amount+' </strong>\n' +
                '* '+cartList[i].stockItem.item.price.toLocaleString('en-US')+'</span>\n'+
                    '</p>\n';
            }

            var table='<div class="cart__item">\n' +
                                    '<div class="gr--img">\n' +
                                    '    <div class="img">\n' +
                                    '        <img id="image1" src="'+cartList[i].stockItem.item.imageList[0].image_url+'" alt="">\n' +
                                    '    </div>\n' +
                                    '    <button class="btn btn-del" onclick="deleteCart('+cartList[i].stockItem.id+')">Xóa</button>\n' +
                                    '</div>\n' +
                                    '<div class="gr--info">\n' +
                                        '    <div class="info-top">\n' +
                                        '        <h4 class="name">'+cartList[i].stockItem.item.name+'</h4>\n'
                                                 +qty+
                                        '    </div>\n'+
                                        '    <div class="total-item">\n' +
                                        '        <span>= '+totalItem.toLocaleString('en-US')+' VNĐ</span>\n'+
                                        '    </div>\n'+
                                    '</div>'+
                            "</div>";
            document.getElementById("cart_detail_list").innerHTML+=table;
        }
    }
}

function order(){

    let cartList = JSON.parse(localStorage.getItem("YOUR_CART"));
    let discountCardID=document.getElementById("discount-code").value;
    let note=document.getElementById("note").value;
    let address=document.getElementById("address").value;
    let email=document.getElementById("emailOrder").value;
    console.log(email);

    $.ajax({
        type: "POST",
        url: contextPath + "/HeaderServlet",
        data: {
            action: "order",
            discountCardID: discountCardID,
            note: note,
            address: address,
            email: email,
            cartList: JSON.stringify(cartList),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if(data.success === 1)
            {
                localStorage.clear();
                window.location.reload();
            }
            else
                console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function deleteCart(stockItemID){
    console.log(stockItemID);
    let cartList = JSON.parse(localStorage.getItem("YOUR_CART"));
    cartList = cartList.filter(cart => cart.stockItem.id !== stockItemID);
    localStorage.setItem("YOUR_CART", JSON.stringify(cartList));
    alert("Xóa sản phẩm khỏi giỏ hàng thành công");
    window.location.reload();
}




