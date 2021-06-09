package com.comp232.addressbookiv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.reflect.Type;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class ListActivity extends AppCompatActivity {

    ListView myListView;
    Button backButton;

    String json;
    String url = "http://10.0.2.2:8080/AddressBookIVService_war_exploded/contacts/abservice/list";

    ArrayList<Contact> contacts = new ArrayList<>();
    OkHttpClient client = new OkHttpClient();
    ArrayAdapter<Contact> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.i("List Act", "here");

        myListView = findViewById(R.id.myListView);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);

        httpGet();

        backButton = findViewById(R.id.listBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void httpGet() {
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    json = Objects.requireNonNull(response.body()).string();
                    ListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            Type contactListType = new TypeToken<ArrayList<Contact>>(){}.getType();
                            contacts = gson.fromJson(json, contactListType);
                            myListView.setAdapter(arrayAdapter);
                            arrayAdapter.clear();
                            arrayAdapter.addAll(contacts);
                            arrayAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

}
