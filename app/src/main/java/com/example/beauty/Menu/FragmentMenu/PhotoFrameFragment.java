package com.example.beauty.Menu.FragmentMenu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.beauty.R;


public class PhotoFrameFragment extends Fragment{


    private static final String KEY_URL_PHOTO = "Photo";

    ImageView imagePhoto;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        assert bundle != null;
        String uri = bundle.getString(KEY_URL_PHOTO);

        view = inflater.inflate(R.layout.fragment_photo_frame, container, false);
        imagePhoto = (ImageView) view.findViewById(R.id.PhotoPeople);

        Log.w("Load post", uri);

        Glide.with(imagePhoto.getContext()).load(uri).into(imagePhoto);

        return inflater.inflate(R.layout.fragment_photo_frame, container, false);
    }


}