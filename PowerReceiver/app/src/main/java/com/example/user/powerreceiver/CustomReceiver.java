package com.example.user.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    private static final String ACTION_CUSTOM_BROADCAST =
            "com.example.user.powerreceiver.ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        String toastMessage = null;
        switch (intentAction){
            case Intent.ACTION_POWER_CONNECTED:
                toastMessage = "POWER CONNECTED";
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                toastMessage = "POWER DISCONNECTED";
                break;
            case ACTION_CUSTOM_BROADCAST:
                toastMessage = context.getString(R.string.broadcastReceiver);
                break;
        }
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }
}
