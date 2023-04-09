package com.example.native_1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.native_1.Auth.LoginFragment;
import com.example.native_1.Home.HomeActivity;

public class MainActivity extends AppCompatActivity {


    Button btn_Hello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = getSupportActionBar();
        bar.hide();
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("isLogin")) {
            String isLogin = sharedPreferences.getString("isLogin", null);
            if (isLogin.equals("true")) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }

        FragmentManager fw = getSupportFragmentManager();
        FragmentTransaction ft = fw.beginTransaction();
        ft.replace(R.id.frameLayout, new LoginFragment());
        ft.commit();

//        btn_Hello = (Button) findViewById(R.id.btnHello);
//        btn_Hello.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
//                startActivity(intent);
//            }
//        });


    }
}