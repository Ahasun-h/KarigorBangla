
package com.mdrayefenam.karigorbangla.ServiceTaker.showServiceTakerJobHistoryModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowServiceTakerJobHistoryResponce {

    @SerializedName("showServiceTakerJobHistory")
    @Expose
    private List<ShowServiceTakerJobHistory> showServiceTakerJobHistory = null;

    public List<ShowServiceTakerJobHistory> getShowServiceTakerJobHistory() {
        return showServiceTakerJobHistory;
    }

    public void setShowServiceTakerJobHistory(List<ShowServiceTakerJobHistory> showServiceTakerJobHistory) {
        this.showServiceTakerJobHistory = showServiceTakerJobHistory;
    }

}
