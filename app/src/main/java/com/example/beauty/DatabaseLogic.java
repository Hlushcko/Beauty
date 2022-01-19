package com.example.beauty;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;


public class DatabaseLogic extends AppCompatActivity {

    private final String serverUrl= "https://beauty-e0204-default-rtdb.europe-west1.firebasedatabase.app";
    private final FirebaseDatabase firebase = FirebaseDatabase.getInstance(serverUrl);
    private final StorageReference storageRef = FirebaseStorage.getInstance().getReference("DataBasePhoto");
    private final StorageReference myStorage = storageRef.child(System.currentTimeMillis() + "image");
    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private final DatabaseReference RealTimeDB = firebase.getReference("savingPostInfo");



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseApp.initializeApp(this);
    }

    public void Register(String email, String password, String password_confirm){

        Boolean email_boolean = Check_null(email);
        Boolean password_boolean = Check_null(password);

        if (email_boolean && password_boolean){

            if(Objects.equals(password, password_confirm)){

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                Verification();
                            }
                        });
            }
        }
    }


    public void LogIn(String email, String password){

        Boolean emailBoolean = Check_null(email);
        Boolean passwordBoolean = Check_null(password);


        if( emailBoolean && passwordBoolean) {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        }
                    });
        }
    }

    private void Verification(){
        Objects.requireNonNull(auth.getCurrentUser()).sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {

            }
        });
    }


    public void PushImageDatabase(byte[] bytePhoto, String description){

        UploadTask uploadTask = myStorage.putBytes(bytePhoto);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        PushPostRealTimeDataBase(task.getResult().toString(), description);
                    }
                });
            }
        });

    }


    static ArrayList<PostPhoto> photo = new ArrayList<>();

    public void addPostPhotoToArray(PostPhoto postPhoto){
        photo.add(postPhoto);
    }

    public void getPhotoFirebase() {

        RealTimeDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int size = 0;
                for(DataSnapshot data : snapshot.getChildren()) {
                    addPostPhotoToArray(data.getValue(PostPhoto.class));
                    size++;
                    if (size == 20) {
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

    private void PushPostRealTimeDataBase(String photoUri, String description){
        RealTimeDB.push().setValue(new PostPhoto(description, photoUri));
    }


    public boolean CheckLoginUser(){
        FirebaseUser user = auth.getCurrentUser();
        return user != null;
    }

    public boolean CheckEmailVerification(){
        FirebaseUser user = auth.getCurrentUser();
        return Objects.requireNonNull(user).isEmailVerified();
    }

    public void ResetPassword(String email){
        auth.sendPasswordResetEmail(email);
    }

    public void SignOut(){
        FirebaseAuth.getInstance().signOut();
    }

    private Boolean Check_null(String check){
        return !TextUtils.isEmpty(check);
    }


}