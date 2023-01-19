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

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button btnLogin;
    TextView registerText;
    DBHelper DB;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        registerText = (TextView) findViewById(R.id.registerText);
        //animasi
        username.setTranslationX(0);
        password.setTranslationX(0);
        btnLogin.setTranslationX(0);
        registerText.setTranslationX(0);

        username.setAlpha(v);
        password.setAlpha(v);
        btnLogin.setAlpha(v);
        registerText.setAlpha(v);

        username.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(10).start();
        password.animate().translationX(0).alpha(1).setDuration(1400).setStartDelay(10).start();
        btnLogin.animate().translationX(0).alpha(1).setDuration(1600).setStartDelay(10).start();
        registerText.animate().translationX(0).alpha(1).setDuration(1800).setStartDelay(10).start();

        DB = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean confirmCredentials = DB.checkUsernamePassword(user, pass);
                    if(confirmCredentials){
                        Toast.makeText(LoginActivity.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}