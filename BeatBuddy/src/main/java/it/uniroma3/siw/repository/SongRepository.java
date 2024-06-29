package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Song;

public interface SongRepository extends CrudRepository<Song,Long>{

    public List<Song> findByTitle(String title);

    public List<Song> findByTitleContainingIgnoreCase(String title);
    
}
