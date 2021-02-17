package com.todo.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.todo.R;
import com.todo.ToDoModel;
import com.todo.retrofit.RetrofitConfig;
import com.todo.service.ToDoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class listViewActivity extends AppCompatActivity {
    ListView studentList;
    String[] students = {"Altaf", "Naz", "Riyad", "Sajjad", "Shuvin", "Sharmin", "Habib", "Shahed", "Sabina", "Altaf", "Naz", "Riyad", "Sajjad", "Shuvin", "Sharmin", "Habib", "Shahed", "Sabina","Altaf", "Naz", "Riyad", "Sajjad", "Shuvin", "Sharmin", "Habib", "Shahed", "Sabina","Altaf", "Naz", "Riyad", "Sajjad", "Shuvin", "Sharmin", "Habib", "Shahed", "Sabina","Altaf", "Naz", "Riyad", "Sajjad", "Shuvin", "Sharmin", "Habib", "Shahed", "Sabina","Altaf", "Naz", "Riyad", "Sajjad", "Shuvin", "Sharmin", "Habib", "Shahed", "Sabina", "Irfan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        studentList = findViewById(R.id.student_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, 0, students);
        studentList.setAdapter(adapter);
        ToDoService toDoService = RetrofitConfig.createService(ToDoService.class);
        Call<List<ToDoModel>> toDoList = toDoService.getToDoList("Altaf");

        toDoList.enqueue(new Callback<List<ToDoModel>>() {

            @Override
            public void onResponse(Call<List<ToDoModel>> call, Response<List<ToDoModel>> response) {
                List<ToDoModel> body = response.body();
                if(body != null){
                    for (ToDoModel v: body){

                        System.out.println("====================start===========================");
                        System.out.println(v.getId());
                        System.out.println(v.getTitle());
                        System.out.println(v.getContent());
                        System.out.println("====================end===========================");
                    }
                }else{
                    //if object null // TODO
                }

            }

            @Override
            public void onFailure(Call<List<ToDoModel>> call, Throwable t) {
                String msg = t.getLocalizedMessage();
                System.out.println(msg+"============================");
                //t.printStackTrace();
            }

        });
    }
}