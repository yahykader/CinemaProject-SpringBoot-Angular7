package kader.org.CinemaBack.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="projectionTickets", types = { Ticket.class})
public interface ProjectionTickets {
	
	public Long getidTicket();
	public String getNameClientTicket();
	public double getPrix();
	public Integer getCodePayment();
	public boolean getReserve();
	public Place getPlace();
	public ProjectionFilm getProjectionFilm();

}
