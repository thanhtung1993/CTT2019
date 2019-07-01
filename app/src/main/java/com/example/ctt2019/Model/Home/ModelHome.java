package com.example.ctt2019.Model.Home;


public class ModelHome {
private int partner_id;
private String parter_name;

    public ModelHome() {

    }

    public int getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(int partner_id) {
        this.partner_id = partner_id;
    }

    public String getParter_name() {
        return parter_name;
    }

    public void setParter_name(String parter_name) {
        this.parter_name = parter_name;
    }

    public ModelHome(int partner_id, String parter_name) {
        this.partner_id = partner_id;
        this.parter_name = parter_name;
    }
}



