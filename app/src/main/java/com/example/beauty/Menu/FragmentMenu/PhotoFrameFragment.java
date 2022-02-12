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

import com.bumptech.glide.Glide;
import com.example.beauty.R;


public class PhotoFrameFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo_frame, container, false);

        Bundle bundle = this.getArguments();

        assert bundle != null;
        String uri = bundle.getString("photo");

        ImageView imagePhoto = view.findViewById(R.id.PhotoPeople);
        Glide
                .with(this)
                .load(uri)
                .into(imagePhoto);

        return inflater.inflate(R.layout.fragment_photo_frame, container, false);
    }


}