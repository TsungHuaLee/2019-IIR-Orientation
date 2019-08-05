package com.example.android_kkbox;
import android.util.Log;
import android.view.View;

import com.example.android_kkbox.model.kkbox_song;
import com.example.android_kkbox.retrofit.RetrofitInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Call;
import retrofit2.Response;


public class RetrofitRequest {

    public static RetrofitRequest mInstance;

    static {
        mInstance = new RetrofitRequest();
    }

   RetrofitInterface RetrofitInterface;

    public RetrofitRequest()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    public static RetrofitRequest getInstance()
    {
        if (mInstance == null)
            mInstance = new RetrofitRequest();
        return mInstance;
    }

    public RetrofitInterface getAPI_interface() { return RetrofitInterface;}

    public void Request_LikeInfo(String preference)
    {
        RetrofitInterface = RetrofitRequest.getInstance().getAPI_interface();

        Call<JsonObject> call = getAPI_interface().getLikeInfo(preference);

        call.enqueue(new Callback<JsonObject>()
        {
            @Override
            public void onResponse(Call<JsonObject>call, Response<JsonObject> response)
            {
                JsonObject response_object = response.body();
                Log.d( "response string", response.toString());

                if(response.isSuccessful()){
                    Log.d("response body string", "success");
                    kkbox_song songs = new Gson().fromJson(response_object, kkbox_song.class);
                }
                else{
                    Log.d("response body string", "failure");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("response fail", String.valueOf(call.request().url()));
                Log.d("response fail", t.getMessage());
            }
        });
    }

}