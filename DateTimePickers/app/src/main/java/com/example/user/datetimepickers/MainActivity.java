package com.example.user.datetimepickers;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDatePickerDialog(View view) {
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), getString(R.string.date_picker));
    }

    public void showTimePickerDialog(View view) {
        DialogFragment fragment = new TimePickerFragment();
        fragment.show(getSupportFragmentManager(), getString(R.string.time_picker));
    }

    public void processDatePickerResult(int year, int month, int day){
        String yearString = Integer.toString(year);
        String monthString = Integer.toString(month+1);
        String dayString = Integer.toString(day);
        String dateMessage = (dayString+"/"+monthString+"/"+yearString);
        Toast.makeText(this, getString(R.string.date)+dateMessage,Toast.LENGTH_SHORT).show();
    }

    public void processTimePickerResult(int hourOfDay, int minute){
        String hourString = Integer.toString(hourOfDay);
        String minuteString = Integer.toString(minute);
        String timeMessage = (hourString+":"+minuteString);
        Toast.makeText(this,getString(R.string.time)+timeMessage, Toast.LENGTH_SHORT).show();
    }
}
