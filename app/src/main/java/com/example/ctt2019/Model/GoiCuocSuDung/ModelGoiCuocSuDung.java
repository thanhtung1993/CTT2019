package com.example.ctt2019.Model.GoiCuocSuDung;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelGoiCuocSuDung {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("descript")
    @Expose
    private String descript;
    @SerializedName("partner_id")
    @Expose
    private Integer partnerId;
    @SerializedName("trans_type")
    @Expose
    private Object transType;
    @SerializedName("channel_type")
    @Expose
    private Object channelType;
    @SerializedName("updated_date")
    @Expose
    private Integer updatedDate;
    @SerializedName("category_id_tyleanchia")
    @Expose
    private String categoryIdTyleanchia;
    @SerializedName("id_tyleanchia")
    @Expose
    private String idTyleanchia;
    @SerializedName("pheduyet")
    @Expose
    private String pheduyet;
    @SerializedName("comment_refuse")
    @Expose
    private Object commentRefuse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Object getTransType() {
        return transType;
    }

    public void setTransType(Object transType) {
        this.transType = transType;
    }

    public Object getChannelType() {
        return channelType;
    }

    public void setChannelType(Object channelType) {
        this.channelType = channelType;
    }

    public Integer getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Integer updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCategoryIdTyleanchia() {
        return categoryIdTyleanchia;
    }

    public void setCategoryIdTyleanchia(String categoryIdTyleanchia) {
        this.categoryIdTyleanchia = categoryIdTyleanchia;
    }

    public String getIdTyleanchia() {
        return idTyleanchia;
    }

    public void setIdTyleanchia(String idTyleanchia) {
        this.idTyleanchia = idTyleanchia;
    }

    public String getPheduyet() {
        return pheduyet;
    }

    public void setPheduyet(String pheduyet) {
        this.pheduyet = pheduyet;
    }

    public Object getCommentRefuse() {
        return commentRefuse;
    }

    public void setCommentRefuse(Object commentRefuse) {
        this.commentRefuse = commentRefuse;
    }
}
