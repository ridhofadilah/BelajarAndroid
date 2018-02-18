package com.example.user.whowroteitloader;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by User on 10-Jan-18.
 */

public class BookLoader extends AsyncTaskLoader <String> {
    private String query;
    public BookLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(query);
    }
}
