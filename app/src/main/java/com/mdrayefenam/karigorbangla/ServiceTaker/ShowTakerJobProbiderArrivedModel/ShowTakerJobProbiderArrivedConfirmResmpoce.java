
package com.mdrayefenam.karigorbangla.ServiceTaker.ShowTakerJobProbiderArrivedModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowTakerJobProbiderArrivedConfirmResmpoce {

    @SerializedName("ShowTakerJobProbiderArrivedConfirm")
    @Expose
    private List<ShowTakerJobProbiderArrivedConfirm> showTakerJobProbiderArrivedConfirm = null;

    public List<ShowTakerJobProbiderArrivedConfirm> getShowTakerJobProbiderArrivedConfirm() {
        return showTakerJobProbiderArrivedConfirm;
    }

    public void setShowTakerJobProbiderArrivedConfirm(List<ShowTakerJobProbiderArrivedConfirm> showTakerJobProbiderArrivedConfirm) {
        this.showTakerJobProbiderArrivedConfirm = showTakerJobProbiderArrivedConfirm;
    }

}
