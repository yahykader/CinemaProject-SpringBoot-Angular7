package kader.org.CinemaBack.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Salle {
	@Id @GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long idSalle;
	@Column(length = 75)
	private String nameSalle;
	private int nombresPlaces;
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private Cinema cinema;
	
	/*@OneToMany(mappedBy = "ville")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Cinema> cinemas;*/
	
	@OneToMany(mappedBy = "salle")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Place> places;
	
	@OneToMany(mappedBy = "salle")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<ProjectionFilm> projectionFilms;
}
