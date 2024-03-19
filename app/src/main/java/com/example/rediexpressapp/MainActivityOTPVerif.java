package com.example.rediexpressapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rediexpressapp.ApiBuilders.ApiBuilder;
import com.example.rediexpressapp.Model.Auth.ForgetPasswordReq;
import com.example.rediexpressapp.Model.Auth.OTPVerifReq;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivityOTPVerif extends AppCompatActivity {


    String access_token;

    TextView tv_setTimer;

    private static final long START_TIME_IN_MILLIS = 60000;

    private CountDownTimer mcountDownTimer;
    private boolean mtimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    Button ButSetPassword;

    Button ButResend;

    EditText txtNum1;
    EditText txtNum2;
    EditText txtNum3;
    EditText txtNum4;
    EditText txtNum5;
    EditText txtNum6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_otpverif);

        txtNum1 = findViewById(R.id.txtNumber1);
        txtNum2 = findViewById(R.id.txtNumber2);
        txtNum3 = findViewById(R.id.txtNumber3);
        txtNum4 = findViewById(R.id.txtNumber4);
        txtNum5 = findViewById(R.id.txtNumber5);
        txtNum6 = findViewById(R.id.txtNumber6);

        txtNum1.addTextChangedListener(etxtnumunlockBut);
        txtNum2.addTextChangedListener(etxtnumunlockBut);
        txtNum3.addTextChangedListener(etxtnumunlockBut);
        txtNum4.addTextChangedListener(etxtnumunlockBut);
        txtNum5.addTextChangedListener(etxtnumunlockBut);
        txtNum6.addTextChangedListener(etxtnumunlockBut);

        tv_setTimer = findViewById(R.id.settxttimer);

        ButResend = findViewById(R.id.buttonResend);

        ButSetPassword = findViewById(R.id.ButtonNewPassword);

        ButSetPassword.setEnabled(false);

        if(!mtimerRunning)
        {
            startTimer();
        }

        ButSetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OTPVerifReq otpVerifReq = new OTPVerifReq(getIntent().getStringExtra("EmailEditText"),
                        txtNum1.getText().toString() +
                        txtNum2.getText().toString() +
                        txtNum3.getText().toString() +
                        txtNum4.getText().toString() +
                        txtNum5.getText().toString() +
                        txtNum6.getText().toString(), "email");

                ApiBuilder.getREPApi().PostOTpVerifInfo("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRod3p4a2FqY3Fscmt2dG9xaXVrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDg5MzQ0OTEsImV4cCI6MjAyNDUxMDQ5MX0.8k6uBTbNsW-GBmFvacD_8_P1m4Z1F4Q7RKZzteMrz-w",
                         otpVerifReq).enqueue(new Callback<OTPVerifReq>() {
                    @Override
                    public void onResponse(Call<OTPVerifReq> call, Response<OTPVerifReq> response) {


                        if (response.code() == 200) {


                            Intent i = new Intent(MainActivityOTPVerif.this, MainActivityNewPassword.class);
                            i.putExtra("EmailOTP", getIntent().getStringExtra("EmailEditText"));
                            startActivity(i);
                            finish();
                        }
                        else if (response.code() == 401)
                        {
                            txtNum1.setBackgroundResource(R.drawable.errortxt);
                            txtNum2.setBackgroundResource(R.drawable.errortxt);
                            txtNum3.setBackgroundResource(R.drawable.errortxt);
                            txtNum4.setBackgroundResource(R.drawable.errortxt);
                            txtNum5.setBackgroundResource(R.drawable.errortxt);
                            txtNum6.setBackgroundResource(R.drawable.errortxt);
                        }
                    }

                    @Override
                    public void onFailure(Call<OTPVerifReq> call, Throwable t) {

                    }
                });
            }
        });


            ButResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mtimerRunning)
                {
                    ForgetPasswordReq forgetPasswordReq = new ForgetPasswordReq(getIntent().getStringExtra("EmailEditText"));

                    ApiBuilder.getREPApi().PostForgetPassInfo("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRod3p4a2FqY3Fscmt2dG9xaXVrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDg5MzQ0OTEsImV4cCI6MjAyNDUxMDQ5MX0.8k6uBTbNsW-GBmFvacD_8_P1m4Z1F4Q7RKZzteMrz-w",
                            forgetPasswordReq).enqueue(new Callback<ForgetPasswordReq>() {
                        @Override
                        public void onResponse(Call<ForgetPasswordReq> call, Response<ForgetPasswordReq> response) {
                            if(response.code() == 200) {
                                resetTimer();
                                startTimer();


                            }
                        }

                        @Override
                        public void onFailure(Call<ForgetPasswordReq> call, Throwable t) {

                        }
                    });

                }
            }
        });

            txtNum1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(txtNum1.getText().toString().trim().length() == 1)
                    {
                        txtNum2.requestFocus();
                        txtNum2.selectAll();
                    }
                }
            });

        txtNum2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(txtNum2.getText().toString().trim().length() == 1)
                {
                    txtNum3.requestFocus();
                    txtNum3.selectAll();
                }
            }
        });

        txtNum3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(txtNum3.getText().toString().trim().length() == 1)
                {
                    txtNum4.requestFocus();
                    txtNum4.selectAll();
                }
            }
        });

        txtNum4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(txtNum4.getText().toString().trim().length() == 1)
                {
                    txtNum5.requestFocus();
                    txtNum5.selectAll();
                }
            }
        });

        txtNum5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(txtNum5.getText().toString().trim().length() == 1)
                {
                    txtNum6.requestFocus();
                    txtNum6.selectAll();
                }
            }
        });



    }





    TextWatcher etxtnumunlockBut = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String num1 = txtNum1.getText().toString().trim();
            String num2 = txtNum2.getText().toString().trim();
            String num3 = txtNum3.getText().toString().trim();
            String num4 = txtNum4.getText().toString().trim();
            String num5 = txtNum5.getText().toString().trim();
            String num6 = txtNum6.getText().toString().trim();

            ButSetPassword.setEnabled(!num1.isEmpty() && !num2.isEmpty() && !num3.isEmpty() && !num4.isEmpty() && !num5.isEmpty() && !num6.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void startTimer() {
        mcountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                    mtimerRunning = false;

                ButResend.setVisibility(View.VISIBLE);
                tv_setTimer.setVisibility(View.INVISIBLE);
            }
        }.start();

        mtimerRunning = true;
        ButResend.setVisibility(View.INVISIBLE);
        tv_setTimer.setVisibility(View.VISIBLE);
    }
    private void pauseTimer() {
        mcountDownTimer.cancel();
        mtimerRunning = false;

        ButResend.setVisibility(View.VISIBLE);
        tv_setTimer.setVisibility(View.INVISIBLE);



    }
    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        ButResend.setVisibility(View.INVISIBLE);

        tv_setTimer.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
            int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
            int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

            String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

            tv_setTimer.setText("resend after " + timeLeftFormatted);
    }
}