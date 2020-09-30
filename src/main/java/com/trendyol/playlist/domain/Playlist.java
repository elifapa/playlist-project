package com.trendyol.playlist.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Playlist {
    private String id;
    private int userId;
    private String name;
    private String description;
    private int followersCount;
    private int tracksCount;
    private List<Track> tracks;

    public Playlist() {
        this.id = UUID.randomUUID().toString();
        this.userId = 1;
        this.name = "chill list";
        this.description = "chillest list in the planet";
        this.followersCount = 0;
        this.tracksCount = 0;
        this.tracks = Arrays.asList(new Track("chillsong1",4, "chill artist"));
    }

    public Playlist(int userId, String name, String description, int followersCount, int tracksCount, List<Track> tracks) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.followersCount = followersCount;
        this.tracksCount = tracksCount;
        this.tracks = tracks;
    }
    public void addTrack(String name, int length, String artist ) {
        Track track = new Track(name, length, artist);
        this.tracks.add(track);
    }

    public void removeTrack(String name, int length, String artist ) {
        Track track = new Track(name, length, artist);
        this.tracks.remove(track);
    }
}
