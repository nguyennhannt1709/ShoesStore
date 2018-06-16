package com.alviss.shoesstore.models;

public class HoaDon extends BaseModel {
    private String CODEB; //code bill
    private String BTIME; //ngay bill
    private String BAMOUNT; //so luong sp trong chi tiet hoa don
    private String BOFFPE; //phan tram chiet khau
    private String BOFFPR; //chiet khau
    private String BPRICE; //tong hoa don


    public HoaDon() {
    }

    public HoaDon(String _id, String CODEB, String BTIME, String BAMOUNT, String BOFFPE, String BOFFPR, String BPRICE) {
        super(_id);
        this.CODEB = CODEB;
        this.BTIME = BTIME;
        this.BAMOUNT = BAMOUNT;
        this.BOFFPE = BOFFPE;
        this.BOFFPR = BOFFPR;
        this.BPRICE = BPRICE;
    }

    public String getCODEB() {
        return CODEB;
    }

    public void setCODEB(String CODEB) {
        this.CODEB = CODEB;
    }

    public String getBTIME() {
        return BTIME;
    }

    public void setBTIME(String BTIME) {
        this.BTIME = BTIME;
    }

    public String getBAMOUNT() {
        return BAMOUNT;
    }

    public void setBAMOUNT(String BAMOUNT) {
        this.BAMOUNT = BAMOUNT;
    }

    public String getBOFFPE() {
        return BOFFPE;
    }

    public void setBOFFPE(String BOFFPE) {
        this.BOFFPE = BOFFPE;
    }

    public String getBOFFPR() {
        return BOFFPR;
    }

    public void setBOFFPR(String BOFFPR) {
        this.BOFFPR = BOFFPR;
    }

    public String getBPRICE() {
        return BPRICE;
    }

    public void setBPRICE(String BPRICE) {
        this.BPRICE = BPRICE;
    }
}
