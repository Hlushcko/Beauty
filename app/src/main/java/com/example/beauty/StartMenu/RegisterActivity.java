package com.example.beauty.StartMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beauty.DatabaseLogic;
import com.example.beauty.R;

public class RegisterActivity extends AppCompatActivity {

    DatabaseLogic DB = new DatabaseLogic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void Back(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }


    public void Registration(View view) {
        EditText email = findViewById(R.id.Email_edit_text);
        EditText password = findViewById(R.id.Password_edit_text);
        EditText password_confirm = findViewById(R.id.PasswordConfirm_edit_text);

        DB.Register(email.getText().toString(), password.getText().toString(), password_confirm.getText().toString());
        Toast.makeText(this, "Check the mail", Toast.LENGTH_SHORT).show();
    }

}