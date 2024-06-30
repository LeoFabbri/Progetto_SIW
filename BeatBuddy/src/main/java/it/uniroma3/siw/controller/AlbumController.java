package it.uniroma3.siw.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.AlbumValidator;
import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Song;
import it.uniroma3.siw.repository.AlbumRepository;
import it.uniroma3.siw.repository.ArtistRepository;
import it.uniroma3.siw.repository.SongRepository;
import it.uniroma3.siw.service.AlbumService;
import it.uniroma3.siw.service.ArtistService;
import jakarta.validation.Valid;

@Controller
public class AlbumController {

    @Autowired
    private AlbumValidator albumValidator;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ArtistService artistService;

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
    public String newAlbum(@Valid @ModelAttribute("album") Album album, Model model, BindingResult bindingResult) {
        List<Artist> artists = new ArrayList<Artist>();
        List<Song> existingSongs = new ArrayList<Song>();
        artists.add(this.artistRepository.findById((Long)model.getAttribute("userId")).get());
        List<Song> songs = new ArrayList<Song>();
        if(album.getSongs()!=null){
            songs.addAll(album.getSongs());
            for(Song s : songs){
                s.setSingers(artists);
                s.setAlbum(album);
            }
        }
        if(album.getArtistsId()!=null){
            for(String s : album.getArtistsId()){
                this.artistRepository.findById(Long.parseLong(s)).get().getAlbums().add(album);
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
        this.albumValidator.validate(album, bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("album", new Album());
            model.addAttribute("error", "this album already exixsts");
            return "artist/formNewAlbum.html";
        }
        this.albumService.save(album);
        model.addAttribute("album", album);
        return "redirect:/albums/"+album.getId();
    }

    @GetMapping("/artist/formUpdateAlbum")
    public String getUpdateAlbums(Model model){
        model.addAttribute("albums", this.albumService.findAll());
        return "artist/updateAlbums.html";
    }

    @GetMapping("/artist/formUpdateAlbum/{id}")
    public String getFormUpdateAlbum(@PathVariable("id") Long id, Model model){
        model.addAttribute("album", this.albumService.findById(id));
        return "artist/formUpdateAlbum.html";
    }
    

    @GetMapping("/artist/deleteAlbums")
    public String getDeleteAlbums(Model model){
        model.addAttribute("albums", this.albumService.findByArtist(this.artistRepository.findById((Long)model.getAttribute("userId")).get()));
        return "artist/deleteArtistAlbum.html";
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

    @GetMapping("/artist/updateAlbum/removeArtist/{ida}/{idar}")
    public String albumRemoveArtist(@PathVariable("ida") Long ida, @PathVariable("idar") Long idar, Model model){
        Album a = this.albumService.findById(ida);
        Artist ar = this.artistRepository.findById(idar).get();
        ar.getAlbums().remove(a);
        a.getArtists().remove(ar);
        this.artistRepository.save(ar);
        this.albumService.save(a);
        return "redirect:/artist/formUpdateAlbum/"+ida;
    }

    @GetMapping("/artist/updateAlbum/removeSong/{ida}/{ids}")
    public String albumRemoveSong(@PathVariable("ida") Long ida, @PathVariable("ids") Long ids, Model model){
        Album a = this.albumService.findById(ida);
        Song s = this.songRepository.findById(ids).get();
        s.setAlbum(null);
        a.getSongs().remove(s);
        Collections.sort(a.getSongs());
        this.songRepository.save(s);
        this.albumService.save(a);
        return "redirect:/artist/formUpdateAlbum/"+ida;
    }

    @GetMapping("/artist/updateAlbum/addArtists/{id}")
    public String getFormAlbumAddArtists(@PathVariable("id") Long id, Model model){
        model.addAttribute("album", this.albumService.findById(id));
        model.addAttribute("newAlbum", new Album());
        model.addAttribute("artists", this.artistService.findAllExceptId((Long)model.getAttribute("userId")));
        return "artist/formAlbumAddArtists.html";
    }

    @PostMapping("/artist/updateAlbum/addArtists/newAlbum/{id}")
    public String addArtistToAlbum(@ModelAttribute("newAlbum") Album newAlbum, @PathVariable("id") Long id, Model model){
        Album album = this.albumService.findById(id);
        for(String s : newAlbum.getArtistsId()){
            album.getArtists().add(this.artistRepository.findById(Long.parseLong(s)).get());
        }
        this.albumService.save(album);
        return "redirect:/artist/formUpdateAlbum/"+album.getId();
    }

    @GetMapping("/artist/updateAlbum/addSongs/{id}")
    public String getFormAlbumAddSongs(@PathVariable("id") Long id, Model model){
        model.addAttribute("album", this.albumService.findById(id));
        model.addAttribute("newAlbum", new Album());
        model.addAttribute("songs", this.songRepository.findAll());
        return "artist/formAlbumAddSongs.html";
    }

    @PostMapping("/artist/updateAlbum/addSongs/newAlbum/{id}")
    public String addSongToAlbum(@ModelAttribute("newAlbum") Album newAlbum, @PathVariable("id") Long id, Model model){
        Album album = this.albumService.findById(id);
        for(String s : newAlbum.getSongsId()){
            album.getSongs().add(this.songRepository.findById(Long.parseLong(s)).get());
        }
        this.albumService.save(album);
        return "redirect:/artist/formUpdateAlbum/"+album.getId();
    }

    //@Post("/artist/updateAlbum/addArtists/album")


}
