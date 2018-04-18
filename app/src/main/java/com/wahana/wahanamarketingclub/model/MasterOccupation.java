package com.wahana.wahanamarketingclub.model;

/**
 * Created by lely
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MasterOccupation {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("occupation")
    @Expose
    private String occupation;

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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }


    @Override
    public String toString() {
        return occupation;
    }

}
