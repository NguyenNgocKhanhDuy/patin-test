package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.dao.UserDaoBT1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "DeleteUserBT1", value = "/deleteUserBT1")
public class DeleteUserBT1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        response.setContentType("text/plain");
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null){
            ip = request.getRemoteAddr();
        }
        try {
            id = Integer.parseInt(request.getParameter("id"));
            User user = new User();
            user.setId(id);
            user.setBeforeData("delete user id: "+user.getId());
            if (UserDaoBT1.getInstance().delete(user, ip, "danger", "admin delete user")){
                response.getWriter().println("Xoá thành công");
            }else {
                response.getWriter().println("Không thể xoá");
            }
        }catch (NumberFormatException e){
            response.getWriter().println("Không phải số");
        }
    }
}