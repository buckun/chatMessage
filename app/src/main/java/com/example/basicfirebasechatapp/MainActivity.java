package com.example.basicfirebasechatapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar_actionbar_main;
    private ViewPager viewPager_main;
    private TabLayout tabLayout_main;
    private TabsAdapter tabsAdapter;

    private FirebaseUser currentUser;
    private FirebaseAuth auth;

    public void init() {
        toolbar_actionbar_main = findViewById(R.id.action_bar_main);
        setSupportActionBar(toolbar_actionbar_main);
        getSupportActionBar().setTitle(R.string.app_name);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        viewPager_main = findViewById(R.id.view_pager_for_main);
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        viewPager_main.setAdapter(tabsAdapter);

        tabLayout_main = findViewById(R.id.tab_layout_main);
        tabLayout_main.setupWithViewPager(viewPager_main);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onStart() {

        if (currentUser == null) {
            Intent intent_login = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent_login);
            finish();
        }
        super.onStart();
    }
}
