package com.wahana.wahanamarketingclub.model;

/**
 * Created by Lely
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerProspectIndex {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("salesman_id")
    @Expose
    private String salesmanId;
    @SerializedName("no_hp")
    @Expose
    private String no_hp;

    @SerializedName("status_profile")
    @Expose
    private String status_profile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getSalesmanId() {
        return salesmanId;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getStatus_profile() {
        return status_profile;
    }

    public void setStatus_profile(String status_profile) {
        this.status_profile = status_profile;
    }

    public String getNo_hp() {
        return no_hp;
    }
}