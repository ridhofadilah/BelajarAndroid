package com.example.user.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by User on 10-Jan-18.
 */

public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {
    TextView textView;

    public SimpleAsyncTask(TextView tv){
        textView = tv;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);
        int s = n*200;
        try {
            Thread.sleep(s);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return "Awake at last after sleeping for "+s+" milliseconds!";
    }

    protected void onPostExecute(String result){
        textView.setText(result);
    }
}
