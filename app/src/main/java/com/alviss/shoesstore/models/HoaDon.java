package com.alviss.shoesstore.models;

import java.util.ArrayList;

public class HoaDon extends BaseModel {
    private String MaHD;
    private String NgayLap;
    private String TongTien;
    private String MaSDTKhachHang;

    public HoaDon(String maHD, String ngayLap, String tongTien, String maKhachHang) {

        MaHD = maHD;
        NgayLap = ngayLap;
        TongTien = tongTien;
        MaSDTKhachHang = maKhachHang;
    }

    public HoaDon(String ngayLap, String tongTien, String maKhachHang) {
        NgayLap = ngayLap;
        TongTien = tongTien;
        MaSDTKhachHang = maKhachHang;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public String getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(String ngayLap) {
        NgayLap = ngayLap;
    }

    public String getTongTien() {
        return TongTien;
    }

    public void setTongTien(String tongTien) {
        TongTien = tongTien;
    }

    public String getMaKhachHang() {
        return MaSDTKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        MaSDTKhachHang = maKhachHang;
    }
}
