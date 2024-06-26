package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Playlist;

@Repository
public interface PlaylistRepository extends CrudRepository<Playlist,Long>{

    public List<Playlist> findByTitle(String title);

}