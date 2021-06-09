package com.comp232.todowitem;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;

public class TodoService extends Service {

    private final IBinder mBinder = new TodoBinder();

    public class TodoBinder extends Binder {
        TodoService getService() {
            return TodoService.this;
        }
    }

    public TodoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void addToStorage(String newTask, ArrayList<String> storage) {
        storage.add(newTask);
    };
}