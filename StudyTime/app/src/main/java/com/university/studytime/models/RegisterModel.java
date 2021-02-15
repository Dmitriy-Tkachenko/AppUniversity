package com.university.studytime.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// POJO класс RegisterModel используется для получения ответа с сервера данных в формате JSON.
// Каждая переменная аннотированная @SerializedName
// и имя в параметре аннотации в точности такое же, как в JSON ответе.
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

    @SerializedName("confirm_password")
    @Expose
    private String confirmPassword;

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

    public String getMessage() {
        return message;
    }

    public int getIsSuccess() {
        return isSuccess;
    }
}
