package com.comp232.factorialapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class FactorialService extends Service {

    // binder given to clients
    private final IBinder mBinder = new FactBinder();
    private int factResult = 1;

    // returns this instance of FactorialService to all clients so that they can
    // then invoke public methods such as getFactorial below
    public class FactBinder extends Binder {
        FactorialService getService() {
            return FactorialService.this;
        }
    }

    // not currently used
    public FactorialService() {
    }

    // returns and instance of final mBinder interface
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // public method for activity clients within program
    public int getFactorial(int y) {
        factResult = 1;
        int x1 = 1;
        for(x1 = y; x1 > 1; x1--) {
            factResult *= x1;
        }
        return factResult;
    }
}