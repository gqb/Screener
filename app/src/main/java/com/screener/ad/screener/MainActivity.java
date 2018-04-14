package com.screener.ad.screener;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;
import com.bumptech.glide.Glide;
import com.screener.ad.screener.network.RetrofitError;
import com.screener.ad.screener.network.ScreenerCallback;
import com.screener.ad.screener.network.ScreenerRestQueue;
import com.screener.ad.screener.network.model.AdEntry;
import com.screener.ad.screener.network.model.AdList;
import com.screener.ad.screener.network.model.Error;
import com.screener.ad.screener.network.model.HeartBit;
import com.screener.ad.screener.network.model.StartApp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private VideoView videoView;
    private ScreenerRestQueue screenerRestQueue = new ScreenerRestQueue();
    private String localDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localDir = getExternalFilesDir("adList").getAbsolutePath();
        initView();
//        onStartUp();
//        getAdList();
//        reportErro("12345");
        uploadInfo();
        uploadInfo();
        uploadInfo();
        uploadInfo();
        uploadInfo();
    }

    private void initView() {
        imageView = findViewById(R.id.splash);
        videoView = findViewById(R.id.video_view);
    }

    private void showImage(@NonNull String url) {
        imageView.setVisibility(View.VISIBLE);
        Glide.with(this)
            .load(url)
            .fitCenter()
            .into(imageView);
    }

    private void onStartUp() {
        StartApp.Up up = new StartApp.Up(Utils.getDeviceNum(this));
        screenerRestQueue.startApp(up.toString(), new ScreenerCallback<StartApp.Down>(this) {
            @Override
            public void handleSafeSuccess(StartApp.Down down, Response response) {
                if (!TextUtils.isEmpty(down.getPicture())) {
                    showImage(down.getPicture());
                }
            }

            @Override
            public void handleSuccess(StartApp.Down down, Response response) {

            }

            @Override
            public void handleSafeFailure(RetrofitError error) {

            }

            @Override
            public void handleFailure(RetrofitError error) {

            }
        });
    }

    private void getAdList() {
        AdList.Up up = new AdList.Up(Utils.getDeviceNum(this));
        screenerRestQueue.getAdList(up.toString(), new ScreenerCallback<AdList.Down>(this) {
            @Override
            public void handleSuccess(AdList.Down down, Response response) {
                parseAdList(down);
            }

            @Override
            public void handleSafeSuccess(AdList.Down down, Response response) {

            }

            @Override
            public void handleFailure(RetrofitError error) {

            }

            @Override
            public void handleSafeFailure(RetrofitError error) {

            }
        });
    }

    private void parseAdList(AdList.Down down) {
        if(down == null) {
            return;
        }
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        for(AdEntry adEntry : down.getAdEntryList()) {
            String adFilePath = adEntry.getAdfilepath();
            String extName = "";
            int index = adFilePath.lastIndexOf("/");
            if (index > 0) {
                extName = adFilePath.substring(index);
            }
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(adFilePath));
            request.setDestinationInExternalFilesDir(this, "adList", extName);
            request.setVisibleInDownloadsUi(true);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
            long id = downloadManager.enqueue(request);
            downloadListener(id);
        }
    }

    private void downloadListener(final long Id) {
        // 注册广播监听系统的下载完成事件。
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long ID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (ID == Id) {
                    Toast.makeText(getApplicationContext(), "任务:" + Id + " 下载完成!", Toast.LENGTH_LONG).show();
                }
            }
        };
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private Map<String, AdEntry> getLocalAdList() {
        Map<String, AdEntry> localAds = new HashMap<>();
        File file = new File(localDir);
        if (!file.exists() || !file.isDirectory()) {
            return null;
        }
        File[] files = file.listFiles();
        for (File ad : files) {

        }
        return localAds;
    }

    private void reportErro(String md5) {
        Error.Up up = new Error.Up(Utils.getDeviceNum(this), 1, md5);
        screenerRestQueue.reportErro(up.toString(), new Callback<Error.Down>() {
            @Override
            public void onResponse(Call<Error.Down> call, Response<Error.Down> response) {

            }

            @Override
            public void onFailure(Call<Error.Down> call, Throwable t) {

            }
        });
    }

    private void uploadInfo() {
        HeartBit.Up up = new HeartBit.Up();
        up.setAppVerion("1.0");
        up.setCurrentMd5("12312312312");
        up.setDeviceid(Utils.getDeviceNum(this));
        up.setFreeSpace(111.0);
        up.setLatitude(111);
        up.setLongitude(11111);
        up.setMemorySpace(334);
        up.setOsVersion("5.0");
        up.setStartTime(233423423);
        List<String> list = new ArrayList<>();
        list.add("123123");
        list.add("2342342");
        list.add("5342");
        up.setFileList("ADFIU-R5229-03FRQ-RFSDF;ADFIU-R5229-03FRQ-RFSDF;ADFIU-R5229-03FRQ-RFSDF;ADFIU-R5229-03FRQ-RFSDF");
        screenerRestQueue.uploadInfo(up.toString(), new Callback<HeartBit.Down>() {
            @Override
            public void onResponse(Call<HeartBit.Down> call, Response<HeartBit.Down> response) {

            }

            @Override
            public void onFailure(Call<HeartBit.Down> call, Throwable t) {

            }
        });
    }
}