package com.wahana.wahanamarketingclub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by User on 3/1/2018.
 */

public class MyProfile {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("is_login")
    @Expose
    private Object isLogin;
    @SerializedName("last_login")
    @Expose
    private Object lastLogin;
    @SerializedName("branch_id")
    @Expose
    private Object branchId;
    @SerializedName("branch_code")
    @Expose
    private Object branchCode;
    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("salesman_id")
    @Expose
    private Object salesmanId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("created_by")
    @Expose
    private Object createdBy;
    @SerializedName("modified_at")
    @Expose
    private String modifiedAt;
    @SerializedName("modi_by")
    @Expose
    private Object modiBy;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Object isLogin) {
        this.isLogin = isLogin;
    }

    public Object getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Object lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Object getBranchId() {
        return branchId;
    }

    public void setBranchId(Object branchId) {
        this.branchId = branchId;
    }

    public Object getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(Object branchCode) {
        this.branchCode = branchCode;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Object getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Object salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Object getModiBy() {
        return modiBy;
    }

    public void setModiBy(Object modiBy) {
        this.modiBy = modiBy;
    }

}

//    @SerializedName("status")
//    @Expose
//    private Integer status;
//    @SerializedName("message")
//    @Expose
//    private String message;
//    @SerializedName("result")
//    @Expose
//    private List<MyProfileResult> result = null;
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public List<MyProfileResult> getResult() {
//        return result;
//    }
//
//    public void setResult(List<MyProfileResult> result) {
//        this.result = result;
//    }
//
//}