package com.trendyol.playlist.repository;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryResult;
import com.trendyol.playlist.domain.Playlist;
import com.trendyol.playlist.exception.NullParameterExpection;
import com.trendyol.playlist.exception.PlaylistNotCreatedException;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class PlaylistRepository {
    private final Cluster couchbaseCluster;
    private final Collection playlistCollection;


    public PlaylistRepository(Cluster couchbaseCluster, Collection playlistCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.playlistCollection = playlistCollection;
    }

    public void insert(Playlist playlist) throws PlaylistNotCreatedException, NullParameterExpection {
        if (playlist==null) { throw new NullParameterExpection("Playlist detail is missing."); }

        try {
            playlistCollection.insert(playlist.getId(), playlist);
        }catch (Exception e){
            throw new PlaylistNotCreatedException(e.getMessage());
        }
    }
    public void update(Playlist playlist) { playlistCollection.replace(playlist.getId(), playlist); }

    public void delete(String id) {
        playlistCollection.remove(id);
    }

    public Playlist findById(String id) {
        GetResult getResult = playlistCollection.get(id);
        Playlist playlist = getResult.contentAs(Playlist.class);
        return playlist;
    }

    public Optional<Playlist> findByIdOptional(String id) {
        try {
            GetResult getResult = playlistCollection.get(id);
            Playlist playlist = getResult.contentAs(Playlist.class);
            return Optional.of(playlist);

        } catch (DocumentNotFoundException exception) {
            return Optional.empty();
        }
    }

    public List<Playlist> findAll () {
        String statement = "Select `playlist`.* from playlist";
        QueryResult query = couchbaseCluster.query(statement);
        return query.rowsAs(Playlist.class);
    }

    public List<Playlist> findAllByUserId (int userId) {
        String statement = "Select `playlist`.* from playlist where userId=$userId";
        QueryResult query = couchbaseCluster.query(statement, QueryOptions.queryOptions()
                .parameters(JsonObject.create().put("userId",userId)));
        return query.rowsAs(Playlist.class);
    }

}
