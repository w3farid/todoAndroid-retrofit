package com.todo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "tododb";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "todos";
    static final String COLUMN_ID = "id";
    static final String COLUMN_CONTENT = "content";
    static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" (" +
            ""+COLUMN_ID+ " Integer primary key, " +
            ""+COLUMN_CONTENT+" text " +
            ");";

    private static DatabaseHelper instance;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getHelper(Context context){

        if(instance == null){
            instance = new DatabaseHelper(context);
        }

        return instance;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON");
        }
    }
}
