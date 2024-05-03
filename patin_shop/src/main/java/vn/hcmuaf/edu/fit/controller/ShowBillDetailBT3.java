package vn.hcmuaf.edu.fit.controller;

import vn.hcmuaf.edu.fit.bean.Bill;
import vn.hcmuaf.edu.fit.dao.BillDaoBT3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet(name = "ShowBillDetailBT3", value = "/showBillDetailBT3")
public class ShowBillDetailBT3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            Bill bill = BillDaoBT3.getInstance().getBill(id);
            request.setAttribute("bill", bill);
            request.setAttribute("total", BillDaoBT3.getInstance().getTotalPrice(id));
            request.getRequestDispatcher("bt3BillDetail.jsp").forward(request, response);
        }catch (NumberFormatException e){

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}