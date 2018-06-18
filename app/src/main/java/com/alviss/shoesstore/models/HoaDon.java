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
}
