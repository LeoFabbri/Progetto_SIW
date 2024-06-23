package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.service.ArtistService;

@RestController
public class ArtistRestController {

    @Autowired
    private ArtistService artistService;

    @GetMapping("/rest/singers")
    @ResponseBody
    public List<Artist> getSingers(Model model){
        List<Artist> artisti = new ArrayList<Artist>();
        for (Artist a : this.artistService.findAllExceptId((Long)model.getAttribute("artistID"))) {
            artisti.add(a);
        }
        return artisti;
    }

    @GetMapping("/rest/artists")
    @ResponseBody
    public List<Artist> getArtists(Model model){
        List<Artist> artisti = new ArrayList<Artist>();
        for (Artist a : this.artistService.findAll()) {
            artisti.add(a);
        }
        return artisti;
    }

}
