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
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditActivity extends AppCompatActivity {

    Button updateContactButton;
    Button backButton;
    EditText editTextOldNum;
    EditText editTextNewFirstName;
    EditText editTextNewLastName;
    EditText editTextNewAddress;
    EditText editTextNewPhoneNum;

    String url = "http://10.0.2.2:8080/AddressBookIVService_war_exploded/contacts/abservice/update";
    OkHttpClient client = new OkHttpClient();
    ArrayList<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTextOldNum = findViewById(R.id.editTextOldPhoneNum);
        editTextNewFirstName = findViewById(R.id.editTextEditFirstName);
        editTextNewLastName = findViewById(R.id.editTextEditLastName);
        editTextNewAddress = findViewById(R.id.editTextEditAddress);
        editTextNewPhoneNum = findViewById(R.id.editTextEditPhoneNum);

        updateContactButton = findViewById(R.id.updateContactButton);
        updateContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contacts.add(new Contact());
                contacts.get(0).setPhoneNumber(editTextOldNum.getText().toString());
                contacts.add(new Contact(
                        editTextNewFirstName.getText().toString(),
                        editTextNewLastName.getText().toString(),
                        editTextNewAddress.getText().toString(),
                        editTextNewPhoneNum.getText().toString()
                ));
                if(contacts.get(0).getPhoneNumber().equals("") || contacts.get(1).getFirstName().equals("") || contacts.get(1).getLastName().equals("") ||
                        contacts.get(1).getAddress().equals("") || contacts.get(1).getPhoneNumber().equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill out all fields before updating", Toast.LENGTH_LONG).show();
                } else {
                    editTextOldNum.setText("");
                    editTextNewFirstName.setText("");
                    editTextNewLastName.setText("");
                    editTextNewAddress.setText("");
                    editTextNewPhoneNum.setText("");
                    httpPostUpdate();
                }
            }
        });

        backButton = findViewById(R.id.editBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private void httpPostUpdate() {
        Gson gson = new Gson();
        String json = gson.toJson(contacts);
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
                    EditActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Contact updated!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}