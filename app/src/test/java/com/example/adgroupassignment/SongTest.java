package com.example.adgroupassignment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SongTest {

    private String title = "Some title";
    private String artist = "Some artist";
    private String path = "Some path";
    private String album = "Some album";
    private String duration = "Some duration";
    private String id = "Some id";

    Song song = new Song(title, artist, path, album, duration,id);;

    @Before
    public void setUp() throws Exception {
        Song song = new Song();
    }

    @Test
    public void getTitle() {
        assertEquals(song.getTitle(),"Some title");
    }

    @Test
    public void getArtist() {
        assertEquals(song.getArtist(),"Some artist");
    }

    @Test
    public void getPath() {
        assertEquals(song.getPath(),"Some path");
    }

    @Test
    public void getAlbum() {
        assertEquals(song.getAlbum(),"Some album");
    }

    @Test
    public void getDuration() {
        assertEquals(song.getDuration(),"Some duration");
    }

    @Test
    public void getId() {
        assertEquals(song.getId(),"Some id");
    }
}