package com.university.studytime.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.university.studytime.R;
import com.university.studytime.fragments.authorization.LoginFragment;
import com.university.studytime.fragments.authorization.RegisterFragment;
import com.university.studytime.fragments.TabAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ViewPager отвечает за показ и прокрутку, необходим для вкладок
        ViewPager viewPager = findViewById(R.id.viewPager);
        // Необходимо для ViewPager
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        // Добавление вкладки "Вход"
        adapter.addFragment(new LoginFragment(), "Вход");
        // Добавление вкладки "Регистрация"
        adapter.addFragment(new RegisterFragment(), "Регистрация");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
