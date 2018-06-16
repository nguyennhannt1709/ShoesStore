package com.alviss.shoesstore.models;

public class HangHoa extends BaseModel {
    private String CODEM;//code sp
    private String MNAME;//ten
    private String MSHOPNAME;//ten
   private String MSUM ; //tong sl 1 mat hang
    private String MSELL; //con ban hay ko(0,1)
    private String MBUY; //gia mua vao
    private String MPRICE; //gia ban ra
    private String MTYPE; //loai
    private String MCOUNT; //don vi tinh
    private String MNOTE; //ghi chu cho kh, sp, bill
    private String MSIZE;
    private String MIMAGE;
    private String MINFO;

    public HangHoa(String CODEM, String MNAME, String MSHOPNAME, String MPRICE, String MSIZE,String MINFO, String MIMAGE) {

        this.CODEM = CODEM;
        this.MNAME = MNAME;
        this.MSHOPNAME = MSHOPNAME;
        this.MPRICE = MPRICE;
        this.MSIZE = MSIZE;
        this.MIMAGE = MIMAGE;
        this.MINFO = MINFO;
    }

    public HangHoa() {
    }

    public String getMSIZE() {
        return MSIZE;
    }

    public void setMSIZE(String MSIZE) {
        this.MSIZE = MSIZE;
    }

    public String getMIMAGE() {
        return MIMAGE;
    }

    public void setMIMAGE(String MIMAGE) {
        this.MIMAGE = MIMAGE;
    }

    public String getMINFO() {
        return MINFO;
    }

    public void setMINFO(String MINFO) {
        this.MINFO = MINFO;
    }

    public String getMSHOPNAME() {
        return MSHOPNAME;
    }

    public void setMSHOPNAME(String MSHOPNAME) {
        this.MSHOPNAME = MSHOPNAME;
    }

    public HangHoa(String _id, String CODEM, String MNAME, String MSUM, String MSELL, String MBUY, String MPRICE, String MTYPE, String MCOUNT, String MNOTE) {
        super(_id);
        this.CODEM = CODEM;
        this.MNAME = MNAME;
        this.MSUM = MSUM;
        this.MSELL = MSELL;
        this.MBUY = MBUY;
        this.MPRICE = MPRICE;
        this.MTYPE = MTYPE;
        this.MCOUNT = MCOUNT;
        this.MNOTE = MNOTE;
    }

    public String getCODEM() {
        return CODEM;
    }

    public void setCODEM(String CODEM) {
        this.CODEM = CODEM;
    }

    public String getMNAME() {
        return MNAME;
    }

    public void setMNAME(String MNAME) {
        this.MNAME = MNAME;
    }

    public String getMSUM() {
        return MSUM;
    }

    public void setMSUM(String MSUM) {
        this.MSUM = MSUM;
    }

    public String getMSELL() {
        return MSELL;
    }

    public void setMSELL(String MSELL) {
        this.MSELL = MSELL;
    }

    public String getMBUY() {
        return MBUY;
    }

    public void setMBUY(String MBUY) {
        this.MBUY = MBUY;
    }

    public String getMPRICE() {
        return MPRICE;
    }

    public void setMPRICE(String MPRICE) {
        this.MPRICE = MPRICE;
    }

    public String getMTYPE() {
        return MTYPE;
    }

    public void setMTYPE(String MTYPE) {
        this.MTYPE = MTYPE;
    }

    public String getMCOUNT() {
        return MCOUNT;
    }

    public void setMCOUNT(String MCOUNT) {
        this.MCOUNT = MCOUNT;
    }

    public String getMNOTE() {
        return MNOTE;
    }

    public void setMNOTE(String MNOTE) {
        this.MNOTE = MNOTE;
    }
}
