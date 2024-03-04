package com.example.rediexpressapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rediexpressapp.ApiBuilders.ApiBuilder;
import com.example.rediexpressapp.Model.Auth.AuthorizationReq;

import java.util.Currency;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivitySignIn extends AppCompatActivity {

    Button ButtonSignUp;
    Button ButtonLogIn;

    EditText etxtEmail;
    EditText etxtPassword;

    CheckBox cbRemember;
    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_in);

        ButtonLogIn = findViewById(R.id.buttonLogIn);
        ButtonSignUp = findViewById(R.id.buttonSignUp);
        etxtEmail = findViewById(R.id.txtEmail);
        etxtPassword = findViewById(R.id.txtPassword);
        cbRemember = findViewById(R.id.cbRememberMe);

        HelperLogin helperLogin = new HelperLogin(this);



        ButtonLogIn.setEnabled(false);

        etxtEmail.addTextChangedListener(LogIntxtWatch);
        etxtPassword.addTextChangedListener(LogIntxtWatch);



        ButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivitySignIn.this, MainActivitySignUp.class);
                startActivity(i);
                finish();
            }
        });



        sp=getSharedPreferences("Data" , MODE_PRIVATE);
        editor=sp.edit();
        boolean login=sp.getBoolean("ISLOGGEDIN" , false);
        if (login){
            etxtEmail.setText(sp.getString("Email", ""));
            etxtPassword.setText(sp.getString("Password", ""));
            cbRemember.setChecked(true);
        }

        ButtonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AuthorizationReq authorizationReq = new AuthorizationReq(etxtEmail.getText().toString().trim(), etxtPassword.getText().toString().trim());

               ApiBuilder.getREPApi().postAuthoInfo("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRod3p4a2FqY3Fscmt2dG9xaXVrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDg5MzQ0OTEsImV4cCI6MjAyNDUxMDQ5MX0.8k6uBTbNsW-GBmFvacD_8_P1m4Z1F4Q7RKZzteMrz-w",
                       authorizationReq).enqueue(new Callback<AuthorizationReq>() {
                   @Override
                   public void onResponse(Call<AuthorizationReq> call, Response<AuthorizationReq> response) {

                       if(response.code() == 200)
                       {
                           boolean checklogin = helperLogin.checkusernameandpassword(etxtEmail.getText().toString(), etxtPassword.getText().toString());
                           {
                               if(checklogin == true)
                               {
                                   if(cbRemember.isChecked())
                                   {
                                       editor.putString("Email", etxtEmail.getText().toString());
                                       editor.putString("Password", etxtPassword.getText().toString());
                                       editor.putBoolean("ISLOGGEDIN" , true);
                                       editor.apply();

                                       Toast.makeText(MainActivitySignIn.this, "Login is successful", Toast.LENGTH_SHORT).show();
                                       Intent i = new Intent(MainActivitySignIn.this, MainActivityHome.class);
                                       startActivity(i);
                                       finish();
                                   }
                                   else if (!cbRemember.isChecked()) {
                                       editor.putString("Email", "");
                                       editor.putString("Password", "");
                                       editor.putBoolean("ISLOGGEDIN" , false);
                                       editor.apply();
                                       Toast.makeText(MainActivitySignIn.this, "Login is successful", Toast.LENGTH_SHORT).show();
                                       Intent i = new Intent(MainActivitySignIn.this, MainActivityHome.class);
                                       startActivity(i);
                                       finish();
                                   }
                               }
                               else
                               {
                                   Toast.makeText(MainActivitySignIn.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                               }
                           }
                       }
                       else if(response.code() == 400)
                       {
                            Toast.makeText(MainActivitySignIn.this, "Invalid email or password. Or the mail has not been confirmed.", Toast.LENGTH_SHORT).show();
                       }


                   }

                   @Override
                   public void onFailure(Call<AuthorizationReq> call, Throwable t) {


                   }
               });
            }
        });



    }

    private TextWatcher LogIntxtWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String userEmail = etxtEmail.getText().toString().trim();
            String userPassword = etxtPassword.getText().toString().trim();

            ButtonLogIn.setEnabled(!userEmail.isEmpty() && !userPassword.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}