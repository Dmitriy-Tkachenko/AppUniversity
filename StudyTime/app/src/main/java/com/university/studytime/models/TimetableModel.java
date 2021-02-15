package com.university.studytime.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// POJO класс TimetableModel используется для получения ответа с сервера данных в формате JSON.
// Каждая переменная аннотированная @SerializedName
// и имя в параметре аннотации в точности такое же, как в JSON ответе.
public class TimetableModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("day_name")
    @Expose
    private String dayName;

    @SerializedName("class_start_time")
    @Expose
    private String startTime;

    @SerializedName("class_end_time")
    @Expose
    private String endTime;

    @SerializedName("audience_name")
    @Expose
    private String audience;

    @SerializedName("number_seats")
    @Expose
    private String numberSeats;

    @SerializedName("discipline_name")
    @Expose
    private String discipline;

    @SerializedName("lesson_type_name")
    @Expose
    private String lessonType;

    @SerializedName("teacher_full_name")
    @Expose
    private String fullName;

    @SerializedName("isSuccess")
    @Expose
    private int isSuccess;

    protected TimetableModel(Parcel in) {
        id = in.readInt();
        dayName = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        audience = in.readString();
        numberSeats = in.readString();
        discipline = in.readString();
        lessonType = in.readString();
        fullName = in.readString();
        isSuccess = in.readInt();
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TimetableModel> CREATOR = new Parcelable.Creator<TimetableModel>() {
        @Override
        public TimetableModel createFromParcel(Parcel in) {
            return new TimetableModel(in);
        }

        @Override
        public TimetableModel[] newArray(int size) {
            return new TimetableModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(dayName);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(audience);
        dest.writeString(numberSeats);
        dest.writeString(discipline);
        dest.writeString(lessonType);
        dest.writeString(fullName);
        dest.writeInt(isSuccess);
    }

    public int getId() {
        return id;
    }

    public String getDayName() {
        return dayName;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getAudience() {
        return audience;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getLessonType() {
        return lessonType;
    }

    public String getFullName() {
        return fullName;
    }

    public int getIsSuccess() {
        return isSuccess;
    }
}
