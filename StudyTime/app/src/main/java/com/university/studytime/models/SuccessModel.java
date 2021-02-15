package com.university.studytime.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// POJO класс SuccessModel используется для получения ответа с сервера данных в формате JSON.
// Каждая переменная аннотированная @SerializedName
// и имя в параметре аннотации в точности такое же, как в JSON ответе.
public class SuccessModel {
    @SerializedName("isSuccess")
    @Expose
    private int isSuccess;

    public SuccessModel(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getIsSuccess() {
        return isSuccess;
    }
}
