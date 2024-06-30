package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Artist;

@Repository
public interface ArtistRepository extends CrudRepository<Artist,Long>{

    public List<Artist> findByStageName(String stageName);

    public List<Artist> findByStageNameContainingIgnoreCase(String stageName);
    
    @Query(value = "select a from Artist a where a.id != :id")
    public List<Artist> findAllExceptId(Long id);

}