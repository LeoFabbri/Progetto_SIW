package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Playlist;

public interface PlaylistRepository extends CrudRepository<Playlist,Long>{

    public List<Playlist> findByTitle(String title);

}