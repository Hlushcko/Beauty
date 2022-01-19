package com.example.beauty.Menu.FragmentMenu;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import com.example.beauty.R;
import com.example.beauty.databinding.FragmentPhotoFrameBinding;


public class PhotoFrameFragment extends Fragment{

    ImageView imagePhoto;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo_frame, container, false);

        bundle = savedInstanceState;
        imagePhoto = view.findViewById(R.id.PhotoPeople);


        return inflater.inflate(R.layout.fragment_photo_frame, container, false);
    }


    public void setPhoto(){
        byte[] bytemap = bundle.getByteArray("photo");
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytemap, 0, bytemap.length);
        imagePhoto.setImageBitmap(bitmap);
    }



}