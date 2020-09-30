package com.trendyol.playlist.service;

import com.trendyol.playlist.domain.Playlist;
import com.trendyol.playlist.domain.Track;
import com.trendyol.playlist.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void addPlaylist(Playlist playlist) {
        playlistRepository.insert(playlist);
    }
    public void deletePlaylist(String id) {
        playlistRepository.delete(id);
    }

    public Playlist findById(String id) { return playlistRepository.findById(id); }
    public List<Playlist> findByUserId(int userId) { return playlistRepository.findAllByUserId(userId); }

    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    public void addTrack(String id, String name, int length, String artist) {
        Playlist playlist = playlistRepository.findById(id);
        playlist.addTrack(name, length, artist);
        playlistRepository.update(playlist);
    }

    public void removeTrack(String id, String name, int length, String artist) {
        Playlist playlist = playlistRepository.findById(id);
        playlist.removeTrack(name, length, artist);
        playlistRepository.update(playlist);
    }
}
