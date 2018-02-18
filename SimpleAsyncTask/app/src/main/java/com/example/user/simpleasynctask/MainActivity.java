package com.example.user.simpleasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private static final String TEXT_STATE = "current task";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView1);
        if (savedInstanceState != null){
            tv.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public void startTask(View view) {
        tv.setText("Napping....");
        SimpleAsyncTask sat = new SimpleAsyncTask(tv);
        sat.execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, tv.getText().toString());

    }



}
