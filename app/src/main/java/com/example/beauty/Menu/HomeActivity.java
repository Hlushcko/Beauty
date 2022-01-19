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
import com.example.beauty.PostPhoto;
import com.example.beauty.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity{

    private DatabaseLogic DB = new DatabaseLogic();
    private ArrayList<PostPhoto> ph;

    private static final ListPhotoFragment listPhoto = new ListPhotoFragment();


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

    public void GoSetting(View view) {
        ComentsFragment LPF = new ComentsFragment();
        FragmentTransaction fragmentLPF = getSupportFragmentManager().beginTransaction();
        fragmentLPF.replace(R.id.Container, LPF);
        fragmentLPF.commit();
    }

    public void AddNewPhotoFirebase(View view){
        startActivity(new Intent(this, AddPhotoFirebase.class));
    }

    public void GoHome(View view) {
        FragmentTransaction fragmentLPF = getSupportFragmentManager().beginTransaction();
        fragmentLPF.replace(R.id.Container, listPhoto);
        fragmentLPF.commit();
        StreamPostPhoto();
    }

    private void StreamPostPhoto( ){

        new Thread(new Runnable() {
            @Override
            public void run() {

                DB.getPhotoFirebase();
                ph = new ArrayList<PostPhoto>();
                Log.w("Size " + ph.size(), "lol");
                for(PostPhoto photo : ph){
                    AddPhotoFragment(ConvertBitmapToByte(ConvertUriToBitmap(photo.getUriPhoto())));
                }

            }
        });

//        DB.getPhotoFirebase();
//        ph = new ArrayList<PostPhoto>();
//        Log.w("Size " + ph.size(), "lol");
//        for(PostPhoto photo : ph){
//            AddPhotoFragment(ConvertBitmapToByte(ConvertUriToBitmap(photo.getUriPhoto())));
//        }
    }


    private byte[] ConvertBitmapToByte(Bitmap map){
        ByteArrayOutputStream arrayByte = new ByteArrayOutputStream();
        map.compress(Bitmap.CompressFormat.JPEG, 90, arrayByte);
        return arrayByte.toByteArray();
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


    private void AddPhotoFragment(byte[] bytesPhoto) {

//        FragmentManager fragMen = getSupportFragmentManager();
//        FragmentTransaction fragTran = fragMen.beginTransaction();
//
//        PhotoFrameFragment photo = new PhotoFrameFragment();
//        fragTran.add(R.id.LinerContainer, photo);
//
//        photo.setPhoto();
//        fragTran.commit();

        FragmentManager fragMen = getSupportFragmentManager();
        FragmentTransaction fragTran = fragMen.beginTransaction();


        Bundle bundle = new Bundle();
        bundle.putByteArray("photo", bytesPhoto);

        PhotoFrameFragment phf = new PhotoFrameFragment();
        phf.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Container, phf)
                .commit();
        phf.setPhoto();

    }



}