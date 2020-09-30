package com.trendyol.playlist.controller;

import com.trendyol.playlist.domain.Playlist;
import com.trendyol.playlist.domain.Track;
import com.trendyol.playlist.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<Void> addPlaylist(@RequestBody Playlist playlist) {
        playlistService.addPlaylist(playlist);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> listPlaylists() {
        return ResponseEntity.ok(playlistService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> listPlaylistById(@PathVariable String id) {
        return ResponseEntity.ok(playlistService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePlaylistById(@PathVariable String id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/user-playlists")
    public ResponseEntity<List<Playlist>> listPlaylistsByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(playlistService.findByUserId(userId));
    }

    @PostMapping("{id}/tracks")
    public ResponseEntity<Void> addTrackByID(@PathVariable String id, @RequestBody Track track) {
        playlistService.addTrack(id, track.getName(), track.getLength(), track.getArtist());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/tracks")
    public ResponseEntity removeTrackByID(@PathVariable String id, @RequestBody Track track) {
        playlistService.removeTrack(id, track.getName(), track.getLength(), track.getArtist());
        return ResponseEntity.noContent().build();
    }
}
