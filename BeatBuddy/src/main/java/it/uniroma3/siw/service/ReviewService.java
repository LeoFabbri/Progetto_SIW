package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.Song;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.ReviewRepository;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;

    public Review findById(Long id){
        return this.reviewRepository.findById(id).get();
    }

    public Iterable<Review> findAll(){
        return this.reviewRepository.findAll();
    }

    public void save(Review Review){
        this.reviewRepository.save(Review);
    }

    public Review findBySongAndUser(Song song, User user){
        return this.reviewRepository.findBySongAndUser(song, user);
    }

    public List<Review> findBySong(Song song){
        return this.reviewRepository.findBySong(song);
    }

}
