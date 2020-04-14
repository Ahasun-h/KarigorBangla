
package com.mdrayefenam.karigorbangla.ServiceProvider.ServiceProviderInformationModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceProviderInformationResponce {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("ServiceProviderInformation")
    @Expose
    private ServiceProviderInformation serviceProviderInformation;

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

    public ServiceProviderInformation getServiceProviderInformation() {
        return serviceProviderInformation;
    }

    public void setServiceProviderInformation(ServiceProviderInformation serviceProviderInformation) {
        this.serviceProviderInformation = serviceProviderInformation;
    }

}
