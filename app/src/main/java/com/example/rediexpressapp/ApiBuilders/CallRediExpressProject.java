package com.example.rediexpressapp.ApiBuilders;

import com.example.rediexpressapp.Model.Auth.AddProfiles;
import com.example.rediexpressapp.Model.Auth.AuthorizationReq;
import com.example.rediexpressapp.Model.Auth.ForgetPasswordReq;
import com.example.rediexpressapp.Model.Auth.NewPasswordReq;
import com.example.rediexpressapp.Model.Auth.OTPVerifReq;
import com.example.rediexpressapp.Model.Registration.RegistrationReq;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

        @POST("/auth/v1/recover")
        Call<ForgetPasswordReq> PostForgetPassInfo(
                @Query("apikey") String apikey,
                @Body ForgetPasswordReq ForgetPassword
        );


        @POST("/auth/v1/verify")
        Call<OTPVerifReq> PostOTpVerifInfo(
                @Query("apikey") String apikey,
                @Body OTPVerifReq otpVerifReq
        );

        @PUT("/auth/v1/user")
        Call <NewPasswordReq> PostNewPasswordInfo(
                @Query("apikey") String apikey,
                @Body NewPasswordReq newPasswordReq
        );

        @POST("/rest/v1/profiles")
        Call <AddProfiles> PostAddProfiles(
                @Query("apikey") String apikey,
                @Body AddProfiles addProfiles
        );
}
