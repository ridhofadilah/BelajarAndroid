package com.example.user.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void increaseScore(View view){
        int score;
        String value;
        TextView textView;
        int viewID = view.getId();
        switch (viewID){
            case R.id.plus1:
                textView = (TextView) findViewById(R.id.score1);
                value = textView.getText().toString();
                score = Integer.parseInt(value);
                score++;
                textView.setText(String.valueOf(score));
                break;
            case R.id.plus2:
                textView = (TextView) findViewById(R.id.score2);
                value = textView.getText().toString();
                score = Integer.parseInt(value);
                score++;
                textView.setText(String.valueOf(score));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.night_mode){
            AppCompatDelegate.getDefaultNightMode();
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if (nightMode==AppCompatDelegate.MODE_NIGHT_YES){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        recreate();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void decreaseScore(View view) {
        int score;
        String value;
        TextView textView;
        int viewID = view.getId();
        switch (viewID){
            case R.id.minus1:
                textView = (TextView) findViewById(R.id.score1);
                value = textView.getText().toString();
                score = Integer.parseInt(value);
                score--;
                textView.setText(String.valueOf(score));
                break;
            case R.id.minus2:
                textView = (TextView) findViewById(R.id.score2);
                value = textView.getText().toString();
                score = Integer.parseInt(value);
                score--;
                textView.setText(String.valueOf(score));
        }
    }
}
