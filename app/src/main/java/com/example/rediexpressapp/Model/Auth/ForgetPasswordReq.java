package com.example.rediexpressapp.Model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPasswordReq {
    @SerializedName("email")
    @Expose
    String email;

   public ForgetPasswordReq(String email)
   {
       this.email = email;
   }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
