package com.example.beauty.Menu.FragmentMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.beauty.R;


public class PhotoFrameFragment extends Fragment{

    private static final String KEY_URL_PHOTO = "Photo";

    private String uri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        assert bundle != null;
        uri = bundle.getString(KEY_URL_PHOTO);

        return inflater.inflate(R.layout.fragment_photo_frame, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();

        View view = getView();
        if(view != null){
            ImageView image = (ImageView) view.findViewById(R.id.PhotoPeople);

            Glide
                .with(this)
                .load(uri)
                .error(R.drawable.ic_launcher_foreground)
                .centerCrop()
                .into(image);
        }

    }


}