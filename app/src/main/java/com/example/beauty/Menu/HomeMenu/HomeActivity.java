package com.example.beauty.Menu.HomeMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.beauty.Menu.HomeMenu.fragment.ScrollFragment;
import com.example.beauty.Menu.HomeMenu.fragment.SettingFragment;
import com.example.beauty.R;
import com.example.beauty.Menu.HomeMenu.fragment.UserAccount;
import com.example.beauty.Menu.WorkPhoto.AddPhotoFirebase;

public class HomeActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        GoHome(null); // bruh...


    }

    public void GoAccount(View view) {
        UserAccount user = new UserAccount();
        FragmentTransaction fragmentLPF = getSupportFragmentManager().beginTransaction();
        fragmentLPF.replace(R.id.Container, user);
        fragmentLPF.commit();

    }


    public void GoSetting(View view) {
        SettingFragment setting = new SettingFragment();
        FragmentTransaction fragmentLPF = getSupportFragmentManager().beginTransaction();
        fragmentLPF.replace(R.id.Container, setting);
        fragmentLPF.commit();

    }


    public void AddNewPhotoFirebase(View view){
        startActivity(new Intent(this, AddPhotoFirebase.class));
    }


    public void GoHome(View view) {
        ScrollFragment scroll = new ScrollFragment();
        FragmentTransaction fragmentLPF = getSupportFragmentManager().beginTransaction();
        fragmentLPF.replace(R.id.Container, scroll);
        fragmentLPF.commit();
    }



}