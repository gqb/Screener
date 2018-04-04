package com.screener.ad.screener.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.solver.Cache;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ScreenerRestQueue {

    private static final String TAG = "ScreenerRestQueue";
    private static final String endPoint = "http://47.94.157.214:8035/";
    private ApiService apiService;

    public ScreenerRestQueue() {
        setUpApiService();
    }

    public void postJson(Class response, String jsonString){
        try {
            jsonString = URLEncoder.encode(jsonString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type: application/json"), jsonString);
        apiService.postJson(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, response.toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
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
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(GrindrCallAdapterFactory.create())
            .build();
    }
}