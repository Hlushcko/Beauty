package com.example.beauty.Menu.StartMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beauty.Menu.HomeMenu.HomeActivity;
import com.example.beauty.R;
import com.example.beauty.viewmodel.ViewModelFirebase;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    ViewModelFirebase viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(ViewModelFirebase.class);
        CheckLoginUser();
    }


    public void LogIn(View view) {
        EditText email = findViewById(R.id.Email_edit_text);
        EditText password = findViewById(R.id.Password_edit_text);

        if(email.getText().toString().contains("@") && !password.getText().toString().isEmpty()){
            viewModel.logIn(email.getText().toString(), password.getText().toString());
        }else{
            Toast.makeText(this, "your mail or password is not valid", Toast.LENGTH_SHORT).show();
        }

        Single
                .timer(1, TimeUnit.SECONDS)
                .map(p -> {
                    CheckLoginUser();
                    return p; })
                .subscribe();
    }


    private void CheckLoginUser() {
        if (viewModel.userIsLoginAndVerification()) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }
    }


    public void ResetPassword(View view) {
        EditText email = findViewById(R.id.Email_edit_text);

        if(email.getText().toString().contains("@")) {
            viewModel.resetPassword(email.getText().toString());
            Toast.makeText(this, "check you email", Toast.LENGTH_LONG).show();
            email.setText("");
        }else{
            Toast.makeText(this, "Fill in the mail input field", Toast.LENGTH_SHORT).show();
        }
    }


    public void RegisterAccount(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }


}