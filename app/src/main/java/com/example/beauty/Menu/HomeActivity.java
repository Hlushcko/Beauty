package com.example.beauty.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.beauty.AddPhotoFirebase;
import com.example.beauty.Menu.FragmentMenu.ComentsFragment;
import com.example.beauty.Menu.FragmentMenu.ListPhotoFragment;
import com.example.beauty.Menu.FragmentMenu.PhotoFrameFragment;
import com.example.beauty.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }


    public void GoAccount(View view) {
        ComentsFragment LPF = new ComentsFragment();
        FragmentTransaction fragmentLPF = getSupportFragmentManager().beginTransaction();
        fragmentLPF.replace(R.id.Container, LPF);
        fragmentLPF.commit();
    }


    public void GoHome(View view) {
        ListPhotoFragment LPF = new ListPhotoFragment();
        FragmentTransaction fragmentLPF = getSupportFragmentManager().beginTransaction();
        fragmentLPF.replace(R.id.Container, LPF);
        fragmentLPF.commit();


    }


    public void GoSetting(View view) {
        ComentsFragment LPF = new ComentsFragment();
        FragmentTransaction fragmentLPF = getSupportFragmentManager().beginTransaction();
        fragmentLPF.replace(R.id.Container, LPF);
        fragmentLPF.commit();
    }

    private void AddPhotoFragment(){
        FragmentManager fragMen = getSupportFragmentManager();
        FragmentTransaction fragTran = fragMen.beginTransaction();

        PhotoFrameFragment photo = new PhotoFrameFragment();
        fragTran.add(R.id.LinerContainer, photo);
        //photo.setPhoto();

        fragTran.commit();

    }

    public void AddNewPhotoFirebase(View view){
        startActivity(new Intent(this, AddPhotoFirebase.class));
    }



}