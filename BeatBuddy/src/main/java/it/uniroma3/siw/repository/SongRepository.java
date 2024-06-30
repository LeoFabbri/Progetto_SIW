package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Song;

@Repository
public interface SongRepository extends CrudRepository<Song,Long>{

    public List<Song> findByTitle(String title);

    public List<Song> findByTitleContainingIgnoreCase(String title);

    public boolean existsByTitle(String title);

    @Query("select s from Song s where :singer member of s.singers")
    public List<Song> findBySinger(Artist singer);

    @Query("select s from Song s where :singer member of s.singers and :title = s.title")
    public List<Song> findByTitleAndSinger(String title, Artist singer);
    
}
