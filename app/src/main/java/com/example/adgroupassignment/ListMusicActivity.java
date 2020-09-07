package com.example.adgroupassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListMusicActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION=99;

    ArrayList<Song> songArrayList;
    ListView lvSongs;
    SongsAdapter songsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_music);

        lvSongs=findViewById(R.id.lvSongs);
        songArrayList=new ArrayList<>();

        songsAdapter=new SongsAdapter(this,songArrayList);
        lvSongs.setAdapter(songsAdapter);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.INTERNET},REQUEST_PERMISSION);
            return;
        }else{
            //I have permission to read from external storage
            getSongs();
        }
        //for (int i=1;i<=10; i++)
          //  songArrayList.add(new Song("Song"+i,"Artist"+i,"Path"+i));

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Song song=songArrayList.get(position);
                Intent openMusicPlayer=new Intent(ListMusicActivity.this,MusicPlayerActivity.class);
                openMusicPlayer.putExtra("song",song);
                startActivity(openMusicPlayer);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==REQUEST_PERMISSION){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getSongs();
            }
        }
    }

    private void getSongs() {
        //read songs from phone
        ContentResolver contentResolver=getContentResolver();
        Uri songUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor songCursor=contentResolver.query(songUri,null,null,null,null);
        if (songCursor != null&&songCursor.moveToFirst()){

            int indexTitle=songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int indexArtist=songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int indexData=songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

            do {
                String title=songCursor.getString(indexTitle);
                String artist=songCursor.getString(indexArtist);
                String path=songCursor.getString(indexData);

                songArrayList.add(new Song(title,artist,path));

            }while (songCursor.moveToNext());
        }
        songsAdapter.notifyDataSetChanged();
    }


}