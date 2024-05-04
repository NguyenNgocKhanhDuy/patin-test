package vn.hcmuaf.edu.fit.bean;

import vn.hcmuaf.edu.fit.dao.IDao;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Log implements Serializable {
    private int id;
    private int ip;
    private String level;
    private String address;
    private String preValue;
    private String value;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Log() {
    }

    public Log(int id, int ip, String level, String address, String preValue, String value, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.ip = ip;
        this.level = level;
        this.address = address;
        this.preValue = preValue;
        this.value = value;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPreValue() {
        return preValue;
    }

    public void setPreValue(String preValue) {
        this.preValue = preValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", ip=" + ip +
                ", level='" + level + '\'' +
                ", address='" + address + '\'' +
                ", preValue='" + preValue + '\'' +
                ", value='" + value + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}