
package com.mdrayefenam.karigorbangla.ServiceTaker.ServiceProviderPendingJobOfferModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowServiceTakerPendingJobOfferResmpoce {

    @SerializedName("ShowServiceTakerPendingJobOffer")
    @Expose
    private List<ShowServiceTakerPendingJobOffer> showServiceTakerPendingJobOffer = null;

    public List<ShowServiceTakerPendingJobOffer> getShowServiceTakerPendingJobOffer() {
        return showServiceTakerPendingJobOffer;
    }

    public void setShowServiceTakerPendingJobOffer(List<ShowServiceTakerPendingJobOffer> showServiceTakerPendingJobOffer) {
        this.showServiceTakerPendingJobOffer = showServiceTakerPendingJobOffer;
    }

}
