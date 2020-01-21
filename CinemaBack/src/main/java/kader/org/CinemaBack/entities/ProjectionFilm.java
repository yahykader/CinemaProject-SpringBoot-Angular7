package kader.org.CinemaBack.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ProjectionFilm implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProjectionFilm;
	private Date dateProjectionFilm;
	private double prix;
	@ManyToOne
	private Salle salle;
	@ManyToOne
	private Film film;
	@OneToMany(mappedBy = "projectionFilm")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Ticket>tickets;
	@ManyToOne
	private Seance seance;
}
