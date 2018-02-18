package com.example.user.droidcafe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {
    private final static String TAG_ACTIVITY = OrderActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

    public void onRadioButtonCicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.sameday:
                if (checked){
                    displayToast(getString(R.string.chosen) +
                    getString(R.string.sameday));
                }
                break;
            case R.id.nextday:
                if (checked){
                    displayToast(getString(R.string.chosen) +
                    getString(R.string.nextday));
                }
                break;
            case R.id.pickup:
                if (checked){
                    displayToast(getString(R.string.chosen)+
                    getString(R.string.pickup));
                }
                break;
            default:
                Log.d(TAG_ACTIVITY, getString(R.string.nothing_clicked));
                break;
        }
    }

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
