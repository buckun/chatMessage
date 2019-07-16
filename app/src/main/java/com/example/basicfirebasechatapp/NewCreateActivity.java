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

public class NewCreateActivity extends AppCompatActivity {


    private Toolbar toolbar_new_account;
    private EditText userName_for_new_account, email_for_new_account, password_for_new_account;
    private Button btn_create_account;

    private FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_create);
        init();

        btn_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });
    }

    private void createNewAccount() {

        String newUserName = userName_for_new_account.getText().toString();
        String newEmail = email_for_new_account.getText().toString();
        String newPassword = password_for_new_account.getText().toString();

        if (TextUtils.isEmpty(newEmail) && TextUtils.isEmpty(newPassword)) {
            Toast.makeText(this, "not empty email and password",Toast.LENGTH_LONG).show();
        }else {
            auth.createUserWithEmailAndPassword(newEmail,newPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "create user success",Toast.LENGTH_LONG).show();
                        Intent register_activity = new Intent(NewCreateActivity.this, RegisterActivity.class);
                        startActivity(register_activity);
                        fileList();
                    }else {
                        Toast.makeText(getApplicationContext(), "create user not success",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    public void init() {
        toolbar_new_account = findViewById(R.id.action_bar_new_account);
        setSupportActionBar(toolbar_new_account);
        getSupportActionBar().setTitle("Account Save");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        userName_for_new_account = findViewById(R.id.userName_for_new_account);
        email_for_new_account = findViewById(R.id.email_for_new_account);
        password_for_new_account = findViewById(R.id.password_for_new_account);
        btn_create_account = findViewById(R.id.btn_create_account);

    }
}
