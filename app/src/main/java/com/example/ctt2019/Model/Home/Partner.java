package com.example.ctt2019.Model.Home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Partner {
    @SerializedName("partner_id")
    @Expose
    private String partnerId;
    @SerializedName("partner_name")
    @Expose
    private String partnerName;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("phone_number")
    @Expose
    private Object phoneNumber;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("cpid")
    @Expose
    private String cpid;
    @SerializedName("callws_check")
    @Expose
    private Integer callwsCheck;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Object phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCpid() {
        return cpid;
    }

    public void setCpid(String cpid) {
        this.cpid = cpid;
    }

    public Integer getCallwsCheck() {
        return callwsCheck;
    }

    public void setCallwsCheck(Integer callwsCheck) {
        this.callwsCheck = callwsCheck;
    }
}
