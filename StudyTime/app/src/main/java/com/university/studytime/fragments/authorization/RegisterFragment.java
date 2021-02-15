package com.university.studytime.fragments.authorization;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.university.studytime.R;
import com.university.studytime.activity.UserActivity;
import com.university.studytime.api.Api;
import com.university.studytime.api.ApiClient;
import com.university.studytime.models.RegisterModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    private EditText fullName, login, password, conFirmPassword;
    private Vibrator vibrator;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        Button regBtn = rootView.findViewById(R.id.regBtn);
        fullName = rootView.findViewById(R.id.regFullName);
        login = rootView.findViewById(R.id.regLogin);
        password = rootView.findViewById(R.id.regPassword);
        conFirmPassword = rootView.findViewById(R.id.confirmPassword);
        vibrator = (Vibrator) Objects.requireNonNull(getActivity()).getSystemService(Context.VIBRATOR_SERVICE);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUserData();
            }
        });
        return rootView;
    }
    // Проверка введенных данных на корректность
    private void validateUserData() {
        final String reg_full_name = fullName.getText().toString();
        final String reg_login = login.getText().toString();
        final String reg_password = password.getText().toString();
        final String reg_confirm_password = conFirmPassword.getText().toString();

        if (TextUtils.isEmpty(reg_full_name)) {
            fullName.setError("Поле не может быть пустым");
            fullName.requestFocus();
            vibrator.vibrate(100);
            return;
        }

        if (reg_full_name.length() > 100) {
            fullName.setError("Поле не может содержать более 100 символов");
            fullName.requestFocus();
            vibrator.vibrate(100);
            return;
        }

        if (TextUtils.isEmpty(reg_login)) {
            login.setError("Поле не может быть пустым");
            login.requestFocus();
            vibrator.vibrate(100);
            return;
        }

        if (login.length() > 30) {
            login.setError("Поле не может содержать более 30 символов");
            login.requestFocus();
            vibrator.vibrate(100);
            return;
        }

        if (reg_password.length() < 5 || reg_password.length() > 30) {
            password.setError("Пароль должен содержать от 5 до 30 символов");
            password.requestFocus();
            vibrator.vibrate(100);
            return;
        }

        if (!reg_password.equals(reg_confirm_password)) {
            conFirmPassword.setError("Пароли не совпадают");
            password.setError("Пароли не совпадают");
            conFirmPassword.requestFocus();
            password.requestFocus();
            vibrator.vibrate(100);
            return;
        }

        registerUser(reg_full_name, reg_login, reg_password);
    }

    // Передача в базу данных введенных данных для регистрации
    private void registerUser(String user_full_name, String user_login, String user_password) {
        Api api = ApiClient.getClient().create(Api.class);
        Call<RegisterModel> register = api.register(user_full_name, user_login, user_password);

        register.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(@NonNull Call<RegisterModel> call, @NonNull Response<RegisterModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getIsSuccess() == 1) {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), UserActivity.class));
                        Objects.requireNonNull(getActivity()).finish();
                    } else {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterModel> call, @NonNull Throwable t) {
                Log.e(String.valueOf(getActivity()), Objects.requireNonNull(t.getLocalizedMessage()));
            }
        });
    }
}
