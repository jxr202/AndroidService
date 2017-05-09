package com.jxr202.androidservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AdditionService extends Service {

    private static final String TAG = "AdditionService";

    public AdditionService() {
        Log.i(TAG, "Service create..");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand..");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind..");
        return new IAdditionInterface.Stub() {

            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public int add(int value1, int value2) throws RemoteException {
                return value1 + value2;
            }
        };
    }
}
