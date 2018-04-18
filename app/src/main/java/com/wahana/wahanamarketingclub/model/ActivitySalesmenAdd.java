package com.wahana.wahanamarketingclub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivitySalesmenAdd {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("activity_kode")
    @Expose
    private String activityKode;
    @SerializedName("activity_tgl")
    @Expose
    private String activityTgl;
    @SerializedName("activity_time")
    @Expose
    private String activityTime;
    @SerializedName("activity_subject")
    @Expose
    private String activitySubject;
    @SerializedName("activity_type")
    @Expose
    private Integer activityType;
    @SerializedName("activity_note")
    @Expose
    private String activityNote;
    @SerializedName("activity_status")
    @Expose
    private Integer activityStatus;
    @SerializedName("lead_id")
    @Expose
    private String leadId;
    @SerializedName("lead_code")
    @Expose
    private String leadCode;
    @SerializedName("lead_status_old")
    @Expose
    private String leadStatusOld;
    @SerializedName("lead_status")
    @Expose
    private String leadStatus;
    @SerializedName("create_by")
    @Expose
    private Integer createBy;
    @SerializedName("modi_by")
    @Expose
    private Integer modiBy;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("is_delete")
    @Expose
    private Integer isDelete;
    @SerializedName("kunci")
    @Expose
    private String kunci;
    @SerializedName("salesman_id")
    @Expose
    private String salesmanId;
    @SerializedName("salesman_code")
    @Expose
    private String salesmanCode;
    @SerializedName("salesman_name")
    @Expose
    private String salesmanName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivitySubject() {
        return activitySubject;
    }

    public void setActivitySubject(String activitySubject) {
        this.activitySubject = activitySubject;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public String getActivityNote() {
        return activityNote;
    }

    public void setActivityNote(String activityNote) {
        this.activityNote = activityNote;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getLeadCode() {
        return leadCode;
    }

    public void setLeadCode(String leadCode) {
        this.leadCode = leadCode;
    }

    public String getLeadStatusOld() {
        return leadStatusOld;
    }

    public void setLeadStatusOld(String leadStatusOld) {
        this.leadStatusOld = leadStatusOld;
    }

    public String getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getModiBy() {
        return modiBy;
    }

    public void setModiBy(Integer modiBy) {
        this.modiBy = modiBy;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
    }

    public String getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(String salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getSalesmanCode() {
        return salesmanCode;
    }

    public void setSalesmanCode(String salesmanCode) {
        this.salesmanCode = salesmanCode;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

}
