package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Song;
import it.uniroma3.siw.service.AlbumService;
import it.uniroma3.siw.service.ArtistService;
import it.uniroma3.siw.service.SongService;

@Controller
public class HomeController {

    @Autowired
    private ArtistService artistService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private SongService songService;

    @PostMapping("/searchAll")
    public String searchAll(Model model, @RequestParam String name) {
        String searchQuery = name.toLowerCase();
        List<Artist> artists = this.artistService.findByStageNameContainingIgnoreCase(searchQuery);
        List<Album> albums = this.albumService.findByTitleContainingIgnoreCase(searchQuery);
        List<Song> songs = this.songService.findByTitleContainingIgnoreCase(searchQuery);

        model.addAttribute("artists", artists);
        model.addAttribute("albums", albums);
        model.addAttribute("songs", songs);

        return "paginaRicerca.html";
    }
    
}
