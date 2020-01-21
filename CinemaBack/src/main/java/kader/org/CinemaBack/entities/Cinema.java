package kader.org.CinemaBack.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Cinema implements Serializable{
  
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCinema;
	@Column(length = 75)
	private String nameCinema;
	private double longitude,latitude,altitude;
	private int nombresSalles;
	@ManyToOne
	private Ville ville;
	@OneToMany(mappedBy = "cinema")
    private Collection<Salle> salles;
}
