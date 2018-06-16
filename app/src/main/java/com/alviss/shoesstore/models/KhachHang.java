package com.alviss.shoesstore.models;

public class KhachHang extends BaseModel {
    private String TenKhachHang;
    private String SoDienThoai;
    private String DiaChi;

    private String Email;
    public KhachHang(){}

    public KhachHang(String tenKhachHang, String soDienThoai, String diaChi, String email) {
        TenKhachHang = tenKhachHang;
        SoDienThoai = soDienThoai;
        DiaChi = diaChi;
        Email = email;
    }

    public KhachHang(String _id, String tenKhachHang, String soDienThoai, String diaChi, String email) {
        super(_id);
        TenKhachHang = tenKhachHang;
        SoDienThoai = soDienThoai;
        DiaChi = diaChi;
        Email = email;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }


    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
