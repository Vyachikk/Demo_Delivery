package com.example.rediexpressapp.ApiBuilders;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {

    static final String Base_URL = "https://thwzxkajcqlrkvtoqiuk.supabase.co/";  //Базовый адрес для конечных точек API

    public  static  CallRediExpressProject getREPApi() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder() // создаём экземпляр библиотеки OkHTTP,
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // чтобы реализовать связь по HTTP
                .build();


        Retrofit retrofit = new Retrofit.Builder() // создаём экземпляр библиотеки Retrofit
                .baseUrl(Base_URL) // чтобы работать с REST сервисами
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(CallRediExpressProject.class);

    }

}
