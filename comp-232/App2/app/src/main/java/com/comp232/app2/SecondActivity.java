package com.comp232.app2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    Button callFirstActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Bundle extras = getIntent().getExtras();
        String username = extras.getString("Username");
        String region = extras.getString("Geo");
        Toast.makeText(getApplicationContext(), "Weclome Back to Second " + username, Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_two);

        callFirstActivity = findViewById(R.id.callFirstActivityButton);
        callFirstActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("Username", "deekoder");
                i.putExtra("Geo", "San Francisco");
                startActivity(i);
            }
        });
    }

}
