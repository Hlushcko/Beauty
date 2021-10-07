package com.example.beauty;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.beauty.Menu.HomeActivity;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class AddPhotoFirebase extends AppCompatActivity {

    ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo_firebase);
        Initialization();
    }

    private void Initialization(){
        photo = findViewById(R.id.imageLoad);
    }

    public void Back(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && Objects.requireNonNull(data).getData() != null){
            if(resultCode == RESULT_OK){
                photo.setImageURI(data.getData());
            }
        }

    }

    public void GoGallery(View view) {

        Intent intentGallery = new Intent();
        intentGallery.setType("image/");
        intentGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentGallery, 1);

    }

    public void PushInfoPost(View view) {

    }

    private byte[] ConvertedPhotoToByte(){

        Bitmap bitmap = ((BitmapDrawable)photo.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();

    }



}