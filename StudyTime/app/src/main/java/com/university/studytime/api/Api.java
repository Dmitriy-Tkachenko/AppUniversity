package com.university.studytime.api;

import com.university.studytime.models.LoginModel;
import com.university.studytime.models.RegisterModel;
import com.university.studytime.models.TimetableModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @POST("register.php")
    @FormUrlEncoded
    Call<RegisterModel> register(@Field("full_name") String full_name, @Field("login") String login, @Field("password") String password);

    @POST("login.php")
    @FormUrlEncoded
    Call<LoginModel> login(@Field("login") String login, @Field("password") String password);

    @POST("user.php")
    @FormUrlEncoded
    Call<List<TimetableModel>> timetable(@Field("group_code") String group_code);
}
