package com.university.studytime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.university.studytime.R;
import com.university.studytime.api.Api;
import com.university.studytime.api.ApiClient;
import com.university.studytime.fragments.timetable.FridayFragment;
import com.university.studytime.fragments.timetable.MondayFragment;
import com.university.studytime.fragments.timetable.SaturdayFragment;
import com.university.studytime.fragments.TabAdapter;
import com.university.studytime.fragments.timetable.ThursdayFragment;
import com.university.studytime.fragments.timetable.TuesdayFragment;
import com.university.studytime.fragments.timetable.WednesdayFragment;
import com.university.studytime.models.SuccessModel;
import com.university.studytime.models.TimetableModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModeratorActivity extends AppCompatActivity {
    private List<TimetableModel> timetableList, mondayList, tuesdayList, wednesdayList, thursdayList, fridayList, saturdayList;
    private String codeGroup;
    private TabLayout tabLayout;
    private TextView requestCode;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moderator);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(5);
        tabLayout = findViewById(R.id.tabLayout);
        requestCode = findViewById(R.id.request_code);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu_moder, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        //Поле поиска номера группы
        final SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);
        // Обработка ввода данных в поле поиска номера группы
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                existGroupCode(codeGroup);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                codeGroup = newText;
                return false;
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

    //Проверка базы данных на наличи введенного номера группы
    private void existGroupCode(String codeGroup) {
        Api api = ApiClient.getClient().create(Api.class);
        Call<SuccessModel> existGroupCode = api.existGroupCode(codeGroup);

        existGroupCode.enqueue(new Callback<SuccessModel>() {
            @Override
            public void onResponse(@NonNull Call<SuccessModel> call, @NonNull Response<SuccessModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getIsSuccess() == 1) {
                        requestCode.setVisibility(View.GONE);
                        iteratorDay();
                    } else {
                        Toast.makeText(getBaseContext(), "Группа не найдена!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SuccessModel> call, @NonNull Throwable t) {
            }
        });
    }
    //Получение рассписания группы
    private void iteratorDay() {
        Api api = ApiClient.getClient().create(Api.class);
        Call<List<TimetableModel>> existGroupCode = api.moderator(codeGroup);

        existGroupCode.enqueue(new Callback<List<TimetableModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<TimetableModel>> call, @NonNull Response<List<TimetableModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    timetableList = new ArrayList<>();
                    mondayList = new ArrayList<>();
                    tuesdayList = new ArrayList<>();
                    wednesdayList = new ArrayList<>();
                    thursdayList = new ArrayList<>();
                    fridayList = new ArrayList<>();
                    saturdayList = new ArrayList<>();
                    timetableList = response.body();
                    //Распределение расписания в списки по дню недели
                    for (int i = 0; i < timetableList.size(); i++) {
                        switch (timetableList.get(i).getDayName()) {
                            case "Понедельник":
                                mondayList.add(timetableList.get(i));
                                break;
                            case "Вторник":
                                tuesdayList.add(timetableList.get(i));
                                break;
                            case "Среда":
                                wednesdayList.add(timetableList.get(i));
                                break;
                            case "Четверг":
                                thursdayList.add(timetableList.get(i));
                                break;
                            case "Пятница":
                                fridayList.add(timetableList.get(i));
                                break;
                            case "Суббота":
                                saturdayList.add(timetableList.get(i));
                                break;
                        }
                    }
                    createFragments();

                } else {
                    Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<TimetableModel>> call, @NonNull Throwable t) {
            }
        });
    }
    //Создание вкладок с соответствующим днем недели
    private void createFragments() {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(MondayFragment.newInstance(mondayList), "Понедельник");
        adapter.addFragment(TuesdayFragment.newInstance(tuesdayList), "Вторник");
        adapter.addFragment(WednesdayFragment.newInstance(wednesdayList), "Среда");
        adapter.addFragment(ThursdayFragment.newInstance(thursdayList), "Четверг");
        adapter.addFragment(FridayFragment.newInstance(fridayList), "Пятница");
        adapter.addFragment(SaturdayFragment.newInstance(saturdayList), "Суббота");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
