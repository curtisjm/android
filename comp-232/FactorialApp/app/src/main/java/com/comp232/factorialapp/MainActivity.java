package com.comp232.factorialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.comp232.factorialapp.FactorialService.FactBinder;

public class MainActivity extends AppCompatActivity {

    Button computeButton;
    EditText inputText, resultText;
    int operand;
    FactorialService factorialService;
    boolean factorialBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        computeButton = (Button) findViewById(R.id.computeButton);
        inputText = (EditText) findViewById(R.id.inputText);
        resultText = (EditText) findViewById(R.id.resultText);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(v);
                }
        });
    }

    public void onButtonClick(View v) {
        operand = Integer.parseInt(inputText.getText().toString());
        Log.v("Activity1:Input", "Read the input!");
        if(factorialBound) {
            int num = factorialService.getFactorial(operand);
            resultText.setText(String.valueOf(num));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // bind to FactorialService
        Intent intent = new Intent(MainActivity.this, FactorialService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(factorialBound) {
            unbindService(mConnection);
            factorialBound = false;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            FactBinder binder = (FactBinder) service;
            factorialService = binder.getService();
            factorialBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            factorialBound = false;
        }
    };

}