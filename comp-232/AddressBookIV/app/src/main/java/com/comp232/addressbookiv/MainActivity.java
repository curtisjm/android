package com.comp232.addressbookiv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button listActivityBtn;
    Button addActivityBtn;
    Button deleteActivityBtn;
    Button editActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listActivityBtn = findViewById(R.id.listButton);
        listActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(i);
            }
        });

        addActivityBtn = findViewById(R.id.addButton);
        addActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(i);
            }
        });

        deleteActivityBtn = findViewById(R.id.deleteButton);
        deleteActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DeleteActivity.class);
                startActivity(i);
            }
        });

        editActivityBtn = findViewById(R.id.editButton);
        editActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EditActivity.class);
                startActivity(i);
            }
        });
    }
}