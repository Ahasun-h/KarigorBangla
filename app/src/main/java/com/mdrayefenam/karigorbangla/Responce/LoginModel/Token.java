
package com.mdrayefenam.karigorbangla.Responce.LoginModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token {

    @SerializedName("headers")
    @Expose
    private Headers headers;
    @SerializedName("original")
    @Expose
    private Original original;
    @SerializedName("exception")
    @Expose
    private Object exception;

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public Original getOriginal() {
        return original;
    }

    public void setOriginal(Original original) {
        this.original = original;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }

}
