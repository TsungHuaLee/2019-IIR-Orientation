package com.example.android_kkbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class kkbox_song {
    @Expose
    @SerializedName("song_name")
    private ArrayList<String> song_name = new ArrayList<String>();

    @Expose
    @SerializedName("artist")
    private ArrayList<String> artist = new ArrayList<String>();

    public kkbox_song(ArrayList<String> song_name, ArrayList<String> artist){
        this.song_name = song_name;
        this.artist = artist;
    }

    public ArrayList<String> getSong_name() {return song_name;}
    public ArrayList<String> getArtist() {return artist;}

}

