package com.university.studytime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.university.studytime.R;
import com.university.studytime.api.Api;
import com.university.studytime.api.ApiClient;
import com.university.studytime.fragments.TabAdapter;
import com.university.studytime.fragments.allusers.AdminsFragment;
import com.university.studytime.fragments.allusers.ModeratorsFragment;
import com.university.studytime.fragments.allusers.SearchUserFragment;
import com.university.studytime.fragments.allusers.UsersFragment;
import com.university.studytime.models.UsersModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdministratorActivity extends AppCompatActivity {
    private List<UsersModel> allUsersList, usersList, moderatorsList, adminsList;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String username;
    private FrameLayout frameLayout;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        frameLayout = findViewById(R.id.frame_layout);

        iteratorAllUsers();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu_admin, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        //Поле поиска логина
        final SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);
        // Обработка ввода данных в поле поиска логина
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchUser(username);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                username = newText;
                return false;
            }
        });

        menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                frameLayout.setVisibility(View.GONE);
                return true;
            }
        });
        return true;
    }

    // Кнопка опций
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Выход из аккаунта
        if (item.getItemId() == R.id.more_vert) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //Проверка базы данных на наличие введенного логина
    private void searchUser(String username) {
        Api api = ApiClient.getClient().create(Api.class);
        Call<UsersModel> searchUser = api.username("searchUser", username);

        searchUser.enqueue(new Callback<UsersModel>() {
            @Override
            public void onResponse(@NonNull Call<UsersModel> call, @NonNull Response<UsersModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getSuccess() == 1) {
                        allUsersList = new ArrayList<>();
                        allUsersList.add(response.body());
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, SearchUserFragment.newInstance(allUsersList));
                        frameLayout.setVisibility(View.VISIBLE);
                        fragmentTransaction.commit();
                    } else if (response.body().getSuccess() == 0){
                        Toast.makeText(getBaseContext(), "Пользователь не найден!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UsersModel> call, @NonNull Throwable t) {
            }
        });
    }

    //Получение данных всех пользователей
    private void iteratorAllUsers() {
        Api api = ApiClient.getClient().create(Api.class);
        Call<List<UsersModel>> allUsers = api.allUsers("allUsers");

        allUsers.enqueue(new Callback<List<UsersModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<UsersModel>> call, @NonNull Response<List<UsersModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    allUsersList = new ArrayList<>();
                    usersList = new ArrayList<>();
                    moderatorsList = new ArrayList<>();
                    adminsList = new ArrayList<>();

                    allUsersList = response.body();
                    //Распределение пользователей в списки по ролям
                    for (int i = 0; i < allUsersList.size(); i++) {
                        switch (allUsersList.get(i).getRole()) {
                            case 1:
                                usersList.add(allUsersList.get(i));
                                break;
                            case 2:
                                moderatorsList.add(allUsersList.get(i));
                                break;
                            case 3:
                                adminsList.add(allUsersList.get(i));
                                break;
                        }
                    }
                    createFragments();

                } else {
                    Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<UsersModel>> call, @NonNull Throwable t) {
            }
        });
    }
    //Создание вкладок с соответствующей ролью пользователя
    private void createFragments() {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(UsersFragment.newInstance(usersList), "Пользователи");
        adapter.addFragment(ModeratorsFragment.newInstance(moderatorsList), "Модераторы");
        adapter.addFragment(AdminsFragment.newInstance(adminsList), "Администраторы");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
