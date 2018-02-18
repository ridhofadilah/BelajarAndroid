package com.example.user.worldlistsql_starter;

import android.view.View;

/**
 * Created by User on 13-Jan-18.
 */

public class MyButtonOnClickListener implements View.OnClickListener {
    private static final String TAG = View.OnClickListener.class.getSimpleName();

    int id;
    String word;

    public MyButtonOnClickListener(int id, String word) {
        this.id = id;
        this.word = word;
    }

    public void onClick(View v) {
        // Implemented in WordListAdapter
    }
}
