package com.example.user.standup;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ToggleButton alarmTB;
    private TextView alarmTV;
    private NotificationManager notificationManager;
    private static final int NOTIFICATION_ID = 0;
    private PendingIntent contentPendingIntent;
    private static final String ACTION_NOTIFY = "com.example.android.standup.ACTION_NOTIFY";
    private AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmTB = (ToggleButton) findViewById(R.id.toogleButton);
        alarmTV = (TextView) findViewById(R.id.alarmToogle);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        final Intent notifyIntent = new Intent(ACTION_NOTIFY);
        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent,
                PendingIntent.FLAG_NO_CREATE) != null);
        alarmTB.setChecked(alarmUp);

        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        alarmTB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String toastMessage;
                if (isChecked){
                    toastMessage = getString(R.string.alarm_on);
                    long triggerTime = SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_FIFTEEN_MINUTES;
                    long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
                    alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            triggerTime, repeatInterval, notifyPendingIntent);
                    AlarmReceiver alarmReceiver = new AlarmReceiver();
                    alarmReceiver.onReceive(MainActivity.this,notifyIntent);
                } else {
                    toastMessage = getString(R.string.alarm_off);
                    alarmManager.cancel(notifyPendingIntent);
                    notificationManager.cancelAll();
                }
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
