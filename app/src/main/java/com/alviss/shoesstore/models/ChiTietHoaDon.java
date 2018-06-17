package com.alviss.shoesstore.models;

public class ChiTietHoaDon extends BaseModel {

    private String MaCTHD;
    private String MaSP;
    private String SoLuong;
    private String GiaBan;
    private String MaHD;

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public ChiTietHoaDon(String maCTHD, String maSP, String soLuong, String giaBan, String maHD) {

        MaCTHD = maCTHD;
        MaSP = maSP;
        SoLuong = soLuong;
        GiaBan = giaBan;
        MaHD = maHD;
    }




    public ChiTietHoaDon() {
    }

    public String getMaCTHD() {
        return MaCTHD;
    }

    public void setMaCTHD(String maCTHD) {
        MaCTHD = maCTHD;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }

    public String getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(String giaBan) {
        GiaBan = giaBan;
    }
}
