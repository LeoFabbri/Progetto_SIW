package it.uniroma3.siw.model;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private LocalDate pubblicationDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Song> songs;

    @Transient
    private List<String> songsId;

    @OneToMany
    private List<Review> reviews;

    @ManyToMany
    private List<Artist> artists;

    @Transient
    private List<String> artistsId;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public LocalDate getPubblicationDate() {
        return pubblicationDate;
    }

    public void setPubblicationDate(LocalDate pubblicationDate) {
        this.pubblicationDate = pubblicationDate;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }    

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<String> getArtistsId() {
        return artistsId;
    }

    public void setArtistsId(List<String> artistsId) {
        this.artistsId = artistsId;
    }

    public List<String> getSongsId() {
        return songsId;
    }

    public void setSongsId(List<String> songsId) {
        this.songsId = songsId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((artists == null) ? 0 : artists.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Album other = (Album) obj;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (artists == null) {
            if (other.artists != null)
                return false;
        } else if (!artists.equals(other.artists))
            return false;
        return true;
    }    
    
}
