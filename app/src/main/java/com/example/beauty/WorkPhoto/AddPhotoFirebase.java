package com.example.beauty.WorkPhoto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beauty.Menu.HomeMenu.HomeActivity;
import com.example.beauty.R;
import com.example.beauty.viewmodel.ViewModelFirebase;

import java.io.ByteArrayOutputStream;
import java.util.Objects;


public class AddPhotoFirebase extends AppCompatActivity {

    private ImageView photo;
    private ViewModelFirebase viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo_firebase);

        initialization();
    }


    private void initialization(){
        photo = findViewById(R.id.imageLoad);
        viewModel = new ViewModelProvider(this).get(ViewModelFirebase.class);
    }


    public void back(View view) {
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

    public void goGallery(View view) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, ""), 1);
    }

    public void pushInfoPost(View view) {

        TextView description = findViewById(R.id.InfoNewPost);

        if(description != null && photo != null) {
            viewModel.pushImageDatabase(convertedPhotoToByte(), description.getText().toString());
            back(null);
        }else{
            Toast.makeText(this, "image if description is empty", Toast.LENGTH_SHORT).show();
        }
    }


    private byte[] convertedPhotoToByte(){
        Bitmap bitmap = ((BitmapDrawable)photo.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        return baos.toByteArray();
    }


    public void logout(View view) {
        viewModel.signOut();
    }
}