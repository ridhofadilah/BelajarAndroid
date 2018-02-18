package com.example.user.notificationscheduler;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by User on 12-Jan-18.
 */

public class NotificationJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (this,0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.jobService))
                .setContentText(getString(R.string.jobRunning))
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.drawable.ic_notif)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        notificationManager.notify(0, builder.build());

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
