package it.uniroma3.siw.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.Song;
import it.uniroma3.siw.repository.ReviewRepository;
import it.uniroma3.siw.service.ArtistService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.service.SongService;
import it.uniroma3.siw.service.UserService;

@Controller
public class SongController {
    
    @Autowired
    private SongService songService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private UserService userService;

    @GetMapping("/song/{id}")
    public String getSong(@PathVariable("id") Long id, Model model) {
        Song song = this.songService.findById(id);
        model.addAttribute("song", song);
        //model.addAttribute("reviews", this.reviewService.findAll());
        if(credentialsService.getCredentials((Long) model.getAttribute("userId")).getRole().equals("DEFAULT")){
            User user = userService.findById((Long) model.getAttribute("userId"));
            model.addAttribute("role", "DEFAULT");

            Review recensioneUser = reviewService.findBySongAndUser(song, user);
            model.addAttribute("recensioneUser", recensioneUser);
            model.addAttribute("recensioni", reviewService.findBySong(song));
            model.addAttribute("playlists", this.userService.findById((Long) model.getAttribute("userId")).getPlaylistsCreated());
        }
        else{
            model.addAttribute("role", "ADMIN");
            model.addAttribute("recensioni", reviewService.findBySong(song));
        }

        return "song.html";
    }

    @PostMapping("/song/{id}/recensione")
    public String addRecensione(@PathVariable("id") Long id, @RequestParam int stars, 
                                @RequestParam String comment, @RequestParam LocalDate pubblicationDate, @ModelAttribute("userId") Long userId, Model model) {
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

        return "redirect:/songs";
    }

    @GetMapping("/songs")
    public String showSongs(Model model) {
        model.addAttribute("songs", this.songService.findAll());
        return "songs.html";
    }

    @PostMapping("/searchSongs")
	public String searchSongs(Model model, @RequestParam String title) {
		model.addAttribute("songs", this.songService.findByTitle(title));
		return "foundSongs.html";
	}

}
