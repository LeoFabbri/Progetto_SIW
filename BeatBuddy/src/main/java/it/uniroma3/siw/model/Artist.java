package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@JsonIdentityInfo(
	generator = ObjectIdGenerators.PropertyGenerator.class,
	property = "id"
)
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

	@ManyToMany(mappedBy = "producers")
	private List<Song> songsProduced;

	@ManyToMany(mappedBy = "singers")
	private List<Song> songsSung;

	@ManyToMany(mappedBy = "writers")
	private List<Song> songWritten;

	@ManyToMany(mappedBy = "artists", fetch = FetchType.EAGER)
	private List<Album> albums;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stageName == null) ? 0 : stageName.hashCode());
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
		Artist other = (Artist) obj;
		if (stageName == null) {
			if (other.stageName != null)
				return false;
		} else if (!stageName.equals(other.stageName))
			return false;
		return true;
	}
	
	

}
