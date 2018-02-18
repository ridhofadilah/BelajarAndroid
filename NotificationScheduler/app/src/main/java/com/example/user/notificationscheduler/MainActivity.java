package com.example.user.notificationscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private JobScheduler jobSchedule;
    private static final int JOB_ID = 0;
    private Switch idleS, chargingS, periodicS;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.networkOptions);
        idleS = (Switch) findViewById(R.id.idleSwitch);
        chargingS = (Switch) findViewById(R.id.chargingSwitch);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        periodicS = (Switch) findViewById(R.id.periodicSwitch);
        final TextView labelSB = (TextView) findViewById(R.id.seekBarLabel);
        final TextView progressSB = (TextView) findViewById(R.id.seekBarProgress);

        periodicS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    labelSB.setText(R.string.periodic_interval);
                } else {
                    labelSB.setText(R.string.override_deadline);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress>0){
                    progressSB.setText(String.valueOf(progress) + " s");
                } else {
                    progressSB.setText(getString(R.string.notSet));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void scheduleJob(View view) {
        int selectedNetworkID = radioGroup.getCheckedRadioButtonId();
        int selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;

        switch (selectedNetworkID){
            case R.id.noNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
                break;
            case R.id.anyNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;
                break;
            case R.id.wifiNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED;
                break;
        }
        jobSchedule = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(getPackageName(), NotificationJobService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID,componentName)
                .setRequiredNetworkType(selectedNetworkOption);
        builder.setRequiresDeviceIdle(idleS.isChecked());
        builder.setRequiresCharging(chargingS.isChecked());

        int seekbarInteger = seekBar.getProgress();
        boolean seekbarSet = seekbarInteger >0;

        if (periodicS.isChecked()){
            if (seekbarSet){
                builder.setOverrideDeadline(seekbarInteger*1000);
            } else{
                Toast.makeText(MainActivity.this, getString(R.string.setPeriodic), Toast.LENGTH_SHORT).show();
            }
        } else {
            if (seekbarSet){
                builder.setOverrideDeadline(seekbarInteger*1000);
            }
        }

        boolean constraintSet = (selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE)
                || idleS.isChecked() || chargingS.isChecked() || seekbarSet;
        if (constraintSet){
            JobInfo myJob = builder.build();
            jobSchedule.schedule(myJob);
            Toast.makeText(MainActivity.this, getString(R.string.jobScheduled),Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.no_constraint), Toast.LENGTH_SHORT).show();
        }


    }

    public void cancelJob(View view) {
        if (jobSchedule != null){
            jobSchedule.cancelAll();
            jobSchedule = null;
            Toast.makeText(MainActivity.this, getString(R.string.jobCanceled), Toast.LENGTH_SHORT).show();
        }
    }
}
