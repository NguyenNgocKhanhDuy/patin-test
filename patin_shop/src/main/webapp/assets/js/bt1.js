email = document.querySelector("input[type = email]")
pass = document.querySelector("input[type = password]")
text = document.querySelectorAll("input[type = text]")
phone = document.querySelector("input[type = phone]")
error = document.querySelector(".return")
var emailRegex = /^[A-Za-z0-9]+[A-Za-z0-9\.]+@[A-Za-z0-9]+([\.A-Za-z0-9]+)$/;
var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
var phoneRegex = /^\d{1,10}$/;

function validate() {
    if (!emailRegex.test(email.value)){
        error.innerText = "Nhập đúng định dạng email"
    }else if (!passwordRegex.test(pass.value)){
        error.innerText = "Mật khẩu có ít nhất một ký tự viết hoa, một ký tự viết thường và một số"
    }else if(!phoneRegex.test(phone.value)){
        error.innerText = "Số điện thoại có 10 số"
    }else if (text[0].value.length == 0) {
        error.innerText = "Họ tên không được để trống"
    }else if (text[1].value.length == 0){
        error.innerText = "Địa chỉ không được để trống"
    }else {
        error.innerText = ""
    }
}
email.addEventListener("input", validate)
pass.addEventListener("input", validate)
phone.addEventListener("input", validate)
text[0].addEventListener("input", validate)
text[1].addEventListener("input", validate)
