package com.example.beauty.database;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beauty.Menu.WorkPhoto.PostPhoto;
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

import java.util.Objects;


public class DatabaseLogic extends AppCompatActivity {

    private final transient String serverUrl= "https://beauty-e0204-default-rtdb.europe-west1.firebasedatabase.app";
    private final FirebaseDatabase firebase = FirebaseDatabase.getInstance(serverUrl);
    private final StorageReference storageRef = FirebaseStorage.getInstance().getReference("DataBasePhoto");
    private final StorageReference myStorage = storageRef.child(System.currentTimeMillis() + "image");
    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private final DatabaseReference realTimeDB = firebase.getReference("savingPostInfo");


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseApp.initializeApp(this);
    }

    public void register(String email, String password){

        if (!email.isEmpty() && !password.isEmpty()){

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    verification();
                }
            });

        }

    }


    public void logIn(String email, String password){

        if( !email.isEmpty() && !password.isEmpty()) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                }
            });
        }
    }

    private void verification(){
        Objects.requireNonNull(auth.getCurrentUser()).sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {

            }
        });
    }


    public void pushImageDatabase(byte[] bytePhoto, String description){

        UploadTask uploadTask = myStorage.putBytes(bytePhoto);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        pushPostRealTimeDataBase(task.getResult().toString(), description);
                    }
                });
            }
        });

    }


    public void getPhotoFirebase(DataChangeInfo dataInfo) {

        realTimeDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataInfo.dataOnDataChange(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Load post", error.toException());
            }
        });


    }

    private void pushPostRealTimeDataBase(String photoUri, String description){
        realTimeDB.push().setValue(new PostPhoto(description, photoUri));
    }

    public boolean checkLoginUser(){
        FirebaseUser user = auth.getCurrentUser();
        return user != null;
    }

    public boolean checkEmailVerification(){
        FirebaseUser user = auth.getCurrentUser();
        if(user != null){
            return true;
        }else {
            return false;
        }
        }

    public void resetPassword(String email){
        auth.sendPasswordResetEmail(email);
    }

    public void signOut(){
        FirebaseAuth.getInstance().signOut();
    }

}