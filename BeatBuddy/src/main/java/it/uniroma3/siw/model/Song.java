package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIdentityInfo(
	generator = ObjectIdGenerators.PropertyGenerator.class,
	property = "id"
)
@Entity
public class Song implements Comparable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotNull
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
    private List<Artist> producers;

    @Transient
    private List<String> producersId;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Artist> singers;

    @Transient
    private List<String> singersId;

    @ManyToMany
    private List<Artist> writers;

    @Transient
    private List<String> writersId;

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

    public List<Artist> getProducers() {
        return producers;
    }

    public void setProducers(List<Artist> producers) {
        this.producers = producers;
    }

    public List<Artist> getSingers() {
        return singers;
    }

    public void setSingers(List<Artist> singers) {
        this.singers = singers;
    }

    public List<Artist> getWriters() {
        return writers;
    }

    public void setWriters(List<Artist> writers) {
        this.writers = writers;
    }

    public List<String> getSingersId(){
        return this.singersId;
    }

    public void setSingersId(List<String> singersId){
        this.singersId = singersId;
    }

    public List<String> getProducersId() {
        return producersId;
    }

    public void setProducersId(List<String> producersId) {
        this.producersId = producersId;
    }

    public List<String> getWritersId() {
        return writersId;
    }

    public void setWritersId(List<String> writersId) {
        this.writersId = writersId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((singers == null) ? 0 : singers.hashCode());
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
        if (singers == null) {
            if (other.singers != null)
                return false;
        } else if (!singers.equals(other.singers))
            return false;
        return true;
    }

    @Override
    public int compareTo(Object o) {
        Song s = (Song)o;
        return this.title.compareTo(s.getTitle());
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
    

}
