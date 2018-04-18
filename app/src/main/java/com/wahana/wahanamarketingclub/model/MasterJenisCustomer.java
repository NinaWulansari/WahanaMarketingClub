package com.wahana.wahanamarketingclub.model;

/**
 * Created by lely
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MasterJenisCustomer {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("jenis")
    @Expose
    private String jenis;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }


    @Override
    public String toString() {
        return jenis;
    }
}