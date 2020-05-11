package com.university.studytime.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.university.studytime.R;
import com.university.studytime.fragments.MondayFragment;
import com.university.studytime.fragments.TabAdapter;
import com.university.studytime.fragments.TuesdayFragment;

public class UserActivity extends AppCompatActivity {
    private String codeGroup;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        /*adapter.addFragment(new MondayFragment(), "Понедельник");
        adapter.addFragment(new TuesdayFragment(), "Вторник");
        adapter.addFragment(new TuesdayFragment(), "Среда");
        adapter.addFragment(new TuesdayFragment(), "Четверг");
        adapter.addFragment(new TuesdayFragment(), "Пятница");
        adapter.addFragment(new TuesdayFragment(), "Суббота");*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE); //Без этого поле ввода лишь на ~2/3 экрана в портретном режиме, а в ланшафтном на ~1/3
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.addFragment(new MondayFragment(codeGroup), "Понедельник");
                adapter.addFragment(new TuesdayFragment(codeGroup), "Вторник");
                adapter.addFragment(new TuesdayFragment(codeGroup), "Среда");
                adapter.addFragment(new TuesdayFragment(codeGroup), "Четверг");
                adapter.addFragment(new TuesdayFragment(codeGroup), "Пятница");
                adapter.addFragment(new TuesdayFragment(codeGroup), "Суббота");
                viewPager.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter = new TabAdapter(getSupportFragmentManager());
                tabLayout.setupWithViewPager(viewPager);
                codeGroup = newText;
                return false;
            }
        });
        return true;
    }
}
