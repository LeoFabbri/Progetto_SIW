package it.uniroma3.siw.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private Integer stars;
	private String comment;
    private LocalDate pubblicationDate;

    @ManyToOne
    private User user;

    public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getPubblicationDate() {
        return pubblicationDate;
    }

    public void setPubblicationDate(LocalDate pubblicationDate) {
        this.pubblicationDate = pubblicationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
	@Override
	public boolean equals(Object o) {
		Review r = (Review)o;
		return this.stars.equals(r.getStars()) && this.comment.equals(r.getComment()) && this.pubblicationDate.equals(r.getPubblicationDate());
	}
	
	@Override
	public int hashCode() {
		return this.stars.hashCode() + this.comment.hashCode() + this.pubblicationDate.hashCode();
	}

}
