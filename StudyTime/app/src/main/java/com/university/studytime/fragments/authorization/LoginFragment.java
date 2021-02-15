package com.university.studytime.fragments.authorization;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.university.studytime.R;
import com.university.studytime.activity.AdministratorActivity;
import com.university.studytime.activity.ModeratorActivity;
import com.university.studytime.activity.UserActivity;
import com.university.studytime.api.Api;
import com.university.studytime.api.ApiClient;
import com.university.studytime.models.LoginModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    private EditText login, password;
    private Vibrator vibrator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        Button loginBtn = rootView.findViewById(R.id.loginBtn);
        login = rootView.findViewById(R.id.login);
        password = rootView.findViewById(R.id.loginPassword);
        vibrator = (Vibrator) Objects.requireNonNull(getActivity()).getSystemService(Context.VIBRATOR_SERVICE);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUserData();
            }
        });

        return rootView;
    }
    // Проверка введенных данных на корректность
    private void validateUserData() {
        final String userLogin = login.getText().toString();
        final String userPassword = password.getText().toString();

        if (TextUtils.isEmpty(userLogin)) {
            login.setError("Поле не может быть пустым");
            login.requestFocus();
            vibrator.vibrate(100);
            return;
        }

        if (TextUtils.isEmpty(userPassword)) {
            password.setError("Поле не может быть пустым");
            password.requestFocus();
            vibrator.vibrate(100);
            return;
        }

        loginUser(userLogin, userPassword);
    }
    // Проверка роли пользователя и предоставление ему соответствующего активити в зависимости от роли
    private void loginUser(String userLogin, String userPassword) {
        Api api = ApiClient.getClient().create(Api.class);
        Call<LoginModel> login = api.login(userLogin, userPassword);

        login.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (Objects.requireNonNull(response.body()).getIsSuccess() == 1) {
                        if (response.body().getRole() == 1) {
                            startActivity(new Intent(getActivity(), UserActivity.class));
                            Objects.requireNonNull(getActivity()).finish();
                        }
                        if (response.body().getRole() == 2) {
                            startActivity(new Intent(getActivity(), ModeratorActivity.class));
                            Objects.requireNonNull(getActivity()).finish();
                        }
                        if (response.body().getRole() == 3) {
                            startActivity(new Intent(getActivity(), AdministratorActivity.class));
                            Objects.requireNonNull(getActivity()).finish();
                        }
                    } else {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {

            }
        });
    }
}
