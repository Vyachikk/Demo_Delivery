package com.example.rediexpressapp.ApiBuilders;

import com.example.rediexpressapp.Model.Auth.AuthorizationReq;
import com.example.rediexpressapp.Model.Registration.RegistrationReq;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CallRediExpressProject {
        @POST("/auth/v1/signup")
        Call<RegistrationReq> postRegisterInfo(
                @Query("apikey") String apikey,
                @Body RegistrationReq signUp
                );


        @POST("/auth/v1/token?grant_type=password")
        Call<AuthorizationReq> postAuthoInfo(
                @Query("apikey") String apikey,
                @Body AuthorizationReq LogIn
        );

        @GET("/auth/v1/token?grant_type=password")
        Call<AuthorizationReq> getAuthoInfo(
                @Query("apikey") String apikey,
                @Body AuthorizationReq LogIn
        );

}
