package com.example.demo;

import javax.persistence.*;
import java.util.Set;
@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /*@NotNull
    @Size(min=3)*/
    private String genre;
    /*@NotNull
    @Size(min=3)*/
    private String artistname;
    private String albumtitle;
    private String headshot;
    @OneToMany(mappedBy = "album",cascade ={CascadeType.PERSIST,CascadeType.DETACH ,CascadeType.MERGE},fetch = FetchType.LAZY)
    public Set<Song> songs;


    public Album() {
    }

    public Album(String genre, String artistname, String albumtitle) {
        this.genre = genre;
        this.artistname = artistname;
        this.albumtitle = albumtitle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    public String getAlbumtitle() {
        return albumtitle;
    }

    public void setAlbumtitle(String albumtitle) {
        this.albumtitle = albumtitle;
    }

    public String getHeadshot() {
        return headshot;
    }

    public void setHeadshot(String headshot) {
        this.headshot = headshot;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
