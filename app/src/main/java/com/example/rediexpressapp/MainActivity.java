package com.example.rediexpressapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ViewPager mSLideViewPager;
    LinearLayout mDotLayout;
    Button nextbtn, skipbtn, signin;

    TextView SignInInfo;

    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;
    Boolean flag;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(pref.getBoolean("activity_executed", false)) {
            Intent intent = new Intent(this, MainActivitySignUp.class);
            startActivity(intent);
            finish();
        }
        else
        {

            SharedPreferences.Editor ed = pref.edit();
            ed.putBoolean("activity_executed", true);
            ed.commit();

        }


        nextbtn = findViewById(R.id.ButtonNext);
        skipbtn = findViewById(R.id.ButtonSkip);
        signin = findViewById(R.id.ButtonSignIn);
        SignInInfo = findViewById(R.id.txt_layout);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 2)
                    mSLideViewPager.setCurrentItem(getitem(1),true);

                else {

                    Intent i = new Intent(MainActivity.this, MainActivitySignUp.class);
                    startActivity(i);
                    finish();

                }

            }
        });

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mSLideViewPager.setCurrentItem(getitem(3),true);

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i = new Intent(MainActivity.this, MainActivitySignIn.class);
                startActivity(i);
                finish();

            }
        });

        mSLideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);

        viewPagerAdapter = new ViewPagerAdapter(this);

        mSLideViewPager.setAdapter(viewPagerAdapter);

        setUpindicator(0);
        mSLideViewPager.addOnPageChangeListener(viewListener);
        mDotLayout.setVisibility(View.INVISIBLE);
        nextbtn.setHeight(20);
        nextbtn.setWidth(200);



    }

    public void setUpindicator(int position){


        dots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0 ; i < dots.length ; i++){

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);

        }

        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);


            if (position < 2){
                skipbtn.setVisibility(View.VISIBLE);
                nextbtn.setHeight(20);
                nextbtn.setWidth(200);
                nextbtn.setText("Next");
                signin.setVisibility(View.INVISIBLE);
                SignInInfo.setVisibility(View.INVISIBLE);

            }else {
                skipbtn.setVisibility(View.INVISIBLE);
                nextbtn.setText("Sign Up");
                nextbtn.setHeight(110);
                nextbtn.setWidth(525);
                signin.setVisibility(View.VISIBLE);
                SignInInfo.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private int getitem(int i){

        return mSLideViewPager.getCurrentItem() + i;
    }


    // Вызывается перед выходом из "активного" состояния
    @Override
    public void onPause(){
        // "Замораживает" пользовательский интерфейс, потоки
        // или трудоемкие процессы, которые могут не обновляться,
        // пока Активность не находится на переднем плане.
        super.onPause();
    }

    // Вызывается перед тем, как Активность перестает быть "видимой".
    @Override
    public void onStop(){
        // "Замораживает" пользовательский интерфейс, потоки
        // или операции, которые могут подождать, пока Активность
        // не отображается на экране. Сохраняйте все введенные
        // данные и изменения в UI так, как будто после вызова
        // этого метода процесс должен быть закрыт.
        super.onStop();
    }
}