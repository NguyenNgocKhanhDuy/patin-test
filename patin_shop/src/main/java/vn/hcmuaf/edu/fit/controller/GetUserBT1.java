package vn.hcmuaf.edu.fit.controller;

import com.google.gson.Gson;
import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.dao.UserDaoBT1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;


@WebServlet(name = "GetUserBT1", value = "/getUserBT1")
public class GetUserBT1 extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = UserDaoBT1.getInstance().getAllUser();
        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(users));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}