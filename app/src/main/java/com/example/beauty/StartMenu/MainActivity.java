package com.example.beauty.StartMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beauty.DatabaseLogic;
import com.example.beauty.Menu.HomeActivity;
import com.example.beauty.R;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    DatabaseLogic DB = new DatabaseLogic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckLoginUser();
    }


    public void LogIn(View view) {
        EditText email = findViewById(R.id.Email_edit_text);
        EditText password = findViewById(R.id.Password_edit_text);

        DB.LogIn(email.getText().toString(), password.getText().toString());
        recreate();
    }


    private void CheckLoginUser(){
        if (DB.CheckLoginUser() && DB.CheckEmailVerification()){
            startActivity(new Intent(this, HomeActivity.class));
        }else {
            setContentView(R.layout.activity_main);
        }
    }

    public void ResetPassword(View view) {
        EditText email = findViewById(R.id.Email_edit_text);
        if(!Objects.isNull(email)) {
            DB.ResetPassword(email.getText().toString());
            Toast.makeText(this, "Check the mail", Toast.LENGTH_SHORT).show();
            email.setText("");
        }else{
            Toast.makeText(this, "Fill in the mail input field", Toast.LENGTH_SHORT).show();
        }
    }


    public void RegisterAccount(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }


}