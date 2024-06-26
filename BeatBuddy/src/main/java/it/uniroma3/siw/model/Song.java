package it.uniroma3.siw.model;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Song {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer duration;
    private LocalDate pubblicationDate;
    private Integer numberOfPlays;
    
    // public String getImagePath() {
    //     return imagePath;
    // }

    // public void setImagePath(String imagePath) {
    //     this.imagePath = imagePath;
    // }

    // private String imagePath;
    
    @Column(length = 1000000000)
    private String base64;

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @ManyToOne
    private Album album;

    @OneToMany
    private List<Review> reviews;

    @ManyToMany
    private List<Artist> producedBy;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Artist> sungBy;

    @ManyToMany
    private List<Artist> writtenBy;

    // public void setImagePath(String imagePath){
    //     this.imagePath = imagePath;
    // }

    // public String getImagePath(){
    //     return this.imagePath;
    // }

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getPubblicationDate() {
        return pubblicationDate;
    }

    public void setPubblicationDate(LocalDate pubblicationDate) {
        this.pubblicationDate = pubblicationDate;
    }

    public Integer getNumberOfPlays() {
        return numberOfPlays;
    }

    public void setNumberOfPlays(Integer numberOfPlays) {
        this.numberOfPlays = numberOfPlays;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Artist> getProducedBy() {
        return producedBy;
    }

    public void setProducedBy(List<Artist> producedBy) {
        this.producedBy = producedBy;
    }

    public List<Artist> getSungBy() {
        return sungBy;
    }

    public void setSungBy(List<Artist> sungBy) {
        this.sungBy = sungBy;
    }

    public List<Artist> getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(List<Artist> writtenBy) {
        this.writtenBy = writtenBy;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((duration == null) ? 0 : duration.hashCode());
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
        Song other = (Song) obj;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (duration == null) {
            if (other.duration != null)
                return false;
        } else if (!duration.equals(other.duration))
            return false;
        return true;
    }
    
    

}
