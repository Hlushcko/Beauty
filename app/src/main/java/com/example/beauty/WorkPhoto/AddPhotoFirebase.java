package com.example.beauty.WorkPhoto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beauty.DatabaseLogic;
import com.example.beauty.Menu.HomeActivity;
import com.example.beauty.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Objects;

public class AddPhotoFirebase extends AppCompatActivity {

    ImageView photo;
    DatabaseLogic DB = new DatabaseLogic();

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

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, ""), 1);

    }

    public void PushInfoPost(View view) {

        TextView description = findViewById(R.id.InfoNewPost);

        if(description != null && photo != null) {
            DB.PushImageDatabase(ConvertedPhotoToByte(), description.getText().toString());

        }

    }

    private byte[] ConvertedPhotoToByte(){

        Bitmap bitmap = ((BitmapDrawable)photo.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();

    }


    public void logout(View view) {
        DB.SignOut();
    }
}