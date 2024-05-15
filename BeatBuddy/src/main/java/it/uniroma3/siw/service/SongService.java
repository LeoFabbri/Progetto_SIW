package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Song;
import it.uniroma3.siw.repository.SongRepository;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Song findById(Long id){
        return this.songRepository.findById(id).get();
    }

    public Iterable<Song> findAll(){
        return this.songRepository.findAll();
    }

    public void save(Song song){
        this.songRepository.save(song);
    }
    
}
