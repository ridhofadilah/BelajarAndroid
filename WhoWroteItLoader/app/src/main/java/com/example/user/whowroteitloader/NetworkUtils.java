package com.example.user.whowroteitloader;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 10-Jan-18.
 */

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String BookBaseURL = "https://www.googleapis.com/books/v1/volumes?";
    private static final String QUERY_PARAM = "q";
    private static final String MAX_RESULT = "maxResults";
    private static final String PRINT_TYPE = "printType";

    static String getBookInfo(String query){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String bookJSONstring = null;

        try{
            Uri builtURI= Uri.parse(BookBaseURL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, query)
                    .appendQueryParameter(MAX_RESULT, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();
            URL reqURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) reqURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream==null){
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line=reader.readLine()) != null){
                buffer.append(line+"\n");
            }
            if (buffer.length()==0){
                return null;
            }
            bookJSONstring = buffer.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, bookJSONstring);
        return bookJSONstring;
    }
}
