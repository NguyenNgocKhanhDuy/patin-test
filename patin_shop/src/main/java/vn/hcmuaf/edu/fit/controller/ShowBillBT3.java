package vn.hcmuaf.edu.fit.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.hcmuaf.edu.fit.LocalDateTimeAdapter;
import vn.hcmuaf.edu.fit.bean.Bill;
import vn.hcmuaf.edu.fit.dao.BillDaoBT3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.List;


@WebServlet(name = "ShowBillBT3", value = "/showBillBT3")
public class ShowBillBT3 extends HttpServlet {
    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bill> bills = BillDaoBT3.getInstance().getAllBill();
        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(bills));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}