
package com.mdrayefenam.karigorbangla.ServiceTaker.ServiceTakerInterestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceTakerInterestResponce {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("UserInterest")
    @Expose
    private UserInterest userInterest;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserInterest getUserInterest() {
        return userInterest;
    }

    public void setUserInterest(UserInterest userInterest) {
        this.userInterest = userInterest;
    }

}
