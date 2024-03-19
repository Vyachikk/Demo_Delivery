package com.example.rediexpressapp.Model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddProfiles {

    @SerializedName("full_name")
    @Expose
    String fullName;

    @SerializedName("phone_number")
    @Expose
    String Phone;

    @SerializedName("email_address")
    @Expose
    String email;

    @SerializedName("avatar_file")
    @Expose
    String avatar;

    public AddProfiles(String fullName, String Phone, String email, String avatar )
    {
        this.fullName = fullName;
        this.Phone = Phone;
        this.email = email;
        this.avatar = avatar;
    }
}
