package kader.org.CinemaBack.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
public class Film implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFilm;
    @Column(length = 75)
	private String titreFilm;
	private double dureeFilm;
	@Column(length = 75)
	private String nameRealisateur;
	private String descriptionFilm;
	private String photoFilm;
	private Date dateSortieFilm;
	@ManyToOne
	private Categorie categorie;
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "film")
	private Collection<ProjectionFilm> projectionFilms;
}
