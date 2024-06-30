package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Song;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.AlbumService;
import it.uniroma3.siw.service.ArtistService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.SongService;
import it.uniroma3.siw.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class AuthenticationController {

   @Autowired
   private CredentialsService credentialsService;

   @Autowired
   private UserService userService;

   @Autowired
    private AlbumService albumService; 

    @Autowired
    private ArtistService artistService;

    @Autowired
    private SongService songService;

   @GetMapping("/")
    public String getIndex(Model model) {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
            
            Iterable<Album> albums = albumService.findAll(); // Recupera tutti gli album dal servizio
            Iterable<Artist> artists = artistService.findAll(); // Recupera tutti gli artisti dal servizio
            Iterable<Song> songs = songService.findAll();

            model.addAttribute("albums", albums);
            model.addAttribute("artists", artists);
            model.addAttribute("songs", songs);
            
	        return "index.html";
		}else{
            UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
			if (credentials.getRole().equals("ARTIST")) {
				return "artistHome.html";
			}else{
                return "userHome.html";
            }

        }
    }
   
   @GetMapping("/login")
   public String getLoginForm() {
       return "login.html";
   }

   @GetMapping("/registration")
   public String getRegistrationForm(Model model) {
       model.addAttribute("credentials", new Credentials());
       model.addAttribute("user", new User());
       model.addAttribute("artist", new Artist());
       return "registration.html";
   }
   
//    @PostMapping("/registration")
//    public String userRegistration(@ModelAttribute("user") User user, @ModelAttribute("credentials") Credentials credentials, Model model){
//         userService.save(user);
//         credentials.setUser(user);
//         credentialsService.saveCredentials(credentials);
//         return "redirect:/login";
//    }

   @PostMapping("/registration")
   public String userRegistration(@ModelAttribute("user") User user, @ModelAttribute("artist") Artist artist, @ModelAttribute("credentials") Credentials credentials, @RequestParam("image") MultipartFile file, Model model){
        try{

            if(credentials.getRole().equals("DEFAULT")){
                System.out.println("QUA SI USER");
                byte[] byteFoto = file.getBytes();
                user.setBase64(Base64.getEncoder().encodeToString(byteFoto));
                userService.save(user);
                credentials.setUser(user);
            }else{
                System.out.println("QUA SI ARTIST");
                byte[] byteFoto = file.getBytes();
                artist.setBase64(Base64.getEncoder().encodeToString(byteFoto));
                artist.setRealName(user.getName());
                artist.setRealSurname(user.getSurname());
                artist.setDateOfBirth(user.getDateOfBirth());
                artist.setEmail(user.getEmail());
                artistService.save(artist);
                credentials.setArtist(artist);
            }
            credentialsService.saveCredentials(credentials);
            return "redirect:/login";

        }catch (IOException e) {
            return "registration.html";
        }
   }
    
}
