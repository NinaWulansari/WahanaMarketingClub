package com.wahana.wahanamarketingclub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lely
 */

public class MasterJenisKelamin {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("gender")
    @Expose
    private String gender;

    public MasterJenisKelamin(String code, String gender) {
        this.code = code;
        this.gender = gender;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return gender;
    }
}
