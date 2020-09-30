package com.trendyol.playlist.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Track {
    private String name;
    private int length;
    private String artist;

    public Track() {
    }

    public Track(String name, int length, String artist) {
        this.name = name;
        this.length = length;
        this.artist = artist;
    }
}
