
package com.mdrayefenam.karigorbangla.Responce.LoginModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponce {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_information")
    @Expose
    private UserInformation userInformation;
    @SerializedName("serviceProviderInformation")
    @Expose
    private ServiceProviderInformation serviceProviderInformation;
    @SerializedName("token")
    @Expose
    private Token token;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public ServiceProviderInformation getServiceProviderInformation() {
        return serviceProviderInformation;
    }

    public void setServiceProviderInformation(ServiceProviderInformation serviceProviderInformation) {
        this.serviceProviderInformation = serviceProviderInformation;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

}
