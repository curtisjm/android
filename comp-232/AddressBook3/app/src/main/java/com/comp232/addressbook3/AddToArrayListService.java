package com.comp232.addressbook3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.util.ArrayList;

public class AddToArrayListService extends Service {

    private ArrayList<Contact> contacts = new ArrayList<>();
    private final IBinder mBinder = new AddBinder();

    public class AddBinder extends Binder {
        AddToArrayListService getService() {
            return AddToArrayListService.this;
        }
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
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

    public void addToList(String cont) {
        contacts.add(0, new Contact(cont));
    }
}