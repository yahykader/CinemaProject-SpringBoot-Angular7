package kader.org.CinemaBack.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Ville implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVille;
	@Column(length = 75)
	private String nameVille;
	private double longitude,latitude,altitude;
	@OneToMany(mappedBy = "ville")
	private Collection<Cinema> cinemas;
}
