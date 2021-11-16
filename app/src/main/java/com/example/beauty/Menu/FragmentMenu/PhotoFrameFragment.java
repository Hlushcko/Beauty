package com.example.beauty.Menu.FragmentMenu;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.beauty.R;

import java.util.Objects;
import java.util.zip.Inflater;

public class PhotoFrameFragment extends Fragment {

    public static FragmentManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        manager = getChildFragmentManager();
        return inflater.inflate(R.layout.fragment_photo_frame, container, false);
    }

    public void SetPhoto(){
        Fragment image = manager.findFragmentById(R.id.PhotoPeople);

    }

}