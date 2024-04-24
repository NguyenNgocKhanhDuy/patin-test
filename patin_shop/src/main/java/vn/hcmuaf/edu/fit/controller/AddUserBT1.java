package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.services.UserServiceBT1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.Date;


@WebServlet(name = "AddUserBT1", value = "/addUserBT1")
public class AddUserBT1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String phone = request.getParameter("phone");
        String fullName = request.getParameter("name");
        String address = request.getParameter("address");
        String date = request.getParameter("date");
        int gender;
        int role;
        int verify;


        try {
            gender = Integer.parseInt(request.getParameter("gender"));
            role = Integer.parseInt(request.getParameter("role"));
            verify = Integer.parseInt(request.getParameter("verify"));

            Date dob = Date.valueOf(date);

            User user = new User();
            user.setEmail(email);
            user.setPassword(pass);
            user.setPhone(phone);
            user.setFullName(fullName);
            user.setAddress(address);
            user.setDob(dob);

            user.setSex(gender);
            user.setVerify(verify);
            user.setRole(role);

            String error = UserServiceBT1.getInstance().validate(user);
            if (error.equals("")){
                if (!UserServiceBT1.getInstance().isExistsUser(user.getEmail())){
                    if (UserServiceBT1.getInstance().addUser(user)){
                        request.setAttribute("typeReturn", "success");
                        request.setAttribute("textReturn", "Thêm thành công");
                        request.getRequestDispatcher("bt1.jsp").forward(request, response);
                    }else {
                        request.setAttribute("typeReturn", "error");
                        request.setAttribute("textReturn", "Lỗi sql");
                        request.getRequestDispatcher("bt1.jsp").forward(request, response);
                    }
                }else {
                    request.setAttribute("typeReturn", "error");
                    request.setAttribute("textReturn", "Đã tồn tại tài khoản");
                    request.getRequestDispatcher("bt1.jsp").forward(request, response);
                }
            }else {
                request.setAttribute("typeReturn", "error");
                request.setAttribute("textReturn", error);
                request.getRequestDispatcher("bt1.jsp").forward(request, response);
            }

        }catch (NumberFormatException e){
            request.setAttribute("typeReturn", "error");
            request.setAttribute("textReturn", "Không hợp lệ");
            request.getRequestDispatcher("bt1.jsp").forward(request, response);
        }
    }
}