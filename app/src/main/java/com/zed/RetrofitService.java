package com.zed;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static HttpLoggingInterceptor logging   = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    private static OkHttpClient client  = new OkHttpClient.Builder().addInterceptor(logging).build();

    public static Retrofit createService() {
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
