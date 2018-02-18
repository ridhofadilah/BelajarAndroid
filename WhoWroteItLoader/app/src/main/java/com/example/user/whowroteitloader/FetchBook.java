package com.example.user.whowroteitloader;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User on 10-Jan-18.
 */

public class FetchBook extends AsyncTask<String, Void, String> {
    private TextView titleText;
    private TextView authorText;

    public FetchBook(TextView titleTV, TextView authorTV){
        titleText = titleTV;
        authorText = authorTV;
    }

    @Override
    protected String doInBackground(String... params) {
        return NetworkUtils.getBookInfo(params[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            for (int i=0;i<itemsArray.length();i++){
                JSONObject book = itemsArray.getJSONObject(i);
                String title = null;
                String author = null;
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                try {
                    title = volumeInfo.getString("title");
                    author = volumeInfo.getString("authors");
                } catch (Exception e){
                    e.printStackTrace();
                }
                if (title!=null && author!=null){
                    titleText.setText(title);
                    authorText.setText(author);
                    return;
                }
            }
            titleText.setText("No Results Found");
            authorText.setText("");
        } catch (JSONException e) {
            titleText.setText("No Results Found");
            authorText.setText("");
            e.printStackTrace();
        }
    }
}
