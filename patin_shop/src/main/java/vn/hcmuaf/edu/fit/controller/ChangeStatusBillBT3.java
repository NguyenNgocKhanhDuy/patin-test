package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.dao.BillDao;
import vn.hcmuaf.edu.fit.dao.BillDaoBT3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "ChangeStatusBillBT3", value = "/changeStatusBillBT3")
public class ChangeStatusBillBT3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        String status = request.getParameter("status");

            try {
                id = Integer.parseInt(request.getParameter("id"));
                String oldStatus = BillDaoBT3.getInstance().getStatus(id);
                if (!oldStatus.equals("Trạng thái hoàn thành")){
                    if (BillDaoBT3.getInstance().changeStatus(id, status)){
                        response.getWriter().println("Thay đổi thành công");
                    }else {
                        response.getWriter().println("Thay đổi thất bại");
                    }
                }else {
                    response.getWriter().println("Trạng thái hoàn thành không thể thay đổi");
                }
            }catch (NumberFormatException e){

            }

    }
}