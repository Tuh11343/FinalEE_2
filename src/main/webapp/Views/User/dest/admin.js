function addCustomer(){

    let name = document.getElementById("add_customerName").value;
    let phoneNumber = document.getElementById("add_customerPhoneNumber").value;
    let email = document.getElementById("add_customerEmail").value;
    let address = document.getElementById("add_customerAddress").value;
    let table= document.getElementById("tableCustomer");


    let customer = {
        name: name,
        phone_number:phoneNumber,
        email:email,
        address:address,
    };

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "addCustomer",
            customer: JSON.stringify(customer),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if(data.success === true)
            {
                let customer=data.customer;
                table.innerHTML+='<tr>\n' +
                    '            <td>'+customer.id+'</td>\n' +
                    '            <td>'+customer.name+'</td>\n' +
                    '            <td>'+customer.phone_number+'</td>\n' +
                    '            <td>'+customer.email+'</td>\n' +
                    '            <td>'+customer.address+'</td>\n' +
                    '            <td>\n' +
                    '              <div class="flex-center grpbtn">\n' +
                    '                <a class="btnHD btnDel" type="submit" onclick="deleteCustomer('+customer.id+')">Xóa</a>\n' +
                    '                <button\n' +
                    '                        class="btnHD btnUpdateCustomer"\n' +
                    '                        data-customerID='+customer.id+'\n' +
                    '                        data-customerName='+customer.name+'\n' +
                    '                        data-customerPhoneNumber='+customer.phone_number+'\n' +
                    '                        data-customerEmail='+customer.email+'\n' +
                    '                        data-customerAddress='+customer.address+'\n' +
                    '                >\n' +
                    '                  Sửa\n' +
                    '                </button>\n' +
                    '              </div>\n' +
                    '            </td>\n' +
                    '          </tr>';
                alert("Thêm khách hàng thành công");
            }
            else
                console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function updateCustomer(){
    let id=document.getElementById("update_customerID").value;
    let name = document.getElementById("update_customerName").value;
    let phoneNumber = document.getElementById("update_customerPhoneNumber").value;
    let email = document.getElementById("update_customerEmail").value;
    let address = document.getElementById("update_customerAddress").value;
    let table= document.getElementById("tableCustomer");

    let customer = {
        id: id,
        name: name,
        phone_number:phoneNumber,
        email:email,
        address:address,
    };

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "updateCustomer",
            customer: JSON.stringify(customer),
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if(data.success === true)
            {
                let customer=data.customer;

                for (let i = 0, row; row = table.rows[i]; i++) {
                    // Lặp qua từng dòng trong bảng
                    let idCell = row.cells[0]; // Giả sử 'customer.id' là ô đầu tiên trong mỗi dòng
                    if (idCell.textContent === id) {
                        row.cells[1].textContent = customer.name; // Cập nhật tên
                        row.cells[2].textContent = customer.phone_number; // Cập nhật số điện thoại
                        row.cells[3].textContent = customer.email; // Cập nhật email
                        row.cells[4].textContent = customer.address; // Cập nhật địa chỉ
                        break; // Thoát khỏi vòng lặp sau khi cập nhật
                    }
                }
                alert("Cập nhật khách hàng thành công");
            }
            else
                console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}

function deleteCustomer(id,action,tableID){
    let confirmationResult = false;
    confirmationResult = confirm("Bạn có chắc chắn muốn xóa?");
    if(confirmationResult){
        $.ajax({
            type: "POST",
            url: adminManagerContextPath + "/AdminServlet",
            data: {
                action: action,
                id:id,
            },
            headers: {
                "X-Requested-With": "XMLHttpRequest",
            },
            success: function (data) {
                if(data.success === true)
                {
                    let table = document.getElementById(tableID);

                    for (let i = 0, row; row = table.rows[i]; i++) {
                        let idCell = row.cells[0]; // Giả sử 'customer.id' là ô đầu tiên trong mỗi dòng
                        if (idCell.textContent == id) {
                            table.deleteRow(i);
                            alert("Xóa thành công");
                        }
                    }
                }
                else
                    console.log("WTF");
            },
            error: function (error) {
                console.log("error:" + error);
            },
        });
    }

}

function customerToExcel(tableID) {
    let table = document.getElementById(tableID);

    let dataToSend = [];

    // Lặp qua các dòng của bảng và thu thập thông tin từ mỗi ô cột
    for (let i = 1; i < table.rows.length; i++) {
        let currentRow = table.rows[i];

        // Thu thập thông tin từ các ô cột trong dòng
        let id = currentRow.cells[0].textContent;
        let name = currentRow.cells[1].textContent;
        let phoneNumber = currentRow.cells[2].textContent;
        let email = currentRow.cells[3].textContent;
        let address = currentRow.cells[4].textContent;

        // Tạo đối tượng chứa thông tin từ dòng hiện tại
        let customerData = {
            id: id,
            name: name,
            phone_number: phoneNumber,
            email: email,
            address: address,
        };

        // Thêm đối tượng vào mảng dataToSend
        dataToSend.push(customerData);
    }

    const workbook = XLSX.utils.book_new();
    const sheet = XLSX.utils.json_to_sheet(dataToSend);
    XLSX.utils.book_append_sheet(workbook, sheet, 'Customers');

    XLSX.writeFile(workbook, 'customers.xlsx');
}

function searchAndSortCustomer(){


    let customerInputSearch=document.getElementById("customerInputSearch").value;
    let customerSortType=document.getElementById("customerSortType").value;
    let customerSearchType=document.getElementById("customerSearchType").value;

    $.ajax({
        type: "POST",
        url: adminManagerContextPath + "/AdminServlet",
        data: {
            action: "searchAndSortCustomer",
            customerInputSearch:customerInputSearch,
            customerSortType:customerSortType,
            customerSearchType:customerSearchType,
        },
        headers: {
            "X-Requested-With": "XMLHttpRequest",
        },
        success: function (data) {
            if(data.success === true)
            {
                let table = document.getElementById("tableCustomer");
                table.innerHTML='';

                let customerList=data.customerList;
                for (let i = 0;customerList.length ; i++) {
                    let customer=customerList[i];
                    table.innerHTML+='<tr>\n' +
                        '            <td>'+customer.id+'</td>\n' +
                        '            <td>'+customer.name+'</td>\n' +
                        '            <td>'+customer.phone_number+'</td>\n' +
                        '            <td>'+customer.email+'</td>\n' +
                        '            <td>'+customer.address+'</td>\n' +
                        '            <td>\n' +
                        '              <div class="flex-center grpbtn">\n' +
                        '                <a class="btnHD btnDel" type="submit" onclick="deleteCustomer('+customer.id+')">Xóa</a>\n' +
                        '                <button\n' +
                        '                        class="btnHD btnUpdateCustomer"\n' +
                        '                        data-customerID='+customer.id+'\n' +
                        '                        data-customerName='+customer.name+'\n' +
                        '                        data-customerPhoneNumber='+customer.phone_number+'\n' +
                        '                        data-customerEmail='+customer.email+'\n' +
                        '                        data-customerAddress='+customer.address+'\n' +
                        '                >\n' +
                        '                  Sửa\n' +
                        '                </button>\n' +
                        '              </div>\n' +
                        '            </td>\n' +
                        '          </tr>';
                }
            }
            else
                console.log("WTF");
        },
        error: function (error) {
            console.log("error:" + error);
        },
    });
}



