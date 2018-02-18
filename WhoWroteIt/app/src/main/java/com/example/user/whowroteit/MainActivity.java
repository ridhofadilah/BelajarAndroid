package com.example.user.whowroteit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView titleTV;
    TextView authorTV;
    EditText searchBookET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleTV = (TextView) findViewById(R.id.titleText);
        authorTV = (TextView) findViewById(R.id.authorText);
        searchBookET = (EditText) findViewById(R.id.bookInput);
    }

    public void searchBooks(View view) {
        String query = searchBookET.getText().toString();

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),inputMethodManager.HIDE_NOT_ALWAYS);

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && query.length()!=0){
            new FetchBook(titleTV,authorTV).execute(query);
            titleTV.setText("");
            authorTV.setText(R.string.loading);
        } else {
            titleTV.setText("");
            authorTV.setText("Please check your network connection and try again");
        }
    }
}
