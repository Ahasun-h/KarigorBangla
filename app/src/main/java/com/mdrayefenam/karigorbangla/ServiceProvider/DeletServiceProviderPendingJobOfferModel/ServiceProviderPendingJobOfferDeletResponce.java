
package com.mdrayefenam.karigorbangla.ServiceProvider.DeletServiceProviderPendingJobOfferModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceProviderPendingJobOfferDeletResponce {

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
