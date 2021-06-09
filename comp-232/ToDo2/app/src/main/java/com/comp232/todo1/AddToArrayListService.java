package com.comp232.todo1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class AddToArrayListService extends Service {

    private final IBinder mBinder = new AddBinder();
    private ArrayList<Task> toDoItems = new ArrayList<>();

    public class AddBinder extends Binder {
        AddToArrayListService getService() {
            return AddToArrayListService.this;
        }
    }

    public ArrayList<Task> getToDoItems() {
        return toDoItems;
    }

    public void setToDoItems(ArrayList<Task> toDoItems) {
        this.toDoItems = toDoItems;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Toast.makeText(this, "Service created...", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service created...", Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service created...", Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addToList(String task, String date) {
        toDoItems.add(0, new Task(task, date));
    }
}