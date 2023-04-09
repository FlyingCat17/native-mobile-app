package com.example.native_1.Home;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.native_1.Auth.LoginFragment;
import com.example.native_1.Home.Dashboard.DashboardFragment;
import com.example.native_1.R;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        FragmentManager fw = getSupportFragmentManager();
//        FragmentTransaction ft = fw.beginTransaction();
//        ft.replace(R.id.container, new DashboardFragment());
//        ft.commit();
        FragmentManager fw = getSupportFragmentManager();
        FragmentTransaction ft = fw.beginTransaction();
        ft.replace(R.id.fragmentMain, new DashboardFragment());
        ft.commit();
    }
}