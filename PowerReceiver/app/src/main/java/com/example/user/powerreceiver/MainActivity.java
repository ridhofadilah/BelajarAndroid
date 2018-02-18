package com.example.user.powerreceiver;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private PackageManager packageManager;
    private ComponentName componentName;
    private static final String ACTION_CUSTOM_BROADCAST =
            "com.example.user.powerreceiver.ACTION_CUSTOM_BROADCAST";
    private CustomReceiver customReceiver = new CustomReceiver();

    @Override
    protected void onStop() {
        super.onStop();
        packageManager.setComponentEnabledSetting(componentName,
                packageManager.COMPONENT_ENABLED_STATE_DISABLED,
                packageManager.DONT_KILL_APP);
    }

    @Override
    protected void onStart() {
        super.onStart();
        packageManager.setComponentEnabledSetting(componentName,
                packageManager.COMPONENT_ENABLED_STATE_ENABLED,
                packageManager.DONT_KILL_APP);
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(customReceiver);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        componentName = new ComponentName(this, CustomReceiver.class);
        packageManager = getPackageManager();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(customReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }
}
