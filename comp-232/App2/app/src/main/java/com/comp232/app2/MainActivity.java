package com.comp232.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button callSecondActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callSecondActivity = findViewById(R.id.callSecondActivityButton);
        callSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                i.putExtra("Username", "deekoder");
                i.putExtra("Geo", "San Francisco");
                startActivity(i);
            }
        });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Bundle extras = getIntent().getExtras();
//        String username = extras.getString("Username");
//        String region = extras.getString("Geo");
//        Toast.makeText(getApplicationContext(), "Weclome Back to Main" + username, Toast.LENGTH_LONG).show();
//        setContentView(R.layout.activity_main);
//    }
}