package com.wahana.wahanamarketingclub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nina on 4/9/2018.
 */

public class ActivitySalesmenIndex {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("activity_subject")
    @Expose
    private String activitySubject;
    @SerializedName("activity_tgl")
    @Expose
    private String activityTgl;
    @SerializedName("activity_time")
    @Expose
    private String activityTime;
    @SerializedName("activity_note")
    @Expose
    private String activityNote;
    @SerializedName("salesman_name")
    @Expose
    private String salesmanName;
    @SerializedName("activity_status")
    @Expose
    private String activityStatus;
    @SerializedName("activity_type")
    @Expose
    private String activityType;
    @SerializedName("lead_nama")
    @Expose
    private String leadNama;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivitySubject() {
        return activitySubject;
    }

    public void setActivitySubject(String activitySubject) {
        this.activitySubject = activitySubject;
    }

    public String getActivityTgl() {
        return activityTgl;
    }

    public void setActivityTgl(String activityTgl) {
        this.activityTgl = activityTgl;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityNote() {
        return activityNote;
    }

    public void setActivityNote(String activityNote) {
        this.activityNote = activityNote;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getLeadNama() {
        return leadNama;
    }

    public void setLeadNama(String leadNama) {
        this.leadNama = leadNama;
    }

}