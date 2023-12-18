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

function accordion() {
  let btnacc = document.querySelectorAll(".order--item .btns");
  console.log("btnacc :>> ", btnacc);
  function removeactive() {
    btnacc.forEach(function (item, index) {
      item.classList.remove("active");
      var panel = item.nextElementSibling;
      panel.style.maxHeight = null;
    });
  }
  btnacc.forEach(function (item, index) {
    item.addEventListener("click", function (e) {
      console.log(1);
      var panel = item.nextElementSibling;
      if (this.classList.contains("active")) {
        this.classList.remove("active");
        panel.style.maxHeight = null;
      } else {
        removeactive();
        this.classList.toggle("active");
        if (panel.style.maxHeight) {
          panel.style.maxHeight = null;
        } else {
          panel.style.maxHeight = panel.scrollHeight + "px";
        }
      }
      // removeactive();
    });
  });
}
accordion();

function HideShowSize() {
  var divSize = document.getElementById("btnShowSize");
  var divDes = document.getElementById("btnShowDes");
  var display = 0;
  if (divSize.style.display == "none") {
    divSize.style.display = "flex";
  } else {
    divSize.style.display = "none";
  }
}
function HideShowDes() {
  var divSize = document.getElementById("btnShowSize");
  var divDes = document.getElementById("btnShowDes");
  var display = 0;
  if (divDes.style.display == "none") {
    divDes.style.display = "block";
  } else {
    divDes.style.display = "none";
  }
}

function submitSort() {
  const formSort = document.querySelector(".formSort"),
      selecTed = document.querySelector(".sortinp");
  formSort.submit();
}

function sliderBanner() {
  var elem = document.querySelector(".hero .slider");
  console.log(elem);
  var flkty = new Flickity(elem, {
    // options
    cellAlign: "left",
    contain: true,
    prevNextButtons: false,
    pageDots: false,
    wrapAround: true,
    autoPlay: 2000,
    imagesLoaded: true,
  });
}
sliderBanner();

const handleModal = () => {
  const btn_close = document.querySelector(".btn-close"),
      modal = document.querySelector(".modal"),
      btn_open = document.querySelector(".btn-user"),
      overlay = document.querySelector(".overlay"),
      tabs = document.querySelectorAll(".tab-item"),
      forms = document.querySelectorAll(".form-pane"),
      formbox = document.querySelectorAll(".form-box"),
      btnFogetpass = document.querySelector(".fogetpass"),
      btnPreLogin = document.querySelector(".btnpre");
  btn_open.addEventListener("click", (e) => {
    e?.preventDefault();
    console.log(1);
    modal.classList.add("show");
  });
  function removeShow() {
    modal.classList.remove("show");
  }
  function removeActive() {
    tabs.forEach((tab) => {
      tab.classList.remove("active");
    });
    forms.forEach((form) => {
      form.classList.remove("active");
    });
  }
  btnPreLogin.addEventListener("click", function (e) {
    e.preventDefault();
    formbox[0].classList.add("active");
    formbox[1].classList.remove("active");
  });
  btnFogetpass.addEventListener("click", function (e) {
    console.log(1);
    e.preventDefault();
    formbox[0].classList.remove("active");
    formbox[1].classList.add("active");
  });
  btn_close.addEventListener("click", () => {
    removeShow();
    formbox[0].classList.add("active");
    formbox[1].classList.remove("active");
  });
  overlay.addEventListener("click", () => {
    removeShow();
  });
  tabs.forEach((tab) => {
    tab.addEventListener("click", () => {
      removeActive();
      tab.classList.add("active");
      let typebtn = tab.getAttribute("data-type");

      forms.forEach((form) => {
        if (form.getAttribute("data-type") == typebtn) {
          form.classList.add("active");
        }
      });
    });
  });
};
handleModal();



