
package com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderConfirmJobModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceProviderConfirmJobResmpoce {

    @SerializedName("serviceConfirmDataForUser")
    @Expose
    private List<ServiceConfirmDataForUser> serviceConfirmDataForUser = null;

    public List<ServiceConfirmDataForUser> getServiceConfirmDataForUser() {
        return serviceConfirmDataForUser;
    }

    public void setServiceConfirmDataForUser(List<ServiceConfirmDataForUser> serviceConfirmDataForUser) {
        this.serviceConfirmDataForUser = serviceConfirmDataForUser;
    }

}
