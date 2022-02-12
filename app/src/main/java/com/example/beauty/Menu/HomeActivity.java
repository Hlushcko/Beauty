package com.example.beauty.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.beauty.Menu.FragmentMenu.PhotoFrameFragment;
import com.example.beauty.WorkPhoto.AddPhotoFirebase;
import com.example.beauty.Menu.FragmentMenu.ComentsFragment;
import com.example.beauty.R;
import com.example.beauty.WorkPhoto.PostPhoto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity{

    private final transient String serverUrl= "https://beauty-e0204-default-rtdb.europe-west1.firebasedatabase.app";
    private final FirebaseDatabase firebase = FirebaseDatabase.getInstance(serverUrl);
    private final DatabaseReference realTimeDB = firebase.getReference("savingPostInfo");

    private static final int MAX_LENGTH_POST_PHOTO = 20;


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
//        FragmentTransaction fragmentLPF = getSupportFragmentManager().beginTransaction();
//        fragmentLPF.replace(R.id.Container, );
//        fragmentLPF.commit();
//
        getPhotoFirebase();
    }

    public void getPhotoFirebase() {

        realTimeDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int size = 0;
                for(DataSnapshot data : snapshot.getChildren()) {

                    PostPhoto post = data.getValue(PostPhoto.class);
                    assert post != null;
                    try {
                        AddPhotoFragment(post.uriPhoto);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    size++;
                    if (size == MAX_LENGTH_POST_PHOTO) {
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Load post", error.toException());
            }
        });
    }

    private void AddPhotoFragment(String uriPhoto) {

        FragmentTransaction fragTran = getSupportFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString("photo", uriPhoto);

        PhotoFrameFragment pff = new PhotoFrameFragment();
        pff.setArguments(bundle);

        Log.w("Load post", uriPhoto);

        fragTran.replace(R.id.Container, pff);
        fragTran.commit();

    }

}