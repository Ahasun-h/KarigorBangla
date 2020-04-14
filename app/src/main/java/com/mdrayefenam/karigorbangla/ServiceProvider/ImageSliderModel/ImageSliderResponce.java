
package com.mdrayefenam.karigorbangla.ServiceProvider.ImageSliderModel;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageSliderResponce {

    @SerializedName("ActiveImage")
    @Expose
    private List<ActiveImage> activeImage = null;

    public List<ActiveImage> getActiveImage() {
        return activeImage;
    }

    public void setActiveImage(List<ActiveImage> activeImage) {
        this.activeImage = activeImage;
    }
}
