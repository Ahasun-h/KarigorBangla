
package com.mdrayefenam.karigorbangla.Responce.SignUpModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SinUpResponce {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("user_information")
    @Expose
    private UserInformation userInformation;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("token")
    @Expose
    private Token token;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
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

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

}
