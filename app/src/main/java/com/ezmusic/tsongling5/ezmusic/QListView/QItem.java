package com.ezmusic.tsongling5.ezmusic.QListView;

/**
 * Created by TsongLing5 on 2017/11/2.
 */

public class QItem {

    private String name,artist,album;

    public QItem(String name, String artist, String album){

        this.name=name;
        this.artist=artist;
        this.album=album;
    }

    public void setName(String name){
        this.name=name;

    }

    public void setArtist(String artist){
        this.artist=artist;

    }

    public void setAlbum(String album){
        this.album=album;

    }

    public String getName(){
        return this.name;
    }

    public String getArtist(){
        return this.artist;
    }
    public String getAlbum(){
        return this.album;
    }
}
