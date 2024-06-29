package it.uniroma3.siw.model;

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

@Entity
public class Song {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Integer duration;
    private LocalDate pubblicationDate;
    private Integer numberOfPlays;
    @Column(length = 1000000000)
	private String base64;
    @Column(length = 1000000000)
	private String audioBase64;    

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

    public String getAudioBase64() {
        return audioBase64;
    }

    public void setAudioBase64(String audioBase64) {
        this.audioBase64 = audioBase64;
    }

    public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}
    
    @Override
    public boolean equals(Object o){
        Song song = (Song)o;
        return this.title.equals(song.getTitle()) && this.duration.equals(song.getDuration());
    }

    @Override
    public int hashCode(){
        return this.title.hashCode() + this.duration.hashCode();
    }

}
