package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Song;
import it.uniroma3.siw.repository.ArtistRepository;
import it.uniroma3.siw.repository.SongRepository;
import it.uniroma3.siw.service.SongService;

@RestController
public class SongRestController {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/rest/songs")
    @ResponseBody
    public List<Song> getSongs(Model model){
        Artist singer = this.artistRepository.findById((Long)model.getAttribute("userId")).get();
        List<Song> songs = this.songRepository.findBySinger(singer);
        System.out.println("Fetched songs : "+songs);
        return songs;
    }

}
