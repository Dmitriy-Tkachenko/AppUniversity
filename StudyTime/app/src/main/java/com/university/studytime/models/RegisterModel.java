package com.university.studytime.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterModel {
    @SerializedName("full_name")
    @Expose
    private String fullName;

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("isSuccess")
    @Expose
    private int isSuccess;

    public RegisterModel(String fullName, String login, String password, String message, int isSuccess) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getMessage() {
        return message;
    }

    public int getIsSuccess() {
        return isSuccess;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }


}
