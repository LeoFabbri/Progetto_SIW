package it.uniroma3.siw.service;

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

    public List<Album> findByTitleContainingIgnoreCase(String title){
        return albumRepository.findByTitleContainingIgnoreCase(title);
    }

    public void deleteById(Long id){
        this.albumRepository.deleteById(id);
    }

    public List<Album> findByArtist(Artist artist){
        return this.albumRepository.findByArtist(artist);
    }

    public List<Album> findByTitleAndArtist(String title, Artist a){
        return this.albumRepository.findByTitleAndArtist(title, a);
    }
}
