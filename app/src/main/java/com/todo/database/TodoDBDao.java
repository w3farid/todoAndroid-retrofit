package com.todo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TodoDBDao {

    SQLiteDatabase database;

    DatabaseHelper dbhelper;
    Context context;

    public TodoDBDao(Context context) {
        this.context = context;
        dbhelper = DatabaseHelper.getHelper(context);

        if(dbhelper == null){
            dbhelper = DatabaseHelper.getHelper(context);
        }else{
            database = dbhelper.getWritableDatabase();
        }
    }
}
