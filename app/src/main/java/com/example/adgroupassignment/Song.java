package com.example.adgroupassignment;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

public class Song  implements Serializable {

    private String title;
    private String artist;
    private String path;
    private String album;
    private String duration;
    private String id;


    public Song(String title, String artist, String path, String album, String duration,String id) {

        this.title = title;
        this.artist = artist;
        this.path = path;
        this.album = album;
        this.duration = duration;
        this.id = id;
    }

    public Song() { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
