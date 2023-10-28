<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/28/2023
  Time: 10:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modallogin">
    <div class="loginform">
        <button  class="btn-close">
            x
        </button>
        <form class="form-modal">
            <div class="form-gr">
                <label for="email">Gmail</label>
                <input name="email" type="email" required placeholder="Nhập Gmail"/ >
            </div>
            <div class="form-gr">
                <label for="password">Mật Khẩu</label>
                <input name="password" type="password" required placeholder="Nhập mật kẩu"/ >
            </div>
            <button class="btn">Đăng Nhập</button>
        </form>
    </div>
</div>
<style>
    .modallogin{
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background: rgba(0, 0, 0, 0.65);
        display: flex;
        justify-content: center;
    align-items: center;
        visibility: hidden;
        pointer-events: none;
        opacity: 0;
        transition: var(--t);
        z-index: 1000;
    }
    .show{
        visibility: visible;
        pointer-events: none;
        opacity: 1;
        transition: var(--t);
    }
    .loginform{
        background: #fff;
        padding: 50px 30px;

        border-radius: var(--brus);
        position: relative;
        min-width: 400px;
        z-index: 1000;
    }
    .loginform .btn-close{
        min-width: 40px;
        min-height: 40px;
        border-radius: 50%;
        background: var(--main-cl);
        position: absolute;
        right: -10px;
        top: -10px;
        font-size: 25px;
        color: #fff;
        display: flex;
        align-items: center;
justify-content: center;
        cursor: pointer;
    }

    .form-modal{
        display: flex;
        flex-direction: column;
    gap: 20px;
        z-index: 1000;
    }
    .form-modal .form-gr{
        display: flex;justify-content: space-between;
        align-items: center;
    }
    .form-modal .form-gr label{
        font-family: pb;
        font-size: 19px;
    }
    .form-modal .form-gr input{
        border-radius: 3px;
        border: 1px solid var(--grayop-cl);
        padding: 5px;
        width: 70%;
    }

</style>

<script>
    const modalForm = document.querySelector(".modallogin"),btnOpen = document.querySelector('.loginicon'), btnClose=document.querySelector('.btn-close')
    btnOpen.addEventListener('click',function (e){
        modalForm.classList.add("show")
    });
   btnClose.addEventListener(("click",function (e){
       modalForm.classList.remove("show")

   }))
</script>