package vn.hcmuaf.edu.fit.services;

import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.dao.UserDao;
import vn.hcmuaf.edu.fit.dao.UserDaoBT1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceBT1 {
    private static UserServiceBT1 instance;

    public static UserServiceBT1 getInstance() {
        if (instance == null) instance = new UserServiceBT1();
        return instance;
    }

    public boolean addUser(User user, String ip, String level, String address) {
        return UserDaoBT1.getInstance().addUser(user, ip, level, address);
    }

    public boolean isExistsUser(String email){
        return UserDaoBT1.getInstance().isExistsUser(email);
    }

    public String validate(User user){
        if (!checkEmai(user.getEmail()) || user.getEmail().length() == 0){
            return "Nhập đúng định dạng email";
        } else if (!checkPass(user.getPassword()) || user.getPassword().length() == 0) {
            return "Mật khẩu có ít nhất một ký tự viết hoa, một ký tự viết thường và một số";
        } else if (!checkPhone(user.getPhone()) || user.getPhone().length() == 0) {
            return "Số điện thoại có 10 số";
        } else if (user.getFullName().length() <= 0) {
            return "Họ tên không được để trống";
        } else if (user.getAddress().length() <= 0) {
            return "Địa chỉ không được để trống";
        } else if (!checkDOB(user.getDob())) {
            return "Bạn chưa đủ 18 tuổi";
        }
        return "";
    }

    public boolean checkEmai(String email){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean checkPass(String pass){
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    public boolean checkPhone(String phone){
        Pattern pattern = Pattern.compile("^\\d{1,10}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public boolean checkDOB(Date dob){
        Date now = new Date();
        return now.getYear() - dob.getYear() > 18;
    }


}
