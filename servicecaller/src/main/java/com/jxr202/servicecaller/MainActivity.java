package com.jxr202.servicecaller;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jxr202.androidservice.IAdditionInterface;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    IAdditionInterface service;
    ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "onServiceConnected..");
            service = IAdditionInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected..");
            service = null;
        }
    };

    EditText value1, value2;
    TextView result;
    Button equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initService();

        value1 = (EditText)findViewById(R.id.v1);
        value2 = (EditText)findViewById(R.id.v2);
        result = (TextView)findViewById(R.id.result);
        equal = (Button) findViewById(R.id.equal);
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v1 = Integer.parseInt(value1.getText().toString());
                int v2 = Integer.parseInt(value2.getText().toString());
                int res = 0;

                try {
                    res = service.add(v1, v2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                result.setText(String.valueOf(res));
            }
        });
    }


    private void initService() {
        Intent intent = new Intent();
        intent.setClassName("com.jxr202.androidservice", "com.jxr202.androidservice.AdditionService");
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }




}
