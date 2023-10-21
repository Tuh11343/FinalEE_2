$(document).ready(function () {
    /* =====================SearchBoxHeader========================*/
    function showBoxSearch() {
        const btnShow = document.querySelector(".btnshow_search"),
                searchModal = document.querySelector(".header__right--search");
        btnShow.addEventListener("click", function (e) {
            e.preventDefault();
            searchModal.classList.toggle("active");
            btnShow.classList.toggle("active");
        });
    }
    showBoxSearch();
    /* =====================SliderProduct========================*/

    function productSlider() {
        var elem = document.querySelectorAll(".carousel-img");
        elem.forEach(function (item) {
            var flkty = new Flickity(item, {
                // options
                cellAlign: "left",
                contain: true,
                imagesLoaded: true,
                prevNextButtons: false,
                imagesLoaded: true,
                // wrapAround: true,
            });
            item.addEventListener("mouseout", function () {
                flkty.previous();
            });
            item.addEventListener("mouseover", function () {
                flkty.next();
            });
        });
    }

    productSlider();
});



function showtable() {

    const btntabs = document.querySelectorAll('.accordion-button'),
            tables = document.querySelectorAll('.table-data');
    function removeActive() {
        tables.forEach(function (item) {
            item.classList.remove('active');
        });
    }

    btntabs.forEach(function (item) {
        item.addEventListener('click', function (e) {
            e.preventDefault();
            removeActive();
            item.classList.add('active');
            let typebtn = item.getAttribute("data-type");
            tables.forEach(function (item) {
                let typeItem = item.getAttribute("data-type");
                if (typeItem === typebtn) {
                    item.classList.add('active');
                }
            });
        });
    });


}
showtable();
function handleDelete() {
    const deltabs = document.querySelectorAll('.btnDel');

    deltabs.forEach(function (item) {
        item.addEventListener("click", () => {
            return confirm('Bạn có chắc chắn muốn xóa?');


        });

    });



}
handleDelete();




function handleAddUsers() {
    var addUser = document.getElementById("AddBtn");
    var modal = document.getElementById("add-user");
    var btnClose = document.querySelector(".clsadduser");
    addUser.addEventListener('click', function () {
         modal.classList.add("active");
        
    });
   btnClose.addEventListener('click',function(){
               modal.classList.remove("active");

   });


}
handleAddUsers();




function handleUpdateUsers() {
    var updateUser = document.querySelectorAll(".btnUpdateUser");
    var modal = document.getElementById("update-user");

    updateUser.forEach(function (item) {
        item.addEventListener("click", () =>{
            modal.style.display = "block";
        });
        
    });
    function closeModal() {
        modal.style.display = "none";
    }
    var closeBtn = document.getElementsByClassName("close")[0];
    closeBtn.addEventListener("click", closeModal);


}
handleUpdateUsers();

function handleAddProducts() {
    var addProducts = document.getElementById("AddProductsBtn");
    var modal = document.querySelector(".modal__addproduct");
    var btnClose = document.querySelector(".clsaddproduct");
    addProducts.addEventListener('click', function () {
        modal.classList.add("active");
        console.log(1);

    });
   
   btnClose.addEventListener('click',function(){
               modal.classList.remove("active")

   })

}
handleAddProducts();

function handleUpdateProducts() {
    var updateProducts = document.querySelectorAll(".btnUpdateProducts");
    var modal = document.querySelector(".modal__updateproduct");
    var btnClose = document.querySelector(".clsupdateproduct");
console.log(modal);
    updateProducts.forEach(function (item) {
        item.addEventListener("click" , () => {
         modal.classList.add("active");
        console.log(1);
        });
       

    });
   
   btnClose.addEventListener('click',function(){
               modal.classList.remove("active")

   })

}
handleUpdateProducts();



const productData = [
    { product: 'Áo thun xanh dương', sales: 100 },
    { product: 'Áo ba lỗ trắng', sales: 200 },
    { product: 'Áo khoác dù chống nước', sales: 150 },
];

// Populate table
const table = document.getElementById('statsTable');
productData.forEach(data => {
    const row = table.insertRow(-1);
    const cell1 = row.insertCell(0);
    const cell2 = row.insertCell(1);
    cell1.textContent = data.product;
    cell2.textContent = data.sales;
});

// Create chart
const ctx = document.getElementById('productChart').getContext('2d');
new Chart(ctx, {
    type: 'bar',
    data: {
        labels: productData.map(data => data.product),
        datasets: [{
            label: 'Sales',
            data: productData.map(data => data.sales),
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});


function itemCollectionClick(itemId) {
    var form = document.getElementById("form_itemCollection");
    form.submit();
}


function test() {
    console.log(1);
}



