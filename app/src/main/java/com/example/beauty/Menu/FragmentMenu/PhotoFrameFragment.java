package com.example.beauty.Menu.FragmentMenu;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beauty.R;
import com.example.beauty.databinding.FragmentPhotoFrameBinding;


public class PhotoFrameFragment extends Fragment {

    FragmentPhotoFrameBinding photo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo_frame, container, false);
    }

    public void SetPhoto(Bitmap bitmap){
        photo.PhotoPeople.setImageBitmap(bitmap);
    }

}