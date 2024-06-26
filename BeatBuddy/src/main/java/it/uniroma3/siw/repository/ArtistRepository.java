package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Artist;

@Repository
public interface ArtistRepository extends CrudRepository<Artist,Long>{

    public List<Artist> findByStageName(String stageName);

}