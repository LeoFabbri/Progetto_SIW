package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.siw.model.Playlist;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.PlaylistRepository;
import it.uniroma3.siw.service.PlaylistService;
import it.uniroma3.siw.service.SongService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

@Controller
public class PlaylistController {
    
    @Autowired
    private PlaylistService playlistService;
    
	@Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SongService songService;
    
    @GetMapping("/playlistPage")
    public String getPlaylistPage(Model model){
        return "playlistPage.html";
    }

    @GetMapping("/formNewPlaylist")
	public String getNewPlaylistForm(Model model) {
		model.addAttribute("playlist", new Playlist());
		return "formNewPlaylist.html";
	}

    @PostMapping("/formNewPlaylist")
	public String newReview(@Valid @ModelAttribute("playlist") Playlist playlist, Model model) {
        User user = this.userService.findById((Long) model.getAttribute("userId"));
        playlist.setUser(user);
        user.getPlaylistsCreated().add(playlist);
        this.playlistRepository.save(playlist); 
		//return "redirect:/song/{id}";
        return "redirect:/";
	}

    @GetMapping("/playlist/{id}")
        public String getPlaylist(@PathVariable("id") Long id, Model model) {
        model.addAttribute("playlist", this.playlistService.findById(id));
        model.addAttribute("songs", this.playlistService.findById(id).getSongs());

        //model.addAttribute("reviews", this.reviewService.findAll());
        return "playlist.html";
    }

    @GetMapping("/playlists")
    public String showPlaylists(Model model) {
        model.addAttribute("playlists", this.userService.findById((Long) model.getAttribute("userId")).getPlaylistsCreated());
        return "playlists.html";
    }

    @PostMapping("/addSongToPlaylist/{id}")
    public String addSongToPlaylist(@PathVariable("id") Long id, @ModelAttribute("userId") Long userId, @RequestParam("playlist.id") Long playlistId,  RedirectAttributes redirectAttributes) {
        Playlist playlist = playlistService.findById(playlistId);
        User user = userService.findById(userId);
        
        if (playlist.getSongs().contains(songService.findById(id))) {
            playlist.getSongs().remove(songService.findById(id));
        } else {
            playlist.getSongs().add(songService.findById(id));
        }
        
        playlistService.save(playlist);
        redirectAttributes.addFlashAttribute("userId", userId);
        return "redirect:/song/" + id;
    }

}
