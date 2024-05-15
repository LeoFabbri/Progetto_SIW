package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.repository.AlbumRepository;

@Service
public class AlbumService {
    
    @Autowired
    private AlbumRepository albumRepository;

    public Album findById(Long id){
        return this.albumRepository.findById(id).get();
    }

    public Iterable<Album> findAll(){
        return this.albumRepository.findAll();
    }

    public void save(Album Album){
        this.albumRepository.save(Album);
    }

}
