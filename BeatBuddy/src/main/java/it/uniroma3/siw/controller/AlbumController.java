package it.uniroma3.siw.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Song;
import it.uniroma3.siw.repository.AlbumRepository;
import it.uniroma3.siw.repository.ArtistRepository;
import it.uniroma3.siw.repository.SongRepository;
import it.uniroma3.siw.service.AlbumService;

@Controller
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SongRepository songRepository;

    @GetMapping("/albums/{id}")
    public String getAlbum(@PathVariable("id") Long id, Model model){
        model.addAttribute("album",this.albumService.findById(id));
        return "album.html";
    }

    @GetMapping("/artist/formNewAlbum")
    public String getFormNewAlbum(Model model) {
        model.addAttribute("album", new Album());
        return "artist/formNewAlbum.html";
    }

    @PostMapping("/artist/newAlbum/album")
    public String newAlbum(@ModelAttribute("album") Album album, Model model) {
        List<Artist> artists = new ArrayList<Artist>();
        List<Song> existingSongs = new ArrayList<Song>();
        artists.add(this.artistRepository.findById((Long)model.getAttribute("artistID")).get());
        List<Song> songs = new ArrayList<Song>(album.getSongs());
        for(Song s : songs){
            s.setSingers(artists);
            s.setAlbum(album);
        }
        if(album.getArtistsId()!=null){
            for(String s : album.getArtistsId()){
                artists.add(this.artistRepository.findById(Long.parseLong(s)).get());
            }
        }
        if(album.getSongsId()!=null){
            for(String s : album.getSongsId()){
                Song song = this.songRepository.findById(Long.parseLong(s)).get();
                song.setAlbum(album);
                existingSongs.add(song);
            }
            songs.addAll(existingSongs);
        }
        Collections.sort(songs);
        album.setSongs(songs);
        album.setArtists(artists);
        album.setPubblicationDate(LocalDate.now());
        this.albumService.save(album);
        model.addAttribute("album", album);
        return "redirect:/albums/"+album.getId();
    }

    @GetMapping("/artist/deleteAlbums")
    public String getDeleteAlbums(Model model){
        model.addAttribute("albums", this.albumService.findByArtist(this.artistRepository.findById((Long)model.getAttribute("artistID")).get()));
        return "artist/artistAlbum.html";
    }

    @GetMapping("/artist/deleteAlbums/{id}")
    public String deleteAlbum(@PathVariable("id") Long id, Model model){
        Album a = this.albumService.findById(id);
        if(a.getSongs() != null){
            for(Song s : a.getSongs()){
                s.setAlbum(null);
            }
            a.setSongs(null);
        }
        this.albumService.deleteById(id);
        return "redirect:/artist/deleteAlbums";
    }

}
