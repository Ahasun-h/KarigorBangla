
package com.mdrayefenam.karigorbangla.ServiceTaker.AddJobHistoryModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddJobHistoryRespoce {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private Integer status;

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

}
