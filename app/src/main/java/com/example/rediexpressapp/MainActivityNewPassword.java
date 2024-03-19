package com.example.rediexpressapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rediexpressapp.ApiBuilders.ApiBuilder;
import com.example.rediexpressapp.Model.Auth.NewPasswordReq;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityNewPassword extends AppCompatActivity {

    EditText etxtPassword;
    EditText etxtRetryPassword;

    Button ButNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new_password);

        etxtPassword = findViewById(R.id.txtNewPassword);
        etxtRetryPassword = findViewById(R.id.txtNewPasswordRetry);

        etxtPassword.addTextChangedListener(etxtnewpass);
        etxtRetryPassword.addTextChangedListener(etxtnewpass);

        ButNewPassword = findViewById(R.id.buttonLogInNew);


        ButNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewPasswordReq newPasswordReq = new NewPasswordReq(getIntent().getStringExtra("EmailOTP"), etxtPassword.getText().toString().trim());

                if (!etxtPassword.getText().toString().trim().equals(etxtRetryPassword.getText().toString()))
                {
                    Toast.makeText(MainActivityNewPassword.this, R.string.DontMatch, Toast.LENGTH_SHORT).show();
                }
                else {

                    ApiBuilder.getREPApi().PostNewPasswordInfo("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRod3p4a2FqY3Fscmt2dG9xaXVrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDg5MzQ0OTEsImV4cCI6MjAyNDUxMDQ5MX0.8k6uBTbNsW-GBmFvacD_8_P1m4Z1F4Q7RKZzteMrz-w",
                            newPasswordReq).enqueue(new Callback<NewPasswordReq>() {
                        @Override
                        public void onResponse(Call<NewPasswordReq> call, Response<NewPasswordReq> response) {

                            if (response.code() == 200) {
                                Intent i = new Intent(MainActivityNewPassword.this, MainActivityHome.class);
                                startActivity(i);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<NewPasswordReq> call, Throwable t) {

                        }
                    });
                }
            }
        });



    }

    TextWatcher etxtnewpass = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String password = etxtPassword.getText().toString().trim();
            String retrypassword = etxtRetryPassword.getText().toString().trim();

            ButNewPassword.setEnabled(!password.isEmpty() && !retrypassword.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}