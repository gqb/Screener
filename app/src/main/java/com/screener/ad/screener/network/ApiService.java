package com.screener.ad.screener.network;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("uploadfile.aspx")
    Call<ResponseBody> postJson(@Body RequestBody requestBody);
}