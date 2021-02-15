package com.university.studytime.api;

import com.university.studytime.models.LoginModel;
import com.university.studytime.models.RegisterModel;
import com.university.studytime.models.SuccessModel;
import com.university.studytime.models.TimetableModel;
import com.university.studytime.models.UsersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

// В интерфейсе Api указывается тип запроса, который будет использоваться
// и конечный адрес, который будет добавляться к базовому URL.
// В Call указывается тип данных, который ожидается получить и параметры.
public interface Api {
    @POST("register.php")
    @FormUrlEncoded
    Call<RegisterModel> register(@Field("full_name") String full_name, @Field("login") String login, @Field("password") String password);

    @POST("login.php")
    @FormUrlEncoded
    Call<LoginModel> login(@Field("login") String login, @Field("password") String password);

    @POST("user.php")
    @FormUrlEncoded
    Call<List<TimetableModel>> userTimetable(@Field("group_code") String group_code);

    @POST("moderator.php")
    @FormUrlEncoded
    Call<List<TimetableModel>> moderator(@Field("group_code") String group_code);

    @POST("moderator.php")
    @FormUrlEncoded
    Call<TimetableModel> delete(@Field("id") Integer id);

    @POST("administrator.php")
    @FormUrlEncoded
    Call<List<UsersModel>> allUsers(@Field("message") String message);

    @POST("administrator.php")
    @FormUrlEncoded
    Call<UsersModel> roleChange(@Field("message") String message, @Field("id") int id, @Field("role_id") int role);

    @POST("administrator.php")
    @FormUrlEncoded
    Call<UsersModel> username(@Field("message") String message, @Field("username") String username);

    @POST("administrator.php")
    @FormUrlEncoded
    Call<UsersModel> deleteUser(@Field("message") String message, @Field("id") int id);

    @POST("existGroupCode.php")
    @FormUrlEncoded
    Call<SuccessModel> existGroupCode(@Field("group_code") String group_code);
}
