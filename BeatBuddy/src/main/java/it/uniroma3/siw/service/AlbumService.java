package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Artist;
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

    public void deleteById(Long id){
        this.albumRepository.deleteById(id);
    }

    public List<Album> findByArtist(Artist artist){
        List<Album> l = new ArrayList<Album>();
        for (Album a : this.albumRepository.findAll()) {
            if(a.getArtists().contains(artist)){
                l.add(a);
            }
        }
        return l;
    }

}
