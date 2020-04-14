
package com.mdrayefenam.karigorbangla.ServiceTaker.ChageJobCompleteStatusModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobCompleteStatusResmpoce {

    @SerializedName("JobCompleteStatus")
    @Expose
    private Integer jobCompleteStatus;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getJobCompleteStatus() {
        return jobCompleteStatus;
    }

    public void setJobCompleteStatus(Integer jobCompleteStatus) {
        this.jobCompleteStatus = jobCompleteStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
