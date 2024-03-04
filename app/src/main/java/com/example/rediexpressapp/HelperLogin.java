package com.example.rediexpressapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HelperLogin extends SQLiteOpenHelper {
    private static final String dbname = "User_registration";
    private static final int version = 1;

    public HelperLogin(@Nullable Context context)
    {
        super(context, dbname, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table users (id integer primary key autoincrement , Email text " +
                ", Password text) ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }

    public boolean insertdata(String email, String password) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Email", email);
        values.put("Password", password);
        long r = db.insert("users", null, values);
        if (r == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkusername(String email) {

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from users where email=?", new String[]{email});
        if (cursor.getCount() > 0) {
            return true;

        } else {
            return false;
        }
    }

        public boolean checkusernameandpassword(String email , String password){
            SQLiteDatabase db = getWritableDatabase();

            Cursor cursor = db.rawQuery("select * from users where email=? and Password=? "
                    , new String[]{email , password});
            if(cursor.getCount()>0){
                return true;
            }else {
                return false;
            }
        }

    }

