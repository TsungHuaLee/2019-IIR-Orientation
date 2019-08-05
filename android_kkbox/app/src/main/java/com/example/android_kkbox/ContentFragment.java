package com.example.android_kkbox;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.android_kkbox.model.kkbox_song;
import com.example.android_kkbox.retrofit.RetrofitInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContentFragment extends Fragment {

    private String preference;
    TextView label;
    RetrofitRequest req;

    public ContentFragment() {
        // Required empty public constructor
    }

    //for accepting bundle
    public static ContentFragment newInstance(Bundle args){
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            preference = (String) getArguments().getString("preference");
            Log.d("Content Fragment", preference);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_fragment, container, false);

        label = view.findViewById(R.id.preference);

        label.setText(preference);


        Request_LikeInfo(view, preference);

        return view;
    }


    private void Request_LikeInfo(final View view, String preference)
    {
        Log.d("SecondFragment", "create request: " + preference);
        final View framentView = view;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(RetrofitInterface.class).getLikeInfo(preference).enqueue(
                new Callback<JsonObject>()
                {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response)
                    {
                        JsonObject response_object = response.body();
                        Log.d( "response string", response.toString());

                        if(response.isSuccessful())
                        {
                            Log.d("response body string", "success");
                            kkbox_song songs = new Gson().fromJson(response_object, kkbox_song.class);

                            try
                            {
                                setSongsUI(framentView, songs);
                            }
                            catch (ParseException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            Log.d("response body string", "failure");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t)
                    {
                        Log.d("response fail", String.valueOf(call.request().url()));
                        Log.d("response fail", t.getMessage());
                    }
                }
        );

    }
    private void setSongsUI(View view, kkbox_song songs) throws ParseException
    {
        StringBuffer sb = new StringBuffer();
        ArrayList<String> song_name = songs.getSong_name();
        ArrayList<String> artist = songs.getArtist();
//        Iterator<String> it1 = song_name.iterator();
//        Iterator<String> it2 = artist.iterator();
        TextView data;
//        while (it1.hasNext() && it2.hasNext()) {
//            sb.append("song name:" + it1 + " ");
//            sb.append("artist:" + it2 + "\n");
//        }
        data = view.findViewById(R.id.song_name);
        data.setText(song_name.get(0));

    }
}
