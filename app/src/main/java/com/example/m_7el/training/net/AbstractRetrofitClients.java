package com.example.m_7el.training.net;


import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class AbstractRetrofitClients<T> {
    protected T mApis;
    private Class<T> mApiClass;
    private final String baseUrl;

    public AbstractRetrofitClients(String baseUrl, Class<T> mApiClass) {
        this.mApiClass = mApiClass;
        this.baseUrl = baseUrl;
        mApis = initiateRetrofit();
    }

    private T initiateRetrofit() {
        Retrofit retrofit;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(mApiClass);
    }


    protected <R> void enqueueCall(@NonNull Call<R> call, @NonNull final ApiCallback<R, String> callback) {
        call.enqueue(new Callback<R>() {


            @Override
            public void onResponse(@NonNull Call<R> call, @NonNull Response<R> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<R> call, @NonNull Throwable t) {
                callback.onError(t.toString());

            }
        });
    }
}