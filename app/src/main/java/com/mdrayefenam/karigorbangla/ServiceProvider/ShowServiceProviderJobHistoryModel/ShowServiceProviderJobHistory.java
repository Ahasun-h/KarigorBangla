
package com.mdrayefenam.karigorbangla.ServiceProvider.ShowServiceProviderJobHistoryModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowServiceProviderJobHistory {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("provider_id")
    @Expose
    private String providerId;
    @SerializedName("provider_profile_image")
    @Expose
    private String providerProfileImage;
    @SerializedName("provider_name")
    @Expose
    private String providerName;
    @SerializedName("provider_mobile")
    @Expose
    private String providerMobile;
    @SerializedName("service_taker_id")
    @Expose
    private String serviceTakerId;
    @SerializedName("service_taker_name")
    @Expose
    private String serviceTakerName;
    @SerializedName("service_taker_email")
    @Expose
    private String serviceTakerEmail;
    @SerializedName("service_taker_numbre")
    @Expose
    private String serviceTakerNumbre;
    @SerializedName("service_taker_address")
    @Expose
    private String serviceTakerAddress;
    @SerializedName("service_taker_profile_image")
    @Expose
    private String serviceTakerProfileImage;
    @SerializedName("service_day")
    @Expose
    private String serviceDay;
    @SerializedName("service_time")
    @Expose
    private String serviceTime;
    @SerializedName("service_address")
    @Expose
    private String serviceAddress;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("job_done_status")
    @Expose
    private String jobDoneStatus;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderProfileImage() {
        return providerProfileImage;
    }

    public void setProviderProfileImage(String providerProfileImage) {
        this.providerProfileImage = providerProfileImage;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderMobile() {
        return providerMobile;
    }

    public void setProviderMobile(String providerMobile) {
        this.providerMobile = providerMobile;
    }

    public String getServiceTakerId() {
        return serviceTakerId;
    }

    public void setServiceTakerId(String serviceTakerId) {
        this.serviceTakerId = serviceTakerId;
    }

    public String getServiceTakerName() {
        return serviceTakerName;
    }

    public void setServiceTakerName(String serviceTakerName) {
        this.serviceTakerName = serviceTakerName;
    }

    public String getServiceTakerEmail() {
        return serviceTakerEmail;
    }

    public void setServiceTakerEmail(String serviceTakerEmail) {
        this.serviceTakerEmail = serviceTakerEmail;
    }

    public String getServiceTakerNumbre() {
        return serviceTakerNumbre;
    }

    public void setServiceTakerNumbre(String serviceTakerNumbre) {
        this.serviceTakerNumbre = serviceTakerNumbre;
    }

    public String getServiceTakerAddress() {
        return serviceTakerAddress;
    }

    public void setServiceTakerAddress(String serviceTakerAddress) {
        this.serviceTakerAddress = serviceTakerAddress;
    }

    public String getServiceTakerProfileImage() {
        return serviceTakerProfileImage;
    }

    public void setServiceTakerProfileImage(String serviceTakerProfileImage) {
        this.serviceTakerProfileImage = serviceTakerProfileImage;
    }

    public String getServiceDay() {
        return serviceDay;
    }

    public void setServiceDay(String serviceDay) {
        this.serviceDay = serviceDay;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getJobDoneStatus() {
        return jobDoneStatus;
    }

    public void setJobDoneStatus(String jobDoneStatus) {
        this.jobDoneStatus = jobDoneStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
