package com.screener.ad.screener.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.solver.Cache;

import com.screener.ad.screener.network.model.AdList;
import com.screener.ad.screener.network.model.Error;
import com.screener.ad.screener.network.model.HeartBit;
import com.screener.ad.screener.network.model.StartApp;

import java.util.Collection;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class ScreenerRestQueue {

    private static final String TAG = "ScreenerRestQueue";
    private static final String endPoint = "http://47.94.157.214:8035/";
    private ApiService apiService;

    public ScreenerRestQueue() {
        setUpApiService();
    }

    public <T> void startApp(String jsonString, Callback<StartApp.Down> callback){
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        apiService.startApp(requestBody).enqueue(callback);
    }

    public void getAdList(String jsonString, Callback<AdList.Down> callback){
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        apiService.getAddList(requestBody).enqueue(callback);
    }

    public void uploadInfo(String jsonString, Callback<HeartBit.Down> callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        apiService.uploadInfo(requestBody).enqueue(callback);
    }

    public void reportErro(String jsonString, Callback<Error.Down> callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        apiService.reportErro(requestBody).enqueue(callback);
    }

    private void setUpApiService() {
        if (apiService == null) {
            apiService = createRestService(ApiService.class, endPoint, getOkHttpClient(null, true, null));
        }
    }

    @NonNull
    private OkHttpClient getOkHttpClient(Collection<Interceptor> interceptors, boolean followRedirects, @Nullable Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.build();
    }

    public <T> T createRestService(Class<T> restClass, String endpoint, Collection<Interceptor> interceptors) {
        OkHttpClient client = getOkHttpClient(interceptors, false, null);
        return createRestService(restClass, endpoint, client);
    }

    private <T> T createRestService(Class<T> restClass, String endpoint, OkHttpClient client) {
        return createRestAdapter(endpoint, client).create(restClass);
    }

    private Retrofit createRestAdapter(String endpoint, OkHttpClient client) {
        return new Retrofit.Builder()
            .baseUrl(endpoint)
            .client(client)
            .addConverterFactory(FastJsonConverterFactory.create())
//            .addCallAdapterFactory(GrindrCallAdapterFactory.create())
            .build();
    }
}