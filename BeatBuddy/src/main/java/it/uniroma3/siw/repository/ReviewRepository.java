package it.uniroma3.siw.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.Song;
import it.uniroma3.siw.model.User;

@Repository
public interface ReviewRepository extends CrudRepository<Review,Long>{

    Review findBySongAndUser(Song song, User user);

    List<Review> findBySong(Song song);

}