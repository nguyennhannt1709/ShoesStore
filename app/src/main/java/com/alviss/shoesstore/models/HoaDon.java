package com.alviss.shoesstore.models;

import java.util.ArrayList;
import java.util.List;

public class HoaDon extends BaseModel {

    public String dateString;
    public String quantity;
    public String price;
    public String discount;
    public String percentDiscount;
    public List<HangHoa> products;
    public KhachHang customer;

    public HoaDon(String _id, String dateString, String quantity, String price, String discount, String percentDiscount, List<HangHoa> products, KhachHang customer) {
        super(_id);
        this.dateString = dateString;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.percentDiscount = percentDiscount;
        this.products = products;
        this.customer = customer;
    }

    public HoaDon(String dateString, String quantity, String price, String discount, String percentDiscount, List<HangHoa> products, KhachHang customer) {
        this.dateString = dateString;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.percentDiscount = percentDiscount;
        this.products = products;
        this.customer = customer;
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

    public List<HangHoa> getProducts() {
        return products;
    }

    public void setProducts(List<HangHoa> products) {
        this.products = products;
    }

    public KhachHang getCustomer() {
        return customer;
    }

    public void setCustomer(KhachHang customer) {
        this.customer = customer;
    }
}
