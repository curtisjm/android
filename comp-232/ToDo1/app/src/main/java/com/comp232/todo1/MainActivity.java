package com.comp232.todo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {

        // declare
        ListView myListView;
        EditText myEditText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hook
        myListView = (ListView) findViewById(R.id.myListView);
        myEditText = (EditText) findViewById(R.id.myEditText);

        // store data
        final ArrayList<String> toDoItems = new ArrayList<>();

        // slaps the entire ArrayList to the ListView
        final ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toDoItems);
        myListView.setAdapter(arrayAdapter);

        // on pressing enter
        myEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                    if(keyCode == KeyEvent.KEYCODE_ENTER) {
                        // add item to data structure
                        toDoItems.add(0, myEditText.getText().toString());
                        // update UI
                        arrayAdapter.notifyDataSetChanged();
                        myEditText.setText("");
                        return true;
                    }
                return false;
            }
        });

    }
//        public void showSoftKeyboard(View view) {
//            if (view.requestFocus()) {
//                InputMethodManager imm = (InputMethodManager)
//                        getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
//            }
//        }
}