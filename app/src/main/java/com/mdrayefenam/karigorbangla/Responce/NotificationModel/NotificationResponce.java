
package com.mdrayefenam.karigorbangla.Responce.NotificationModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationResponce {

    @SerializedName("Notification")
    @Expose
    private List<Notification> notification = null;

    public List<Notification> getNotification() {
        return notification;
    }

    public void setNotification(List<Notification> notification) {
        this.notification = notification;
    }

}
