package com.example.ctt2019.Model.DichVuHot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDichVuHot  {
    @SerializedName("SUB_CODE")
    @Expose
    private String SUBCODE;
    @SerializedName("CODE")
    @Expose
    private String CODE;
    @SerializedName("GAMELIST_ID")
    @Expose
    private Integer GAMELISTID;
    @SerializedName("PRICE")
    @Expose
    private String PRICE;
    @SerializedName("SUB_ID")
    @Expose
    private Integer SUBID;
    @SerializedName("PARTNER_NAME")
    @Expose
    private String PARTNERNAME;
    @SerializedName("DAY_CIRCLE")
    @Expose
    private String DAYCIRCLE;

    private String NAME;

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getSUBCODE() {
        return SUBCODE;
    }

    public void setSUBCODE(String SUBCODE) {
        this.SUBCODE = SUBCODE;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public Integer getGAMELISTID() {
        return GAMELISTID;
    }

    public void setGAMELISTID(Integer GAMELISTID) {
        this.GAMELISTID = GAMELISTID;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public Integer getSUBID() {
        return SUBID;
    }

    public void setSUBID(Integer SUBID) {
        this.SUBID = SUBID;
    }

    public String getPARTNERNAME() {
        return PARTNERNAME;
    }

    public void setPARTNERNAME(String PARTNERNAME) {
        this.PARTNERNAME = PARTNERNAME;
    }

    public String getDAYCIRCLE() {
        return DAYCIRCLE;
    }

    public void setDAYCIRCLE(String DAYCIRCLE) {
        this.DAYCIRCLE = DAYCIRCLE;
    }
}
