package com.example.beauty;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.beauty.Menu.HomeActivity;
import com.example.beauty.StartMenu.RegisterActivity;

public class AddPhotoFirebase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo_firebase);
    }

    public void Back(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void GoGallery(View view) {

        Intent intentGallery = new Intent();
        intentGallery.setType("image/");
        intentGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentGallery, 1);

    }

    public void PushInfoPost(View view) {

    }



}