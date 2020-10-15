package com.example.Todo_List_Demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText Email,Password;
    Button LoginBtn;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email =(EditText) findViewById(R.id.EmailId);
        Password =(EditText) findViewById(R.id.Password);
        LoginBtn =(Button) findViewById(R.id.Loginbtn);
        auth=FirebaseAuth.getInstance();
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Text_email = Email.getText().toString();
                String Text_password = Password.getText().toString();
                LoginUser(Text_email,Text_password);
            }
        });

    }

    private void LoginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Login user Successful",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this,TODO.class));
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this,"Login Fail",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    }
