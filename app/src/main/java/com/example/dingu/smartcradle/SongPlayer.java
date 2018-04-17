package com.example.dingu.smartcradle;

/**
 * Created by dingu on 17/4/18.
 */

public class SongPlayer {
    private String songName;
    private Boolean isPlaying;

    public SongPlayer() {
    }
    public SongPlayer(String songName, Boolean isPlaying) {
        this.songName = songName;

        this.isPlaying = isPlaying;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Boolean getPlaying() {
        return isPlaying;
    }

    public void setPlaying(Boolean playing) {
        isPlaying = playing;
    }


}
