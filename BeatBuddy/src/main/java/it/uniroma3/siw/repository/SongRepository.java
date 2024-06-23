package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Song;

@Repository
public interface SongRepository extends CrudRepository<Song,Long>{

    public List<Song> findByTitle(String title);
    
}
