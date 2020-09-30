package com.trendyol.playlist.service;

import com.trendyol.playlist.domain.Playlist;
import com.trendyol.playlist.exception.NullParameterExpection;
import com.trendyol.playlist.exception.PlaylistNotCreatedException;
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

    public void addPlaylist(Playlist playlist) throws PlaylistNotCreatedException, NullParameterExpection {
        try{
            playlistRepository.insert(playlist);
        }catch (PlaylistNotCreatedException e) {
            throw new PlaylistNotCreatedException(e.getMessage());
        }catch (NullParameterExpection e) {
            throw new NullParameterExpection(e.getMessage());
        }
    }
    public void deletePlaylist(String id) {
        playlistRepository.delete(id);
    }

    public Optional<Playlist> findById(String id) { return playlistRepository.findByIdOptional(id); }
    public List<Playlist> findByUserId(int userId) { return playlistRepository.findAllByUserId(userId); }

    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    public void addTrack(String id, String name, int length, String artist) {
        Playlist playlist = playlistRepository.findById(id);
        playlist.addTrack(name, length, artist);
        playlist.updateTrackSize();
        playlistRepository.update(playlist);
    }

    public void removeTrack(String id, String name, String artist) {
        Playlist playlist = playlistRepository.findById(id);
        playlist.removeTrack(name, artist);
        playlist.updateTrackSize();
        playlistRepository.update(playlist);
    }
}
