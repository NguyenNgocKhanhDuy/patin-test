package vn.hcmuaf.edu.fit.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.hcmuaf.edu.fit.LocalDateTimeAdapter;
import vn.hcmuaf.edu.fit.bean.BillDetail;
import vn.hcmuaf.edu.fit.dao.BillDaoBT3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.List;


@WebServlet(name = "GetDataBillDetail", value = "/getDataBillDetail")
public class GetDataBillDetail extends HttpServlet {
    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
//    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            response.setContentType("application/json");
            List<BillDetail> billDetails = BillDaoBT3.getInstance().getBillAllDetail(id);
            response.getWriter().println(gson.toJson(billDetails));
        }catch (NumberFormatException e){

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}