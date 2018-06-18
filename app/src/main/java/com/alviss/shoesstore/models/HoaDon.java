package com.alviss.shoesstore.models;

import java.util.ArrayList;
import java.util.List;

public class HoaDon extends BaseModel {

    private String dateString;
    private String quantity;
    private String price;
    private String discount;
    private String percentDiscount;
    private List<HangHoa> hangHoa;

    public HoaDon(String _id, String dateString, String quantity, String price, String discount, String percentDiscount, List<HangHoa> hangHoa) {
        super(_id);
        this.dateString = dateString;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.percentDiscount = percentDiscount;
        this.hangHoa = hangHoa;
    }

    public HoaDon(String dateString, String quantity, String price, String discount, String percentDiscount, List<HangHoa> hangHoa) {
        this.dateString = dateString;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.percentDiscount = percentDiscount;
        this.hangHoa = hangHoa;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPercentDiscount() {
        return percentDiscount;
    }

    public void setPercentDiscount(String percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public List<HangHoa> getHangHoa() {
        return hangHoa;
    }

    public void setHangHoa(List<HangHoa> hangHoa) {
        this.hangHoa = hangHoa;
    }


//=======
//    private String MaHD;
//    private String NgayLap;
//    private String TongTien;
//    private String MaSDTKhachHang;
//
//    public HoaDon(String maHD, String ngayLap, String tongTien, String maKhachHang) {
//
//        MaHD = maHD;
//        NgayLap = ngayLap;
//        TongTien = tongTien;
//        MaSDTKhachHang = maKhachHang;
//    }
//
//    public HoaDon(String ngayLap, String tongTien, String maKhachHang) {
//        NgayLap = ngayLap;
//        TongTien = tongTien;
//        MaSDTKhachHang = maKhachHang;
//    }
//
//    public String getMaHD() {
//        return MaHD;
//    }
//
//    public void setMaHD(String maHD) {
//        MaHD = maHD;
//    }
//
//    public String getNgayLap() {
//        return NgayLap;
//    }
//
//    public void setNgayLap(String ngayLap) {
//        NgayLap = ngayLap;
//    }
//
//    public String getTongTien() {
//        return TongTien;
//    }
//
//    public void setTongTien(String tongTien) {
//        TongTien = tongTien;
//    }
//
//    public String getMaKhachHang() {
//        return MaSDTKhachHang;
//    }
//
//    public void setMaKhachHang(String maKhachHang) {
//        MaSDTKhachHang = maKhachHang;
//>>>>>>> fb44c878767b7179fb5580283072a31e3a3dbf37
//    }
}
