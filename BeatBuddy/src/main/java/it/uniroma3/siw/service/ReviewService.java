package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Review;
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

}
