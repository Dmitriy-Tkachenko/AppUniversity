package com.university.studytime.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimetableModel {

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

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("isSuccess")
    @Expose
    private int isSuccess;

    public TimetableModel(String dayName, String startTime, String endTime, String audience, String numberSeats, String discipline, String lessonType, String fullName, String message, int isSuccess) {
        this.dayName = dayName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.audience = audience;
        this.numberSeats = numberSeats;
        this.discipline = discipline;
        this.lessonType = lessonType;
        this.fullName = fullName;
        this.message = message;
        this.isSuccess = isSuccess;
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

    public String getNumberSeats() {
        return numberSeats;
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

    public String getMessage() {
        return message;
    }

    public int getIsSuccess() {
        return isSuccess;
    }


    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public void setNumberSeats(String numberSeats) {
        this.numberSeats = numberSeats;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }
}
