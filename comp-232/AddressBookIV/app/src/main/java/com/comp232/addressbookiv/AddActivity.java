package com.comp232.addressbookiv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddActivity extends AppCompatActivity {

    Button addContactButton;
    Button backButton;
    EditText editTextFirstName;
    EditText editTextLastName;
    EditText editTextAddress;
    EditText editTextPhoneNum;

    String url = "http://10.0.2.2:8080/AddressBookIVService_war_exploded/contacts/abservice/add";
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhoneNum = findViewById(R.id.editTextPhoneNum);

        addContactButton = findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact newContact = new Contact(
                        editTextFirstName.getText().toString(),
                        editTextLastName.getText().toString(),
                        editTextAddress.getText().toString(),
                        editTextPhoneNum.getText().toString()
                );
                if(newContact.getFirstName().equals("") || newContact.getLastName().equals("") ||
                         newContact.getAddress().equals("") || newContact.getPhoneNumber().equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill out all fields before adding", Toast.LENGTH_LONG).show();
                } else {
                    editTextFirstName.setText("");
                    editTextLastName.setText("");
                    editTextAddress.setText("");
                    editTextPhoneNum.setText("");
                    httpPostAdd(newContact);
                }
            }
        });

        backButton = findViewById(R.id.addBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private void httpPostAdd(Contact contact) {
        Gson gson = new Gson();
        String json = gson.toJson(contact);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    AddActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Contact added!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}