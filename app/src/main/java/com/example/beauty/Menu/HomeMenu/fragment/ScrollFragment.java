package com.example.beauty.Menu.HomeMenu.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beauty.database.DataChangeInfo;
import com.example.beauty.R;
import com.example.beauty.Menu.WorkPhoto.PostPhoto;
import com.example.beauty.recyclerview.RecycleViewFragment;
import com.example.beauty.viewmodel.ViewModelFirebase;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;


public class ScrollFragment extends Fragment implements DataChangeInfo {

    private RecycleViewFragment myRecycler;
    private RecyclerView recycler;
    private MutableLiveData<List<PostPhoto>> liveDataPost;

    private ViewModelFirebase viewModelFirebase;
    private View view;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_scroll, container, false);
        view = fragmentView;

        return fragmentView;
    }


    @Override
    public void onStart() {
        super.onStart();

        init();
        checkData();
    }


    private void init(){
        liveDataPost = new MutableLiveData<>();

        recycler = view.findViewById(R.id.recyclerContainer);
        myRecycler = new RecycleViewFragment(view.getContext());
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recycler.setAdapter(myRecycler);

        viewModelFirebase = new ViewModelProvider(requireActivity()).get(ViewModelFirebase.class);
    }


    private void checkData(){
        viewModelFirebase.getPhotoFirebase(this);
        liveDataPost.observe(this, new Observer<List<PostPhoto>>() {
            @Override
            public void onChanged(List<PostPhoto> postPhotos) {
                myRecycler.setList(postPhotos);
            }
        });
    }


    @Override
    public void dataOnDataChange(DataSnapshot snapshot) {

        List<PostPhoto> postData = new ArrayList<>();
        for(DataSnapshot data : snapshot.getChildren()) {

            postData.add(data.getValue(PostPhoto.class));
            liveDataPost.setValue(postData);

        }
    }

}