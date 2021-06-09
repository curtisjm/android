package com.comp232.todowitem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.*;
import com.comp232.todowitem.TodoService.TodoBinder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText todo;
    ListView history;
    Button addTask;
    ArrayList<String> storage = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    TodoService todoService;
    boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todo = (EditText) findViewById(R.id.myEditText);
        addTask = (Button) findViewById(R.id.addButton);
        history = (ListView) findViewById(R.id.myListView);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, storage);
        history.setAdapter(arrayAdapter);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(v);
            }
        });
    }

    public void onButtonClick(View v) {
        if (isBound) {
            todoService.addToStorage(todo.getText().toString(), storage);
            arrayAdapter.notifyDataSetChanged();
            todo.setText("");
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TodoBinder binder = (TodoBinder) service;
            todoService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this, TodoService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isBound) {
            unbindService(mConnection);
            isBound = false;
        }
    }
}