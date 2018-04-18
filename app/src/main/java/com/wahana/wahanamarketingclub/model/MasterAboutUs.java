package com.wahana.wahanamarketingclub.model;

/**
 * Created by ratriwow on 21/02/2018.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MasterAboutUs {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<MasterAboutUsResult> result = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MasterAboutUsResult> getResult() {
        return result;
    }

    public void setResult(List<MasterAboutUsResult> result) {
        this.result = result;
    }

}
