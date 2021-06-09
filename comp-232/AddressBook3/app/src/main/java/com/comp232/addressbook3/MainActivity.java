package com.comp232.addressbook3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.comp232.addressbook3.AddToArrayListService.AddBinder;

public class MainActivity extends AppCompatActivity {

    // declare
    ListView myListView;
    EditText myEditText;
    AddToArrayListService addToArrayListService = new AddToArrayListService();
    boolean addServiceBound = false;
    ArrayAdapter<Contact> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hook
        myListView = (ListView) findViewById(R.id.myListView);
        myEditText = (EditText) findViewById(R.id.myEditText);

        // slaps the entire ArrayList to the ListView
        arrayAdapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, addToArrayListService.getContacts());
        myListView.setAdapter(arrayAdapter);

        // on pressing enter
        myEditText.setOnKeyListener(new View.OnKeyListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                    if(keyCode == KeyEvent.KEYCODE_ENTER) {
                        // add item to data structure
                        Log.v("MainActivity", "Adding to list!");
                        addToArrayListService.addToList(myEditText.getText().toString());
                        // update UI
                        arrayAdapter.clear();
                        arrayAdapter.addAll(addToArrayListService.getContacts());
                        arrayAdapter.notifyDataSetChanged();
                        myEditText.setText("");
                        return true;
                    }
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this, AddToArrayListService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(addServiceBound) {
            unbindService(mConnection);
            addServiceBound = false;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AddBinder binder = (AddBinder) service;
            addToArrayListService = binder.getService();
            addServiceBound = true;
            Log.v("MainActivity", "mConnection");

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            addServiceBound = false;
        }
    };

}