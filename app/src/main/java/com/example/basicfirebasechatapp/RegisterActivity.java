package com.example.basicfirebasechatapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private Toolbar register_toolbar;

    private EditText email_register, password_register;
    private Button btnRegister;

    private FirebaseAuth auth;
    private FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerActivity();
            }
        });
    }

    private void registerActivity() {

        String email = email_register.getText().toString();
        String password = password_register.getText().toString();

        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            Toast.makeText(this, "not empty email and password",Toast.LENGTH_LONG).show();
        }else {
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "registration success",Toast.LENGTH_LONG).show();
                        Intent register = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(register);
                    }else {
                        Toast.makeText(getApplicationContext(), "registration not success",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }


    private void init() {

        register_toolbar =  findViewById(R.id.action_bar_register);
        setSupportActionBar(register_toolbar);
        //toolbarda isim belirtmek
        getSupportActionBar().setTitle("Register");
        //geri buttonu setlemek
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        email_register =  findViewById(R.id.email);
        password_register = findViewById(R.id.password);
        btnRegister = findViewById(R.id.btnRegister);
    }
}
