package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.ArtistService;

@Controller
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping("/artists/{id}")
    public String getArtist(@PathVariable("id") Long id, Model model) {
        if((Long) model.getAttribute("userId") == null){
            model.addAttribute("role", "ANONIMO");
        }else{
            model.addAttribute("role", "NON ANONIMO");
        }
        model.addAttribute("artist", this.artistService.findById(id));
        return "artist.html";
    }
    
}
