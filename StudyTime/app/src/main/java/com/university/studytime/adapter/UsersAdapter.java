package com.university.studytime.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.university.studytime.R;
import com.university.studytime.activity.AdministratorActivity;
import com.university.studytime.api.Api;
import com.university.studytime.api.ApiClient;
import com.university.studytime.models.UsersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//Данный класс отвечает за предоставление данных
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.RecyclerViewAdapter> {
    private List<UsersModel> usersModels;
    private Context context;

    public UsersAdapter(List<UsersModel> usersModels, Context context) {
        this.usersModels = usersModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_allusers, parent, false);
        return new RecyclerViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter holder, int position) {
        final UsersModel userModel = usersModels.get(position);
        holder.tv_login.setText(userModel.getLogin());
        holder.rb_user.setChecked(userModel.getRole() == 1);
        holder.rb_moder.setChecked(userModel.getRole() == 2);
        holder.rb_admin.setChecked(userModel.getRole() == 3);
        // Обработка нажатия на кнопку выбора роли пользователя
        holder.rb_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userModel.getRole() != 1) {
                    holder.b_confirm.setVisibility(View.VISIBLE);
                } else {
                    holder.b_confirm.setVisibility(View.INVISIBLE);
                }
            }
        });
        // Обработка нажатия на кнопку выбора роли модератора
        holder.rb_moder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userModel.getRole() != 2) {
                    holder.b_confirm.setVisibility(View.VISIBLE);
                } else {
                    holder.b_confirm.setVisibility(View.INVISIBLE);
                }
            }
        });
        // Обработка нажатия на кнопку выбора роли администратора
        holder.rb_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userModel.getRole() != 3) {
                    holder.b_confirm.setVisibility(View.VISIBLE);
                } else {
                    holder.b_confirm.setVisibility(View.INVISIBLE);
                }
            }
        });
        // Обработка нажатия на кнопку подтверждения изменения роли
        holder.b_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Api api = ApiClient.getClient().create(Api.class);
                final Call<UsersModel> roleChange;
                if (holder.rb_user.isChecked()) {
                    roleChange = api.roleChange("roleChange", userModel.getId(), 1);
                } else if (holder.rb_moder.isChecked()) {
                    roleChange = api.roleChange("roleChange", userModel.getId(), 2);
                } else {
                    roleChange = api.roleChange("roleChange", userModel.getId(), 3);
                }

                roleChange.enqueue(new Callback<UsersModel>() {
                    @Override
                    public void onResponse(@NonNull Call<UsersModel> call, @NonNull Response<UsersModel> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getSuccess() == 1) {
                                Toast.makeText(context, "Роль изменена!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, AdministratorActivity.class);
                                context.startActivity(intent);
                                ((Activity) context).overridePendingTransition(0,0);
                                ((Activity)context).finish();
                            } else {
                                Toast.makeText(context, "Ошибка!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "Ошибка!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UsersModel> call, @NonNull Throwable t) {
                    }
                });
            }
        });
        // Обработка нажатия на кнопку удаления пользователя
        holder.ib_deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Api api = ApiClient.getClient().create(Api.class);
                Call<UsersModel> deleteUser = api.deleteUser("deleteUser", userModel.getId());

                deleteUser.enqueue(new Callback<UsersModel>() {
                    @Override
                    public void onResponse(@NonNull Call<UsersModel> call, @NonNull Response<UsersModel> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getSuccess() == 1) {
                                Toast.makeText(context, "Пользователь удален!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, AdministratorActivity.class);
                                context.startActivity(intent);
                                ((Activity) context).overridePendingTransition(0,0);
                                ((Activity)context).finish();
                            } else {
                                Toast.makeText(context, "Ошибка!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "Ошибка!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UsersModel> call, @NonNull Throwable t) {
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersModels.size();
    }

    static class RecyclerViewAdapter extends RecyclerView.ViewHolder  {
        CardView cardView;
        TextView tv_login;
        RadioButton rb_user, rb_moder, rb_admin;
        Button b_confirm;
        ImageButton ib_deleteUser;

        RecyclerViewAdapter(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            tv_login = itemView.findViewById(R.id.login);
            rb_user = itemView.findViewById(R.id.user);
            rb_moder = itemView.findViewById(R.id.moder);
            rb_admin = itemView.findViewById(R.id.admin);
            b_confirm = itemView.findViewById(R.id.confirm);
            ib_deleteUser = itemView.findViewById(R.id.btnDeleteCard);
        }
    }
}

