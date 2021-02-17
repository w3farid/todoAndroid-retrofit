package com.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.todo.database.ToDoDao;
import com.todo.fragmentexample.FragmentMain;
import com.todo.listviewdemo.listViewActivity;
import com.todo.retrofit.RetrofitConfig;
import com.todo.service.ToDoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText content;
    private TextView msg;
    private Context context;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Intent i;

    ToDoDao toDoDao;
    ToDoService toDoService;
    ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toDoService = RetrofitConfig.createService(ToDoService.class);

        context = this;
        toDoDao = new ToDoDao(context);
        pref =  context.getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();
       // getActionBar().setDisplayHomeAsUpEnabled(true);
        content = findViewById(R.id.content);
        msg = findViewById(R.id.msg);

        // iamge view
        String url = "http://i.imgur.com/7spzG.png";
        mImageView = (ImageView) findViewById(R.id.mImageView);
        Picasso.get().load(url).into(mImageView);


    }

    public void saveData(View view){
        ToDoModel entity = new ToDoModel();
        String content1 = content.getText().toString();

        entity.setTitle("dfksd");
        entity.setContent(content1);
        Call<ToDoModel> res =  toDoService.save(entity);
        res.enqueue(new Callback<ToDoModel>() {
            @Override
            public void onResponse(Call<ToDoModel> call, Response<ToDoModel> response) {
                ToDoModel rs = response.body();
                System.out.println(rs.toString());

            }

            @Override
            public void onFailure(Call<ToDoModel> call, Throwable t) {
                t.fillInStackTrace();
            }
        });
       // System.out.println(result+" ======================saved================");
        showDialog("Saved successfully");

        // shared preference example

        editor.putString("content", content1+" SharedPreferences");
        editor.apply();
        i = new Intent(this, FirstActivity.class);
        i.putExtra("content", content1+" intent");

    }

    public void showList(View view){
        i = new Intent(this, listViewActivity.class);
        startActivity(i);
    }

    public void showFragment(View view){
        i = new Intent(this, FragmentMain.class);
        startActivity(i);
    }

    private void showDialog(String msg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        startActivity(i);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

}