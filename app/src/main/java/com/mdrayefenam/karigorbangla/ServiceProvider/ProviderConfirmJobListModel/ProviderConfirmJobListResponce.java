
package com.mdrayefenam.karigorbangla.ServiceProvider.ProviderConfirmJobListModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProviderConfirmJobListResponce {

    @SerializedName("ServiceConfirmData")
    @Expose
    private List<ServiceConfirmDatum> serviceConfirmData = null;

    public List<ServiceConfirmDatum> getServiceConfirmData() {
        return serviceConfirmData;
    }

    public void setServiceConfirmData(List<ServiceConfirmDatum> serviceConfirmData) {
        this.serviceConfirmData = serviceConfirmData;
    }

}
