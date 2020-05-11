package com.university.studytime.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.university.studytime.R;
import com.university.studytime.fragments.LoginFragment;
import com.university.studytime.fragments.RegisterFragment;
import com.university.studytime.fragments.TabAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.viewPager2);
        TabLayout tabLayout = findViewById(R.id.tabLayout2);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment(), "Вход");
        adapter.addFragment(new RegisterFragment(), "Регистрация");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
