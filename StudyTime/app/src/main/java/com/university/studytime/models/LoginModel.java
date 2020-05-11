package com.university.studytime.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("role")
    @Expose
    private int role;

    @SerializedName("isSuccess")
    @Expose
    private int isSuccess;

    public LoginModel(String login, String password, String message, int role, int isSuccess) {
        this.login = login;
        this.password = password;
        this.message = message;
        this.role = role;
        this.isSuccess = isSuccess;
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

    public int getRole() {
        return role;
    }

    public int getIsSuccess() {
        return isSuccess;
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

    public void setRole(int role) {
        this.role = role;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }
}
