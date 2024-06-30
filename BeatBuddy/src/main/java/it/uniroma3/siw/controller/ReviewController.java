package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.repository.ReviewRepository;
import it.uniroma3.siw.service.ReviewService;
import jakarta.validation.Valid;

@Controller
public class ReviewController {

    @Autowired ReviewService reviewService;

    
	@Autowired ReviewRepository reviewRepository;
    
    @GetMapping("/formNewReview")
	public String getNewReviewForm(Model model) {
		model.addAttribute("review", new Review());
		return "formNewReview.html";
	}

    @PostMapping("/formNewReview")
	public String newReview(@Valid @ModelAttribute("review") Review review, Model model) {
        this.reviewRepository.save(review); 
		//return "redirect:/song/{id}";
        return "redirect:/songs";
	}

}
