package com.example.rediexpressapp.Model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.http.Body;
import retrofit2.http.PUT;

public class AuthorizationReq {
    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("password")
    @Expose
    String password;

    @SerializedName("error_description")
    @Expose
    String bodyerror;

    public AuthorizationReq(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public String getEmail()
    {
        return  email;
    }

    public String getBodyerror()
    {
        return  bodyerror;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBodyerror(String bodyerror) {
        this.bodyerror = bodyerror;
    }



}
