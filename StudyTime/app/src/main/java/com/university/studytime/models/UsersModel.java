package com.university.studytime.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// POJO класс UsersModel используется для получения ответа с сервера данных в формате JSON.
// Каждая переменная аннотированная @SerializedName
// и имя в параметре аннотации в точности такое же, как в JSON ответе.
public class UsersModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("role")
    @Expose
    private int role;

    @SerializedName("isSuccess")
    @Expose
    private int success;

    protected UsersModel(Parcel in) {
        id = in.readInt();
        login = in.readString();
        role = in.readInt();
        success = in.readInt();
    }

    public static final Creator<UsersModel> CREATOR = new Creator<UsersModel>() {
        @Override
        public UsersModel createFromParcel(Parcel in) {
            return new UsersModel(in);
        }

        @Override
        public UsersModel[] newArray(int size) {
            return new UsersModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(login);
        dest.writeInt(role);
        dest.writeInt(success);
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public int getRole() {
        return role;
    }

    public int getSuccess() {
        return success;
    }
}
