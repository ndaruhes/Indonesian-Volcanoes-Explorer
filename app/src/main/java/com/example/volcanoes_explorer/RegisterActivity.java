package com.example.volcanoes_explorer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.volcanoes_explorer.Facades.DBHelper;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password, confirmPassword;
    Button registerBtn;
    TextView loginText;
    DBHelper DB;
    float v=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        loginText = (TextView) findViewById(R.id.loginText);

        //animated edit text
        username.setTranslationX(0);
        password.setTranslationX(0);
        confirmPassword.setTranslationX(0);
        registerBtn.setTranslationX(0);
        loginText.setTranslationX(0);

        username.setAlpha(v);
        password.setAlpha(v);
        confirmPassword.setAlpha(v);
        registerBtn.setAlpha(v);
        loginText.setAlpha(v);

        username.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(10).start();
        password.animate().translationX(0).alpha(1).setDuration(1400).setStartDelay(10).start();
        confirmPassword.animate().translationX(0).alpha(1).setDuration(1600).setStartDelay(10).start();
        registerBtn.animate().translationX(0).alpha(1).setDuration(1800).setStartDelay(10).start();
        loginText.animate().translationX(0).alpha(1).setDuration(2000).setStartDelay(10).start();

        DB = new DBHelper(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String confirmPass = confirmPassword.getText().toString();

                if(user.equals("")||pass.equals("")||confirmPass.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(confirmPass)){
                        Boolean checkUser = DB.checkUsername(user);
                        if(!checkUser){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert){
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "User Already Exists! Please Sign In", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}