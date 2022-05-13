package com.example.beauty.Menu.StartMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beauty.R;
import com.example.beauty.viewmodel.ViewModelFirebase;

public class RegisterActivity extends AppCompatActivity {

    ViewModelFirebase viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        viewModel = new ViewModelProvider(this).get(ViewModelFirebase.class);
    }

    public void Back(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }


    public void Registration(View view) {
        EditText email = findViewById(R.id.Email_edit_text);
        EditText password = findViewById(R.id.Password_edit_text);
        EditText password_confirm = findViewById(R.id.PasswordConfirm_edit_text);

        viewModel.register(
                email.getText().toString(),
                password.getText().toString(),
                password_confirm.getText().toString());

        Toast.makeText(this, "Check you email: " + email.getText().toString(), Toast.LENGTH_SHORT).show();
    }

}