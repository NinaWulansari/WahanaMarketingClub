package com.wahana.wahanamarketingclub.model;

/**
 * Created by lely
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotifActivitySalesman {

    @SerializedName("id_cp")
    @Expose
    private String idCp;
    @SerializedName("cust_code")
    @Expose
    private String custCode;
    @SerializedName("cust_nama")
    @Expose
    private String custNama;
    @SerializedName("cust_alamat")
    @Expose
    private String custAlamat;
    @SerializedName("cust_hp")
    @Expose
    private String custHp;
    @SerializedName("id_act")
    @Expose
    private String idAct;
    @SerializedName("activity_kode")
    @Expose
    private String activityKode;
    @SerializedName("activity_tgl")
    @Expose
    private String activityTgl;
    @SerializedName("activity_subject")
    @Expose
    private String activitySubject;

    public String getIdCp() {
        return idCp;
    }

    public void setIdCp(String idCp) {
        this.idCp = idCp;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustNama() {
        return custNama;
    }

    public void setCustNama(String custNama) {
        this.custNama = custNama;
    }

    public String getCustAlamat() {
        return custAlamat;
    }

    public void setCustAlamat(String custAlamat) {
        this.custAlamat = custAlamat;
    }

    public String getCustHp() {
        return custHp;
    }

    public void setCustHp(String custHp) {
        this.custHp = custHp;
    }

    public String getIdAct() {
        return idAct;
    }

    public void setIdAct(String idAct) {
        this.idAct = idAct;
    }

    public String getActivityKode() {
        return activityKode;
    }

    public void setActivityKode(String activityKode) {
        this.activityKode = activityKode;
    }

    public String getActivityTgl() {
        return activityTgl;
    }

    public void setActivityTgl(String activityTgl) {
        this.activityTgl = activityTgl;
    }

    public String getActivitySubject() {
        return activitySubject;
    }

    public void setActivitySubject(String activitySubject) {
        this.activitySubject = activitySubject;
    }

}