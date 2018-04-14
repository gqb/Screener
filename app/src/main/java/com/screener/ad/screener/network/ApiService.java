package com.screener.ad.screener.network;

import com.screener.ad.screener.network.model.AdList;
import com.screener.ad.screener.network.model.Error;
import com.screener.ad.screener.network.model.HeartBit;
import com.screener.ad.screener.network.model.StartApp;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("uploadfile.aspx")
    Call<StartApp.Down> startApp(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("uploadfile.aspx")
    Call<AdList.Down> getAddList(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("uploadfile.aspx")
    Call<HeartBit.Down> uploadInfo(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("uploadfile.aspx")
    Call<Error.Down> reportErro(@Body RequestBody requestBody);
}