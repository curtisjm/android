package com.comp232.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Calculation> calculationHistory = new ArrayList<>();

    // 1. Declare UX components from activity in code
    EditText numIn1, numIn2;
    Button addButton, subtractButton, multiplyButton, divideButton, powerButton, rootButton, moduloButton, saveButton;
    TextView resultsView, operationView;
    double num1, num2, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. Hook up UX variables to Java variables
        numIn1 = (EditText) findViewById(R.id.num1);
        numIn2 = (EditText) findViewById(R.id.num2);

        addButton = (Button) findViewById(R.id.plusButton);
        subtractButton = (Button) findViewById(R.id.subtractButton);
        multiplyButton = (Button) findViewById(R.id.multiplyButton);
        divideButton = (Button) findViewById(R.id.divideButton);
        powerButton = (Button) findViewById(R.id.powerButton);
        rootButton = (Button) findViewById(R.id.rootButton);
        moduloButton = (Button) findViewById(R.id.moduloButton);
        saveButton = (Button) findViewById(R.id.saveButton);

        operationView = (TextView) findViewById(R.id.operationView);
        resultsView = (TextView) findViewById(R.id.resultView);

        // 3. On pressing plus, add and post results
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(numIn1.getText().toString());
                num2 = Integer.parseInt(numIn2.getText().toString());
                result = num1 + num2;
                operationView.setText(addButton.getText());
                resultsView.setText(String.valueOf(result));
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(numIn1.getText().toString());
                num2 = Integer.parseInt(numIn2.getText().toString());
                result = num1 - num2;
                operationView.setText(subtractButton.getText());
                resultsView.setText(String.valueOf(result));
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(numIn1.getText().toString());
                num2 = Integer.parseInt(numIn2.getText().toString());
                result = num1 * num2;
                operationView.setText(multiplyButton.getText());
                resultsView.setText(String.valueOf(result));
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(numIn1.getText().toString());
                num2 = Integer.parseInt(numIn2.getText().toString());
                result = num1 / num2;
                operationView.setText(divideButton.getText());
                resultsView.setText(String.valueOf(result));
            }
        });

        powerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(numIn1.getText().toString());
                num2 = Integer.parseInt(numIn2.getText().toString());
                result = Math.pow(num1, num2);
                operationView.setText(powerButton.getText());
                resultsView.setText(String.valueOf(result));
            }
        });

        rootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(numIn1.getText().toString());
                num2 = Integer.parseInt(numIn2.getText().toString());
                result = Math.pow(num1, (1 / num2));
                operationView.setText(rootButton.getText());
                resultsView.setText(String.valueOf(result));
            }
        });

        moduloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Integer.parseInt(numIn1.getText().toString());
                num2 = Integer.parseInt(numIn2.getText().toString());
                result = num1 % num2;
                operationView.setText(moduloButton.getText());
                resultsView.setText(String.valueOf(result));
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                calculationHistory.add(new Calculation(numIn1.getText().toString(), numIn2.getText().toString(), operationView.getText().toString(), resultsView.getText().toString()));
                String history = "Saved History: ";
                for(Calculation calc : calculationHistory) {
                    history += calc;
                }
                i.putExtra("Calculation", history);
                startActivity(i);
            }
        });
    }
}