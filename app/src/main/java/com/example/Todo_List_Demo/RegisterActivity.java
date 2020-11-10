package com.example.Todo_List_Demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
EditText Email,Password;
Button RegisterBtn;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
  Email =(EditText) findViewById(R.id.EmailId);
        Password =(EditText) findViewById(R.id.Password);
        RegisterBtn =(Button) findViewById(R.id.Registerbtn);
        auth=FirebaseAuth.getInstance();
        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Text_email = Email.getText().toString();
                String Text_password = Password.getText().toString();

                if(TextUtils.isEmpty(Text_email )|| TextUtils.isEmpty(Text_password)){
                    Toast.makeText(RegisterActivity.this,"Empty Credentials",Toast.LENGTH_LONG).show();

                }
                else if(Text_password.length()<6){
                    Toast.makeText(RegisterActivity.this,"Password Too Short",Toast.LENGTH_LONG).show();

                }

                else{
                    registerUser(Text_email,Text_password);
                }
            }
        });

    }

    private void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Registering user Successful",Toast.LENGTH_LONG).show();
                    RegisterActivity.this.startActivity(new Intent(RegisterActivity.this,TODO.class));
                    RegisterActivity.this.finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Registering Fail",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}