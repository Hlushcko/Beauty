package com.example.beauty.Menu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.beauty.AddPhotoFirebase;
import com.example.beauty.DatabaseLogic;
import com.example.beauty.Menu.FragmentMenu.ComentsFragment;
import com.example.beauty.Menu.FragmentMenu.ListPhotoFragment;
import com.example.beauty.Menu.FragmentMenu.PhotoFrameFragment;
import com.example.beauty.R;

import java.io.IOException;
import java.net.URL;

public class HomeActivity extends AppCompatActivity{

    private DatabaseLogic DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        GoHome(null);
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

        try {
            DB.getPhoto();
        }catch (NullPointerException exception){
            Log.w("Bruh", exception);
        }

    }


    public void GoSetting(View view) {
        ComentsFragment LPF = new ComentsFragment();
        FragmentTransaction fragmentLPF = getSupportFragmentManager().beginTransaction();
        fragmentLPF.replace(R.id.Container, LPF);
        fragmentLPF.commit();
    }

    public void AddNewPhotoFirebase(View view){
        startActivity(new Intent(this, AddPhotoFirebase.class));
    }


    private Bitmap ConvertUriToBitmap(String UriPhoto){
        Bitmap bitmapImage = null;

        try {
            URL url = new URL(UriPhoto);
            bitmapImage = BitmapFactory.decodeStream(url.openConnection().getInputStream());

        }catch (IOException exception){
            exception.printStackTrace();
        }

        return bitmapImage;
    }


    private void AddPhotoFragment(Bitmap bitmapPhoto) {

        FragmentManager fragMen = getSupportFragmentManager();
        FragmentTransaction fragTran = fragMen.beginTransaction();

        PhotoFrameFragment photo = new PhotoFrameFragment();
        fragTran.add(R.id.LinerContainer, photo);
        photo.SetPhoto(bitmapPhoto);
        fragTran.commit();

    }



}