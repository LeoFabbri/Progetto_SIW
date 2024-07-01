package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.service.AlbumService;

@Component
public class AlbumValidator implements Validator{

    @Autowired
    private AlbumService albumService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Album.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Album a = (Album)target;
        if(a.getTitle()!=null && a.getArtists()!=null && existsByTitleAndArtists(a)){
            errors.reject("album.duplicate");
        }
    }

    public boolean existsByTitleAndArtists(Album album){
        int cont = 0;
        for(Artist a : album.getArtists()){
            if(!this.albumService.findByTitleAndArtist(album.getTitle(), a).isEmpty()){
                cont++;
            }
        }
        if(cont<album.getArtists().size()){
            return false;
        }else{
            return true;
        }
    }

}
