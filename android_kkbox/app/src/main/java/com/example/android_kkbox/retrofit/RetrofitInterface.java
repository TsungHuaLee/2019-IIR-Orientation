package com.example.android_kkbox;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Call;
import java.util.ArrayList;

public interface RetrofitInterface {
    String BASE_URL = "http://10.0.2.2:8000";

    @GET(value = {"/{AndroidsearchSong}/")
    Call<ArrayList<kkbox_song>> getSongInfo(@Path("like")String song_name);
}
