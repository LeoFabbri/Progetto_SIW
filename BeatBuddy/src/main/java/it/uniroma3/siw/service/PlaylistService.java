package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Playlist;
import it.uniroma3.siw.model.Song;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.PlaylistRepository;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    public Playlist findById(Long id){
        return this.playlistRepository.findById(id).get();
    }

    public Iterable<Playlist> findAll(){
        return this.playlistRepository.findAll();
    }

    public void save(Playlist Playlist){
        this.playlistRepository.save(Playlist);
    }

    public List<Playlist> findByUSerAndSong(User user, Song song){
        return this.playlistRepository.findByUserAndSong(user, song);
    }
    
}
