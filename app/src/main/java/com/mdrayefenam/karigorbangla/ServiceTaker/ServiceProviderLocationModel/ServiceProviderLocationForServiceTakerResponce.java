
package com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderLocationModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceProviderLocationForServiceTakerResponce {

    @SerializedName("ServiceProviders")
    @Expose
    private List<ServiceProvider> serviceProviders = null;

    public List<ServiceProvider> getServiceProviders() {
        return serviceProviders;
    }

    public void setServiceProviders(List<ServiceProvider> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

}
