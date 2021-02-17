package com.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.todo.util.MySharedPreferences;

public class FirstActivity extends AppCompatActivity {
    private TextView contentView;
    private TextView contentView2;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        context = this;
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        String content = bundle.getString("content");

        contentView = findViewById(R.id.contentView);
        contentView2 = findViewById(R.id.contentView2);
        contentView.setText(content);

        SharedPreferences pref =  context.getSharedPreferences("MyPref", MODE_PRIVATE);
        String content2 = pref.getString("content", "Default value");
        contentView2.setText(content2);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("Started....................");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Stopped....................");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy....................");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume....................");
    }
}