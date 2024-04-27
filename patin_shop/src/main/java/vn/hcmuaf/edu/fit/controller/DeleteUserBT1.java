package vn.hcmuaf.edu.fit.controller;

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
        try {
            id = Integer.parseInt(request.getParameter("id"));
            if (UserDaoBT1.getInstance().delete(id)){
                response.getWriter().println("Xoá thành công");
            }else {
                response.getWriter().println("Không thể xoá");
            }
        }catch (NumberFormatException e){
            response.getWriter().println("Không phải số");
        }
    }
}