package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Playlist;
import it.uniroma3.siw.model.Song;
import it.uniroma3.siw.model.User;

@Repository
public interface PlaylistRepository extends CrudRepository<Playlist,Long>{

    public List<Playlist> findByTitle(String title);

    @Query("select p from Playlist p where :song not member of p.songs and p.user = :user")
    public List<Playlist> findByUSerAndSong(User user, Song song);

}