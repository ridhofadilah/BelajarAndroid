package com.example.user.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText websiteEditText;
    private EditText locationEditText;
    private EditText shareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        websiteEditText = (EditText) findViewById(R.id.editText_uri);
        locationEditText = (EditText) findViewById(R.id.editText_loc);
        shareEditText = (EditText) findViewById(R.id.editText_share);
    }
    public void openWebsite(View view) {
        String url = websiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "cant handle this");
        }
    }

    public void openLocation(View view) {
        String loc = locationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "cant handle thisss");
        }
    }

    public void shareText(View view) {
        String txt = shareEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with : ")
                .setText(txt)
                .startChooser();
        
    }
}
