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

public class DeleteActivity extends AppCompatActivity {

    Button deleteContactButton;
    Button backButton;
    EditText editTextDelPhoneNum;

    String url = "http://10.0.2.2:8080/AddressBookIVService_war_exploded/contacts/abservice/delete";
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        editTextDelPhoneNum = findViewById(R.id.editTextDelPhoneNum);

        deleteContactButton = findViewById(R.id.deleteContactButton);
        deleteContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact oldContact = new Contact();
                oldContact.setPhoneNumber(editTextDelPhoneNum.getText().toString());
                if(oldContact.getPhoneNumber().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter a phone number first", Toast.LENGTH_LONG).show();
                } else {
                    editTextDelPhoneNum.setText("");
                    httpPostDelete(oldContact);
                }
            }
        });

        backButton = findViewById(R.id.deleteBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private void httpPostDelete(Contact contact) {
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
                    DeleteActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Contact deleted!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}