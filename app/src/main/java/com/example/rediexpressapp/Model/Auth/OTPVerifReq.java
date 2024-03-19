package com.example.rediexpressapp.Model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class OTPVerifReq {
        @SerializedName("email")
        @Expose
        String email;

        @SerializedName("token")
        @Expose
        String token;

        @SerializedName("type")
        @Expose
        String type;


    public OTPVerifReq(String email, String token, String type)
        {
            this.email = email;
            this.token = token;
            this.type = type;
        }
}
