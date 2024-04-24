<%@ page import="java.text.NumberFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bt1.css">
    <title>Quản lý</title>
    <fmt:setLocale value="vi_VN"/>
</head>
<body>
    <header>
        <div class="container">
            <img src="${pageContext.request.contextPath}/assets/images/logo.PNG" alt="" class="logo">
            <nav>
                <ul class="menu">
                    <li><a href="home">TRANG CHỦ</a></li>
                    <li>
                        <a href="listProduct">SẢN PHẨM</a>
                        <ul class="sub_menu list-category">

                        </ul>
                    </li>
                    <li><a href=contact.jsp>LIÊN HỆ</a></li>
                </ul>
            </nav>
            <div class="user">
                <ul>
                    <c:if test="${sessionScope.auth != null}">
                        <li>
                            <a href="account.jsp">
                                    ${sessionScope.auth.getFullName()}
                            </a>
                            <ul class="sub_menu user_sub">
                                <li>
                                    <a href="account.jsp">Tài khoản</a>
                                </li>
                                <c:if test="${sessionScope.auth.getRole() > 0}">
                                    <li>
                                        <a href="showUserAdmin">Quản lý</a>
                                    </li>
                                </c:if>
                                <li>
                                    <a href="logout">Đăng xuất</a>
                                </li>
                            </ul>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.auth == null}">
                        <li>
                            <a href="login.jsp">
                                ĐĂNG NHẬP
                            </a>
                        </li>
                        <li>
                            <a href="register.jsp">
                                ĐĂNG KÝ
                            </a>
                        </li>
                    </c:if>

                    <li><a href="showWishList"><i class="fa-solid fa-heart"></i></a></li>
                    <li class="cartLink">
                        <a href="showCart"><i class="fa-solid fa-cart-shopping"></i></a>
                        <c:if test="${sessionScope.cart != null && sessionScope.cart.getData().size() > 0}">
                            <span class="amount">${sessionScope.cart.getData().size()}</span>
                        </c:if>
                    </li>
                </ul>
            </div>
        </div>
    </header>

   <div id="content">
       <div class="container">
           <form action="addUserBT1" method="post">
               <h1>Thêm người dùng</h1>
               <div class="inside">
                   <div class="wrapper">
                       <input class="part" type="email" placeholder="email" name="email">
                       <input class="part" type="password" placeholder="password" name="pass">
                       <input class="part" type="text" placeholder="full name" name="name">
                   </div>
                   <div class="wrapper">
                       <div class="holder holder-row">
                           <input class="part" type="phone" placeholder="phone" name="phone">
                           <input class="part" type="text" placeholder="address" name="address">
                       </div>
                       <input class="part" type="date" name="date">
                       <div class="holder holder-row">
                           <select class="part" name="gender" id="gender">
                               <option value="0">Nam</option>
                               <option value="1">Nữ</option>
                           </select>
                           <select class="part" name="role" id="role">
                               <option value="0">Khách hàng</option>
                               <option value="1">Mod</option>
                               <option value="2">Admin</option>
                           </select>
                           <select class="part" name="verify" id="verify">
                               <option value="0">Chưa xác thực</option>
                               <option value="1">Đã xác thực</option>
                           </select>
                       </div>
                   </div>

               </div>
               <div class="return ${typeReturn != null ? typeReturn : ""}">
                   ${textReturn != null ? textReturn : ""}
               </div>
               <input type="submit" class="btn" value="Thêm">
           </form>
       </div>
   </div>

    <div class="popup ${type != null ? type : "none"}">
        <c:if test="${type.equals(\"success\")}">
            <i class="fa-solid fa-check icon"></i>
        </c:if>
        <c:if test="${type.equals(\"error\")}">
            <i class="fa-solid fa-ban fa-flip-horizontal icon"></i>
        </c:if>
        <c:if test="${type.equals(\"alert\")}">
            <i class="fa-solid fa-triangle-exclamation icon"></i>
        </c:if>
        <p>${information}</p>
        <i class="fa-solid fa-xmark del"></i>
    </div>

    <footer>
        <div class="container">
            <div class="info">
                <img src="${pageContext.request.contextPath}/assets/images/logo.PNG" alt="">
                <p>
                    Địa chỉ: Trường Đại học Nông Lâm Thành Phố Hồ Chí Minh
                </p>
                <p>
                    Số điện thoại:
                    <a href="tel:+">0839151003</a>
                </p>

                <p>
                    Email:
                    <a href="mailto:">21130035@st.hcmuaf.edu.vn</a>
                </p>
            </div>
            <div class="subscribe">
                <div class="social-media">
                    <ul>
                        <li><a href="https://www.facebook.com/"><i class="fa-brands fa-facebook-f"></i></a></li>
                        <li><a href="https://www.instagram.com/"><i class="fa-brands fa-instagram"></i></a></li>
                        <li><a href="https://twitter.com/"><i class="fa-brands fa-twitter"></i></a></li>
                        <li><a href="https://www.pinterest.com/"><i class="fa-brands fa-pinterest"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>

<%--    <script src="${pageContext.request.contextPath}/assets/js/showDanhMuc.js"></script>--%>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/assets/js/bt1.js"></script>
<%--    <script src="${pageContext.request.contextPath}/assets/js/search.js"></script>--%>
<%--    <script src="${pageContext.request.contextPath}/assets/js/category.js"></script>--%>
    <script src="${pageContext.request.contextPath}/assets/js/popupNotice.js"></script>
</body>
</html>