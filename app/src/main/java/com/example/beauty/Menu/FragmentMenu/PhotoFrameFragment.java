package com.example.beauty.Menu.FragmentMenu;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.beauty.R;

public class PhotoFrameFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo_frame, container, false);
    }

    public void setPhoto(ImageView image, Bitmap bitmapImage){
        image.findViewById(R.id.PhotoPeople);
        image.setImageBitmap(bitmapImage);
    }



}