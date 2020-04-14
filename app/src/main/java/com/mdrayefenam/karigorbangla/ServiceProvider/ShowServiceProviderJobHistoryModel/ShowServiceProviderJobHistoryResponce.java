
package com.mdrayefenam.karigorbangla.ServiceProvider.ShowServiceProviderJobHistoryModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowServiceProviderJobHistoryResponce {

    @SerializedName("ShowServiceProviderJobHistory")
    @Expose
    private List<ShowServiceProviderJobHistory> showServiceProviderJobHistory = null;

    public List<ShowServiceProviderJobHistory> getShowServiceProviderJobHistory() {
        return showServiceProviderJobHistory;
    }

    public void setShowServiceProviderJobHistory(List<ShowServiceProviderJobHistory> showServiceProviderJobHistory) {
        this.showServiceProviderJobHistory = showServiceProviderJobHistory;
    }

}
