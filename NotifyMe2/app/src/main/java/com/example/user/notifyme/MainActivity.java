package com.example.user.notifyme;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button notifyButton;
    private Button cancelButton;
    private Button updateButton;
    private NotificationManager notificationManager;
    private static final int NOTIFICATION_ID = 0;
    private String NOTIFICATION_GUIDE_URL = "https://developer.android.com/design/patterns/notifications.html";
    private static final String ACTION_UPDATE_NOTIFICATION = "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION";
    private static final String ACTION_CANCEL_NOTIFICATION = "com.example.android.notifyme.ACTION_CANCEL_NOTIFICATION";
    private BroadcastReceiver receiver = new NotificationReceiver();

    public class NotificationReceiver extends BroadcastReceiver {

        public NotificationReceiver(){

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case ACTION_CANCEL_NOTIFICATION:
                    cancelNotification();
                    break;
                case ACTION_UPDATE_NOTIFICATION:
                    updateNotification();
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notifyButton = (Button) findViewById(R.id.notify);
        cancelButton = (Button) findViewById(R.id.cancel);
        updateButton = (Button) findViewById(R.id.update);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_UPDATE_NOTIFICATION);
        intentFilter.addAction(ACTION_CANCEL_NOTIFICATION);
        registerReceiver(receiver, intentFilter);

        notifyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sendNotification();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                cancelNotification();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                updateNotification();
            }
        });
        notifyButton.setEnabled(true);
        updateButton.setEnabled(false);
        cancelButton.setEnabled(false);
    }

    public void sendNotification(){
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(NOTIFICATION_GUIDE_URL));
        PendingIntent learnMorePendingIntent = PendingIntent.getActivity
                (this, NOTIFICATION_ID, learnMoreIntent, PendingIntent.FLAG_ONE_SHOT);

        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("You've been Notified!")
                .setContentText("This is Notification Text")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .addAction(R.drawable.ic_learn_more, "Learn More", learnMorePendingIntent)
                .addAction(R.drawable.ic_update, "Update", updatePendingIntent);

        Notification notification = notifyBuilder.build();
        notificationManager.notify(NOTIFICATION_ID, notification);

        notifyButton.setEnabled(false);
        updateButton.setEnabled(true);
        cancelButton.setEnabled(true);
    }

    public void updateNotification(){
        Bitmap androidImage = BitmapFactory.decodeResource(getResources(), R.drawable.mascot_1);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(NOTIFICATION_GUIDE_URL));
        PendingIntent learnMorePendingIntent = PendingIntent.getActivity
                (this, NOTIFICATION_ID, learnMoreIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("You've been Notified!")
                .setContentText("This is Notification Text")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setStyle(new NotificationCompat.BigPictureStyle()
                    .bigPicture(androidImage)
                    .setBigContentTitle("Notification Updated!"))
                .addAction(R.drawable.ic_learn_more, "Learn More", learnMorePendingIntent);
        notificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());
        notifyButton.setEnabled(false);
        updateButton.setEnabled(false);
        cancelButton.setEnabled(true);
    }

    public void cancelNotification(){
        notificationManager.cancel(NOTIFICATION_ID);
        notifyButton.setEnabled(true);
        updateButton.setEnabled(false);
        cancelButton.setEnabled(false);
    }

}
