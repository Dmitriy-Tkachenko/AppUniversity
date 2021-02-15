package com.university.studytime.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// POJO класс LoginModel используется для получения ответа с сервера данных в формате JSON.
// Каждая переменная аннотированная @SerializedName
// и имя в параметре аннотации в точности такое же, как в JSON ответе.
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

    public String getMessage() {
        return message;
    }

    public int getRole() {
        return role;
    }

    public int getIsSuccess() {
        return isSuccess;
    }
}
