package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.repository.ArtistRepository;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public Artist findById(Long id){
        return this.artistRepository.findById(id).get();
    }

    public Iterable<Artist> findAll(){
        return this.artistRepository.findAll();
    }

    public void save(Artist Artist){
        this.artistRepository.save(Artist);
    }

    public List<Artist> findAllExceptId(Long id){
        return this.artistRepository.findAllExceptId(id);
    }
    
}
