package com.example.android_kkbox.retrofit;
import com.example.android_kkbox.model.kkbox_song;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {
    String BASE_URL = "http://10.0.2.2:8000";

    @GET(value = "/GET/{pre}")
    Call<JsonObject> getLikeInfo(@Path("pre") String like);
}
