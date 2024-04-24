package vn.hcmuaf.edu.fit.dao;

import vn.hcmuaf.edu.fit.bean.User;
import vn.hcmuaf.edu.fit.db.JDBIConnector;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

public class UserDaoBT1 {
    private static UserDaoBT1 instance;

    public static UserDaoBT1 getInstance() {
        if (instance == null) instance = new UserDaoBT1();
        return instance;
    }

    public List<User> getAllUser(){
        List<User> users = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM user").mapToBean(User.class).stream().collect(Collectors.toList());
        });
        return users;
    }

    public boolean addUser(User user){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO user(email, password, verify, fullName, address, phone, sex, dob, role) VALUES (:email, :password,:verify, :fullName, :address, :phone, :sex, :dob, :role)")
                    .bind("email", user.getEmail()).bind("password", hashPassword(user.getPassword())).bind("verify", user.getVerify())
                    .bind("fullName", user.getFullName()).bind("address", user.getAddress()).bind("phone", user.getPhone())
                    .bind("sex", user.getSex()).bind("dob", user.getDob()).bind("role", user.getRole()).execute();
        });
        return i == 1 ? true : false;
    }

    public boolean isExistsUser(String email){
        List<String> strings = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT email FROM user WHERE email = ?")
                    .bind(0, email).mapTo(String.class).stream().collect(Collectors.toList());
        });
        return strings.size() == 0 ? false : true;
    }

    public boolean delete(int id){
        Integer i = JDBIConnector.get().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM user WHERE id = ?").bind(0, id).execute();
        });
        return i == 1 ? true : false;
    }

    public String hashPassword(String password){
        try {
            MessageDigest sha256 = null;
            sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = sha256.digest(password.getBytes());
            BigInteger number = new BigInteger(1, hash);
            return number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
