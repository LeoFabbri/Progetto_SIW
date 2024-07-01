package it.uniroma3.siw.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import it.uniroma3.siw.controller.validator.SongValidator;
import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.Song;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.ArtistRepository;
import it.uniroma3.siw.repository.PlaylistRepository;
import it.uniroma3.siw.service.AlbumService;
import it.uniroma3.siw.service.ArtistService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.PlaylistService;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.service.SongService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class SongController {

    @Autowired
    private SongValidator songValidator;

    @Autowired
    private SongService songService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private UserService userService;

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private AlbumService albumService;

    @GetMapping("/songs")
    public String getSongs(Model model) {
        model.addAttribute("songs", this.songService.findAll());
        return "songs.html";
    }
    
    @GetMapping("/songs/{id}")
    public String getSong(@PathVariable("id") Long id, Model model) {
        Song song = this.songService.findById(id);
        model.addAttribute("song", song);
        model.addAttribute("recensioni", reviewService.findBySong(song));
        if((Long) model.getAttribute("userId") == null){
            model.addAttribute("role", "ANONIMO");
        }else{
            if(credentialsService.getCredentials(((UserDetails) model.getAttribute("userDetails")).getUsername()).getRole().equals("DEFAULT")){
                User user = userService.findById((Long) model.getAttribute("userId"));
                model.addAttribute("role", "DEFAULT");
    
                // this.userService.findById((Long) model.getAttribute("userId")).getPlaylistsCreated().get(0).getSongs().contains(song);

                Review recensioneUser = reviewService.findBySongAndUser(song, user);
                model.addAttribute("recensioneUser", recensioneUser);
                // model.addAttribute("playlists", this.userService.findById((Long) model.getAttribute("userId")).getPlaylistsCreated());
                model.addAttribute("playlists", this.playlistService.findByUSerAndSong(user, song));
            }
            else{
                model.addAttribute("role", "ARTIST");
            }
        }

        return "song.html";
    }

    @PostMapping("/songs/{id}/recensione")
    public String addRecensione(@PathVariable("id") Long id, @RequestParam int stars, 
                                @RequestParam String comment, @ModelAttribute("userId") Long userId, Model model) {
        Song song = songService.findById(id);
        User user = userService.findById(userId);

        Review recensione = new Review();
        recensione.setUser(user);
        recensione.setSong(song);
        recensione.setComment(comment);
        recensione.setPubblicationDate(LocalDate.now());
        recensione.setStars(stars);
        reviewService.save(recensione);
        song.getReviews().add(recensione);

        return "redirect:/songs/" + id;
    }

    @GetMapping("/artist/songs")
    public String getArtistSongs(Model model){
        model.addAttribute("songs", this.songService.findBySinger(this.artistService.findById((Long)model.getAttribute("userId"))));
        return "artist/songs.html";
    }

    @GetMapping("/artist/formNewSong")
    public String getFormNewSong(Model model) {
        model.addAttribute("song", new Song());
        return "artist/formNewSong.html";
    }

    @GetMapping("/artist/newAlbum/formNewSong")
    public String getAlbumFormNewSong(Model model){
        model.addAttribute("song", new Song());
        return "artist/albumFormNewSong.html";
    }

    @PostMapping("/artist/newSong/song")
    public String newSong(@Valid @ModelAttribute("song") Song song, @RequestParam("image") MultipartFile file, @RequestParam("audio") MultipartFile audio, Model model, BindingResult bindingResult) {
        try{
        List<Artist> singers = new ArrayList<Artist>();
        List<Artist> producers = new ArrayList<Artist>();
        List<Artist> writers = new ArrayList<Artist>();
        singers.add(this.artistService.findById((Long)model.getAttribute("userId")));
        if(song.getSingersId()!=null){
            for(String id : song.getSingersId()){
                this.artistService.findById(Long.parseLong(id)).getSongsSung().add(song);
                singers.add(this.artistService.findById(Long.parseLong(id)));
            }
        }
        if(song.getProducersId()!=null){
            for(String id : song.getProducersId()){
                this.artistService.findById(Long.parseLong(id)).getSongsProduced().add(song);
                producers.add(this.artistService.findById(Long.parseLong(id)));
            }
        }
        if(song.getWritersId()!=null){
            for(String id : song.getWritersId()){
                this.artistService.findById(Long.parseLong(id)).getSongWritten().add(song);
                writers.add(this.artistService.findById(Long.parseLong(id)));
            }
        }
            song.setSingers(singers);
            song.setProducers(producers);
            song.setWriters(writers);
            song.setAlbum(null);
            song.setPubblicationDate(LocalDate.now());
            song.setNumberOfPlays(0);

            byte[] byteFoto = file.getBytes();
            song.setBase64(Base64.getEncoder().encodeToString(byteFoto));
            byte[] byteAudio = audio.getBytes();
            song.setAudioBase64(Base64.getEncoder().encodeToString(byteAudio));

            this.songValidator.validate(song, bindingResult);
            if(bindingResult.hasErrors()){
                model.addAttribute("error","Song already exists");
                model.addAttribute("song", new Song());
                return "artist/formNewSong.html";
            }
            this.songService.save(song);
            return "redirect:/songs/"+song.getId();
        } catch (IOException e) {
            return "artist/formNewSong.html";
        }
    }

    @PostMapping("/artist/newAlbum/newSong/song")
    public String albumFormNewSong(@Valid @ModelAttribute("song") Song song, Model model, BindingResult bindingResult) {
        List<Artist> singers = new ArrayList<Artist>();
        List<Artist> producers = new ArrayList<Artist>();
        List<Artist> writers = new ArrayList<Artist>();
        singers.add(this.artistService.findById((Long)model.getAttribute("userId")));
        if(song.getSingersId()!=null){
            for(String id : song.getSingersId()){
                this.artistService.findById(Long.parseLong(id)).getSongsSung().add(song);
                singers.add(this.artistService.findById(Long.parseLong(id)));
            }
        }
        if(song.getProducersId()!=null){
            for(String id : song.getProducersId()){
                this.artistService.findById(Long.parseLong(id)).getSongsProduced().add(song);
                producers.add(this.artistService.findById(Long.parseLong(id)));
            }
        }
        if(song.getWritersId()!=null){
            for(String id : song.getWritersId()){
                this.artistService.findById(Long.parseLong(id)).getSongWritten().add(song);
                writers.add(this.artistService.findById(Long.parseLong(id)));
            }
        }
        song.setSingers(singers);
        song.setProducers(producers);
        song.setWriters(writers);
        song.setPubblicationDate(LocalDate.now());
        song.setNumberOfPlays(0);
        this.songValidator.validate(song, bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("error","Song already exists");
            model.addAttribute("song", new Song());
            return "artist/albumFormNewSong.html";
        }
        this.songService.save(song);
        return "redirect:/artist/formNewAlbum";
    }
    
    @GetMapping("/artist/deleteSongs")
    public String getDeleteSongs(Model model) {
        model.addAttribute("songs", this.songService.findBySinger(this.artistService.findById((Long)model.getAttribute("userId"))));
        return "artist/deleteArtistSongs.html";
    }

    @PostMapping("/artist/deleteSongs/{id}")
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
