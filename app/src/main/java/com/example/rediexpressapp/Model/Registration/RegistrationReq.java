package com.example.rediexpressapp.Model.Registration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class RegistrationReq {

    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("password")
    @Expose
    String password;

    public RegistrationReq(String email, String password)
    {
        this.email = email;
        this.password = password;
    }


    public String getEmail()
    {
        return  email;
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

}
