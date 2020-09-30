package com.trendyol.playlist.controller;

import com.trendyol.playlist.domain.Playlist;
import com.trendyol.playlist.domain.Track;
import com.trendyol.playlist.exception.NullParameterExpection;
import com.trendyol.playlist.exception.PlaylistNotCreatedException;
import com.trendyol.playlist.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    //creates playlist
    @PostMapping
    public ResponseEntity addPlaylist(@RequestBody Playlist playlist) {
        try{
            playlistService.addPlaylist(playlist);
        }catch (PlaylistNotCreatedException e){
            return ResponseEntity.status(500).build();
        }catch (NullParameterExpection e) {
            return ResponseEntity.badRequest().body("Playlist detail is null.");
        }
        return ResponseEntity.ok().build();
    }
    //lists all playlists
    @GetMapping
    public ResponseEntity<List<Playlist>> listPlaylists() {
        return ResponseEntity.ok(playlistService.findAll());
    }
    //gets one playlist by its id
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Playlist>> listPlaylistById(@PathVariable String id) {
        return ResponseEntity.ok(playlistService.findById(id));
    }
    //deletes one playlist by its id
    @DeleteMapping("/{id}")
    public ResponseEntity deletePlaylistById(@PathVariable String id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }
    //gets playlists by its userid
    @GetMapping("/{userId}/user-playlists")
    public ResponseEntity<List<Playlist>> listPlaylistsByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(playlistService.findByUserId(userId));
    }
    //adds new track to a playlist by id
    @PostMapping("{id}/tracks")
    public ResponseEntity<Void> addTrackByID(@PathVariable String id, @RequestBody Track track) {
        playlistService.addTrack(id, track.getName(), track.getLength(), track.getArtist());
        return ResponseEntity.ok().build();
    }
    //deletes track from playlist by id
    @DeleteMapping("/{id}/tracks")
    public ResponseEntity removeTrackByID(@PathVariable String id, @RequestBody Track track) {
        playlistService.removeTrack(id, track.getName(), track.getArtist());
        return ResponseEntity.noContent().build();
    }
}
