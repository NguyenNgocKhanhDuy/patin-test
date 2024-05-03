<%@ page import="java.text.NumberFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/DataTables/datatables.css">
    <title>BT3</title>
    <fmt:setLocale value="vi_VN"/>
    <style>
        #content .container {
            margin-top: 40px;
            margin-bottom: 80px;
        }

        .bill-info {
            margin-bottom: 40px;
            font-size: 18px;
        }

        .bill-info ul li {
            list-style: none;
            margin: 8px 0;
        }
    </style>
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
            <div class="bill-info">
                <ul>
                    <li>Bill:
                        <input type="hidden" id="billID" value="${bill.getId()}">
                        ${bill.getName()}</li>
                    <li>Date:
                        <fmt:parseDate pattern="y-M-dd'T'H:m" var="date" value="${bill.getDate()}"/>
                        <fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm"/>
                    </li>
                    <li>Status:
<%--                        ${bill.getStatus()}--%>
                        <select id="statusBill">
                            <option ${bill.getStatus().equals("Trạng thái 1") ? "selected" : ""} value="Trạng thái 1">Trạng thái 1</option>
                            <option ${bill.getStatus().equals("Trạng thái 2") ? "selected" : ""} value="Trạng thái 2">Trạng thái 2</option>
                            <option ${bill.getStatus().equals("Trạng thái 3") ? "selected" : ""} value="Trạng thái 3">Trạng thái 3</option>
                            <option ${bill.getStatus().equals("Trạng thái huỷ") ? "selected" : ""} value="Trạng thái huỷ">Trạng thái huỷ</option>
                            <option ${bill.getStatus().equals("Trạng thái hoàn thành") ? "selected disabled" : ""} value="Trạng thái hoàn thành">Trạng thái hoàn thành</option>
                        </select>
                    </li>
                    <li>Payment: ${bill.getPayment()}</li>
                    <li>Note: ${bill.getNote()}</li>
                    <li>User name: ${bill.getUser().getFullName()}</li>
                    <li>User Phone: ${bill.getUser().getPhone()}</li>
                </ul>
            </div>

            <table id="data" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Color</th>
                        <th>Size</th>
                        <th>Quantity</th>
                        <th>Price</th>
                    </tr>
                </thead>
            </table>
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
    <script src="${pageContext.request.contextPath}/assets/js/category.js"></script>
    <script src="${pageContext.request.contextPath}/assets/DataTables/datatables.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bt3BillDetail.js"></script>
</body>
</html>