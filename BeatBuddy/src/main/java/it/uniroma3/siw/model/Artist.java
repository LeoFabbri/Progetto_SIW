package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Artist {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String stageName;
    private String realName;
	private LocalDate dataDiNascita;
    private Long monthlyListeners;
    private String biography;
	private String urlImage;

	@ManyToMany(mappedBy = "producedBy")
	private List<Song> songsProduced;

	@ManyToMany(mappedBy = "sungBy")
	private List<Song> songsSung;

	@ManyToMany(mappedBy = "writtenBy")
	private List<Song> songWritten;

	@ManyToMany(mappedBy = "artists", fetch = FetchType.EAGER)
	private List<Album> albums;

	public Artist(){}

	public Artist(String stageName, String urlImage){
		this.stageName=stageName;
		this.urlImage=urlImage;
	}

    public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStageName() {
		return stageName;
	}
	
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

    public String getRealName() {
		return realName;
	}
	
	public void setRealName(String realName) {
		this.realName = realName;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

    public Long getMonthlyListeners() {
        return monthlyListeners;
    }

    public void setMonthlyListeners(Long monthlyListeners) {
        this.monthlyListeners = monthlyListeners;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

	public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

	public List<Song> getSongsProduced() {
		return songsProduced;
	}

	public void setSongsProduced(List<Song> songsProduced) {
		this.songsProduced = songsProduced;
	}

	public List<Song> getSongsSung() {
		return songsSung;
	}

	public void setSongsSung(List<Song> songsSung) {
		this.songsSung = songsSung;
	}

	public List<Song> getSongWritten() {
		return songWritten;
	}

	public void setSongWritten(List<Song> songWritten) {
		this.songWritten = songWritten;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
	@Override
	public boolean equals(Object o) {
		Artist a = (Artist)o;
		return this.stageName.equals(a.getStageName());
	}
	
	@Override
	public int hashCode() {
		return this.stageName.hashCode();
	}

}
