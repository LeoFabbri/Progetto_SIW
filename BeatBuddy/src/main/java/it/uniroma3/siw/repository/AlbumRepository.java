package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Album;

public interface AlbumRepository extends CrudRepository<Album,Long>{

    public List<Album> findByTitle(String title);

    public List<Album> findByTitleContainingIgnoreCase(String title);

}
