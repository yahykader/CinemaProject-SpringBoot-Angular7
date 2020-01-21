package kader.org.CinemaBack.entities;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;


@Projection(name="p1",types = { ProjectionFilm.class })
public interface ProjectFilmProj{
	
	public Long getIdProjectionFilm();
	public Date getDateProjectionFilm();
	public double getPrix();
	public Salle getSalle();
	public Film getFilm();
	public Seance getSeance();
	public Collection<Ticket> getTickets();
}
