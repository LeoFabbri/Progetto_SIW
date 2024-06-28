package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Artist;

@Repository
public interface AlbumRepository extends CrudRepository<Album,Long>{

    public List<Album> findByTitle(String title);

    public boolean existsByTitle(String title);

    @Query("select a from Album a where :artist member of a.artists and :title = a.title")
    public List<Album> findByTitleAndArtist(String title, Artist artist);

}
