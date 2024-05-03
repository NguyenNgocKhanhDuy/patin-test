package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.Bill;
import vn.hcmuaf.edu.fit.bean.BillDetail;
import vn.hcmuaf.edu.fit.db.JDBIConnector;

import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

public class BillDaoBT3 {
    private static BillDaoBT3 instance;

    public BillDaoBT3() {
    }

    public static BillDaoBT3 getInstance() {
        if(instance == null) instance = new BillDaoBT3();
        return instance;
    }

    public List<Bill> getAllBill(){
        List<Bill> bills = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT bill.id, bill.name, bill.date, bill.status, bill.payment, bill.note, user.phone as user_phone FROM bill, user WHERE user.id = bill.user_id")
                    .mapToBean(Bill.class).stream().collect(Collectors.toList());
        });
        return bills;
    }

    public Bill getBill(int id){
        List<Bill> bills = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT bill.*, user.fullName as user_fullName, user.phone as user_phone FROM bill, user WHERE bill.user_id = user.id AND bill.id = ?")
                    .bind(0, id).mapToBean(Bill.class).stream().collect(Collectors.toList());
        });
        return bills.size() == 1 ? bills.get(0) : null;
    }

    public List<BillDetail> getBillAllDetail(int id){
        List<BillDetail> billDetails = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT bill_detail.price, bill_detail.quantity,  " +
                            "product.name as product_product_detail_product_name," +
                            "color.name as color_name, size.name as size_name " +
                            "FROM bill_detail, product, size, color " +
                            "WHERE bill_detail.bill_id = ? AND bill_detail.product_id = product.id " +
                            "AND bill_detail.color_id = color.id AND bill_detail.size_id = size.id ")
                    .bind(0, id).mapToBean(BillDetail.class).stream().collect(Collectors.toList());
        });
        return billDetails;

    }

    public boolean changeStatus(int id, String status){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("UPDATE bill SET status = :status WHERE id = :id").bind("id", id).bind("status", status).execute();
        });
        return i == 1 ? true : false;
    }

    public String getStatus(int id){
        List<String> strings = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT status FROM bill WHERE id = ?").bind(0, id).mapTo(String.class).stream().collect(Collectors.toList());
        });
        return strings.size() == 1 ? strings.get(0) : null;
    }

    public int getTotalPrice(int id){
        List<Integer> integers = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT SUM(price * quantity) FROM bill_detail WHERE bill_id = ?")
                    .bind(0, id).mapTo(Integer.class).stream().collect(Collectors.toList());
        });
        return integers.size() == 1 ? integers.get(0) : 0;
    }

    public static void main(String[] args) {
        System.out.println(BillDaoBT3.getInstance().getBillAllDetail(25));
    }
}
