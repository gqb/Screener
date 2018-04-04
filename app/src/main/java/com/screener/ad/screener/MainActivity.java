package com.screener.ad.screener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.screener.ad.screener.network.ScreenerRestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ScreenerRestQueue screenerRestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JSONObject action = new JSONObject();
        try {
            action.put("action", "startapp");
            action.put("deviceid", "13146452373");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        screenerRestQueue = new ScreenerRestQueue();
        screenerRestQueue.postJson(null, action.toString());
    }
}