package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
	private String surname;
    private LocalDate dateOfBirth;
    private String email;
    
    @Column(length = 100000000)
    private String base64;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Playlist> playlistsCreated;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Playlist> getPlaylistsCreated() {
        return playlistsCreated;
    }

    public void setPlaylistsCreated(List<Playlist> playlistsCreated) {
        this.playlistsCreated = playlistsCreated;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
	
	@Override
	public boolean equals(Object o) {
		User a = (User)o;
		return this.email.equals(a.getEmail());
	}
	
	@Override
	public int hashCode() {
		return this.email.hashCode();
	}    
}