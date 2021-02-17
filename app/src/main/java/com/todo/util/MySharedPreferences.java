package com.todo.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class MySharedPreferences {
    //create an object of SingleObject
    private static SharedPreferences instance;
    private static final String MYPREF = "MyPref";

    public static SharedPreferences getInstance(Context context){
        if(instance == null){
            instance = context.getSharedPreferences(MYPREF, MODE_PRIVATE);
        }
        return instance;
    }

}
