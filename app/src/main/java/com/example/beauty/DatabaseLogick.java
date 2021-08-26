package com.example.beauty;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class DatabaseLogick extends AppCompatActivity {

    private final FirebaseAuth auth = FirebaseAuth.getInstance();

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

        if(!Objects.isNull(email) && !Objects.isNull(password)) {
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