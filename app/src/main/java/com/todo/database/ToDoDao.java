package com.todo.database;

import android.content.ContentValues;
import android.content.Context;

import com.todo.ToDoModel;

public class ToDoDao extends TodoDBDao {

    public ToDoDao(Context context) {
        super(context);
    }

    public long save(ToDoModel toDoModel){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ID, toDoModel.getId());
        values.put(DatabaseHelper.COLUMN_CONTENT, toDoModel.getContent());
        return database.insert(DatabaseHelper.TABLE_NAME, null, values);
    }

}
