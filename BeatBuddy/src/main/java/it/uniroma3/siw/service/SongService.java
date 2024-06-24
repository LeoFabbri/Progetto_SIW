package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Artist;
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

    public Song save(Song song){
        return this.songRepository.save(song);
    }

    public void deleteById(Long id){
        this.songRepository.deleteById(id);
    }

    public List<Song> findBySinger(Artist a){
        List<Song> songs = new ArrayList<Song>();
        for(Song s : this.songRepository.findAll()){
            if(s.getSingers().contains(a) && s.getAlbum()==null){
                songs.add(s);
            }
        }
        return songs;
    }
    
}
