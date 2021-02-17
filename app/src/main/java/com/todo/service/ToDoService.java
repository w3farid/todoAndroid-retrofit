package com.todo.service;

import com.todo.ToDoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ToDoService {
    @GET("/todo/todolist/{username}")
    public Call<List<ToDoModel>> getToDoList(@Path("username") String username);

    @POST("/todo/api/add")
    public Call<ToDoModel> save(@Body ToDoModel toDoModel);
}
