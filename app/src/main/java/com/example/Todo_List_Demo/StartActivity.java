package com.example.Todo_List_Demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {
Button Register,Login;
    EditText Email,Password;
    Button LoginBtn;
    FirebaseAuth auth;
    private static final String TAG="StartActivity";
    public static boolean DarkMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
//setBackground();
        Email =(EditText) findViewById(R.id.EmailId);
        Password =(EditText) findViewById(R.id.Password);
        Register = (Button)findViewById(R.id.Register);
        LoginBtn =(Button) findViewById(R.id.Loginbtn);
        auth=FirebaseAuth.getInstance();
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,RegisterActivity.class));
                finish();
            }
        });


        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Text_email = Email.getText().toString();
                String Text_password = Password.getText().toString();
                LoginUser(Text_email,Text_password);
            }
        });

    }
    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            startActivity(new Intent(StartActivity.this,TODO.class));
            //addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();

        }
    }
    private void LoginUser(final String email, String password) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(StartActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                    SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("getUser", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("Current User",email );
                  startActivity(new Intent(StartActivity.this,TODO.class));
                   finish();
                }
                else{
                    Toast.makeText(StartActivity.this,"Wrong Id or Password",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}