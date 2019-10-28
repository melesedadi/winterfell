package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank
    @Column(name = "song_title", length = 250)
    private String songtitle;
    private String headshot;

    //private String headshot;

    @ManyToOne(cascade ={CascadeType.PERSIST,CascadeType.DETACH} ,fetch = FetchType.EAGER)
    @JoinColumn(name="album_id")
    private Album album;

    public Song() {
    }

    public Song(@NotBlank String songtitle, Album album) {
        this.songtitle = songtitle;
        this.album = album;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSongtitle() {
        return songtitle;
    }

    public void setSongtitle(String songtitle) {
        this.songtitle = songtitle;
    }

    public String getHeadshot() {
        return headshot;
    }

    public void setHeadshot(String headshot) {
        this.headshot = headshot;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
