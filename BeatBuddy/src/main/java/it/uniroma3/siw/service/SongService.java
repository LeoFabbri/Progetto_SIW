package it.uniroma3.siw.service;

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

    public List<Song> findByTitle(String title){
        return this.songRepository.findByTitle(title);
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
        return this.songRepository.findBySinger(a);
    }

    public List<Song> findByTitleAndSinger(String title, Artist a){
        return this.songRepository.findByTitleAndSinger(title, a);
    }

    public List<Song> findByTitleContainingIgnoreCase(String title){
        return songRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Song> findByArtistWithoutAlbum(Artist artist){
        return this.songRepository.findByArtistWithoutAlbum(artist);
    }
}
