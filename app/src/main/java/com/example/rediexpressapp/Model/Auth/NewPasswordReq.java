package com.example.rediexpressapp.Model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewPasswordReq {

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("password")
    @Expose
    String password;

    public NewPasswordReq(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

}
