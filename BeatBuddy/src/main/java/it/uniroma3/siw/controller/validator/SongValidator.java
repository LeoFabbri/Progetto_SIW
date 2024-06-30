package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Song;
import it.uniroma3.siw.repository.ArtistRepository;
import it.uniroma3.siw.repository.SongRepository;
import it.uniroma3.siw.service.SongService;

@Component
public class SongValidator implements Validator{
    
    @Autowired
    private SongRepository songRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Song.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Song s = (Song)target;
        if(s.getTitle()!=null && s.getSingers()!=null && existsByTitleAndArtists(s)){
            errors.reject("song already exists");
        }
    }

    public boolean existsByTitleAndArtists(Song song){
        int cont = 0;
        for(Artist a : song.getSingers()){
            if(!this.songRepository.findByTitleAndSinger(song.getTitle(), a).isEmpty()){
                cont++;
            }
        }
        if(cont<song.getSingers().size()){
            return false;
        }else{
            return true;
        }
    }

}
