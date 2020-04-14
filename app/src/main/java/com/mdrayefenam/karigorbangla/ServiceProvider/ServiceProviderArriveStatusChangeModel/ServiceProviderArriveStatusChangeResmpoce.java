
package com.mdrayefenam.karigorbangla.ServiceProvider.ServiceProviderArriveStatusChangeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceProviderArriveStatusChangeResmpoce {

    @SerializedName("ChangeStatus")
    @Expose
    private Integer changeStatus;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(Integer changeStatus) {
        this.changeStatus = changeStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
