package com.example.rediexpressapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rediexpressapp.ApiBuilders.ApiBuilder;
import com.example.rediexpressapp.Model.Registration.RegistrationReq;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class MainActivitySignUp extends AppCompatActivity {

    EditText etxtEmail;
    EditText etxtpassword;
    EditText etxtRetryPassword;

    CheckBox checkBoxPolicy;

    Button ButtonSignUp;
    Button ButtonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_up);

        HelperLogin helperLogin = new HelperLogin(this);

        etxtEmail = findViewById(R.id.txtEmail);
        etxtpassword = findViewById(R.id.txtPassword);
        etxtRetryPassword = findViewById(R.id.txtPassword2);
        checkBoxPolicy = findViewById(R.id.cbPolicy);
        ButtonSignUp = findViewById(R.id.buttonSignUp);
        ButtonSignIn = findViewById(R.id.ButtonSignIn);

        ButtonSignUp.setEnabled(false);

        checkBoxPolicy.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {

                          if(checkBoxPolicy.isChecked()) {
                              ButtonSignUp.setEnabled(true);
                          }
                          else
                          {
                              ButtonSignUp.setEnabled(false);
                          }
                      }
                  });

        ButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivitySignUp.this, MainActivitySignIn.class);
                startActivity(i);
                finish();
            }
        });

        ButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etxtEmail.getText().toString().trim().equals(""))
                {
                    Toast.makeText(MainActivitySignUp.this, R.string.ForgetEmail, Toast.LENGTH_SHORT).show();
                }
                else if(etxtpassword.getText().toString().trim().equals(""))
                {
                    Toast.makeText(MainActivitySignUp.this, R.string.ForgetPassword, Toast.LENGTH_SHORT).show();
                }
                else if(etxtRetryPassword.getText().toString().trim().equals(""))
                {
                    Toast.makeText(MainActivitySignUp.this, R.string.ForgetRetryPassword, Toast.LENGTH_SHORT).show();
                }
                else if (!etxtpassword.getText().toString().trim().equals(etxtRetryPassword.getText().toString()))
                {
                    Toast.makeText(MainActivitySignUp.this, R.string.DontMatch, Toast.LENGTH_SHORT).show();
                }
                else
                {

                    RegistrationReq registrationReq = new RegistrationReq(etxtEmail.getText().toString(), etxtpassword.getText().toString());

                    ApiBuilder.getREPApi().postRegisterInfo("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRod3p4a2FqY3Fscmt2dG9xaXVrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDg5MzQ0OTEsImV4cCI6MjAyNDUxMDQ5MX0.8k6uBTbNsW-GBmFvacD_8_P1m4Z1F4Q7RKZzteMrz-w", registrationReq).enqueue(new Callback<RegistrationReq>() {
                        @Override
                        public void onResponse(Call<RegistrationReq> call, Response<RegistrationReq> response) {

                            if(response.code() == 429)
                            {
                                Toast.makeText(MainActivitySignUp.this, "Email rate limit exceeded", Toast.LENGTH_SHORT).show();
                            }
                            else if (response.code() == 200) {

                                boolean u = helperLogin.checkusername(etxtEmail.getText().toString());
                                {
                                    if (u == false)
                                    {
                                        boolean insert = helperLogin.insertdata(etxtEmail.getText().toString(),
                                                etxtpassword.getText().toString());
                                        if(insert == true)
                                        {
                                            Toast.makeText(MainActivitySignUp.this, "Confirm your email", Toast.LENGTH_SHORT).show();

                                            Intent i = new Intent(MainActivitySignUp.this, MainActivitySignIn.class);
                                            startActivity(i);
                                            finish();
                                        }

                                    }
                                    else {
                                        Toast.makeText(MainActivitySignUp.this, "Already Exist ", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<RegistrationReq> call, Throwable t) {

                        }
                    });



                }


            }
        });

    }
}