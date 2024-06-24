package it.uniroma3.siw.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Song;
import it.uniroma3.siw.repository.ArtistRepository;
import it.uniroma3.siw.service.SongService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/songs")
    public String getSongs(Model model) {
        model.addAttribute("songs", this.songService.findAll());
        return "songs.html";
    }
    
    @GetMapping("/songs/{id}")
    public String getSong(@PathVariable("id") Long id, Model model) {
        model.addAttribute("song", this.songService.findById(id));
        return "song.html";
    }
    

    @GetMapping("/artist/formNewSong")
    public String getFormNewSong(Model model) {
        model.addAttribute("song", new Song());
        return "artist/formNewSong.html";
    }

    @PostMapping("/artist/newSong/song")
    public String newSong(@ModelAttribute("song") Song song, Model model) {
        List<Artist> singers = new ArrayList<Artist>();
        List<Artist> producers = new ArrayList<Artist>();
        List<Artist> writers = new ArrayList<Artist>();
        singers.add(this.artistRepository.findById((Long)model.getAttribute("artistID")).get());
        if(song.getSingersId()!=null){
            for(String id : song.getSingersId()){
                singers.add(this.artistRepository.findById(Long.parseLong(id)).get());
            }
        }
        if(song.getProducersId()!=null){
            for(String id : song.getProducersId()){
                producers.add(this.artistRepository.findById(Long.parseLong(id)).get());
            }
        }
        if(song.getWritersId()!=null){
            for(String id : song.getWritersId()){
                writers.add(this.artistRepository.findById(Long.parseLong(id)).get());
            }
        }
        song.setSingers(singers);
        song.setProducers(producers);
        song.setWriters(writers);
        song.setPubblicationDate(LocalDate.now());
        song.setNumberOfPlays(0);
        this.songService.save(song);
        model.addAttribute("song", song);
        return "redirect:/songs/"+song.getId();
    }
    
    @GetMapping("/artist/deleteSongs")
    public String getDeleteSongs(Model model) {
        model.addAttribute("songs", this.artistRepository.findById((Long)model.getAttribute("artistID")).get().getSongsSung());
        return "artist/artistSongs.html";
    }

    @GetMapping("/artist/deleteSongs/{id}")
    public String deleteSong(Model model, @PathVariable("id") Long id) {
        Song s = this.songService.findById(id);
        if(s.getAlbum() != null){
            s.getAlbum().getSongs().remove(s);
            Collections.sort(s.getAlbum().getSongs());
            s.setAlbum(null);
        }
        this.songService.deleteById(id);
        return "redirect:/artist/deleteSongs";
    }
    
    

}
