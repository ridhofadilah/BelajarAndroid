package com.example.user.hellotoast;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mCount =0;
    private TextView mShowCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void countUp(View view){
        mCount++;
        mShowCount = (TextView) findViewById(R.id.show_count);
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
        if (mCount==10){
            showToast(view);
            mCount = 0;
        }
    }

    public void showToast(View view){
        Context context = getApplicationContext();

        Toast toast = Toast.makeText(context, R.string.toast_message, Toast.LENGTH_LONG);
        toast.show();
    }
}


