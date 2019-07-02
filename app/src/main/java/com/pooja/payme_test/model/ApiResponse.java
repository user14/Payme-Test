package com.pooja.payme_test.model;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    private String success;

    @SerializedName("msg")
    private String message;
    private String token;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
