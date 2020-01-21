package kader.org.CinemaBack.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kader.org.CinemaBack.dao.FilmRepository;
import kader.org.CinemaBack.dao.TicketRepository;
import kader.org.CinemaBack.entities.Film;
import kader.org.CinemaBack.entities.Ticket;
import lombok.Data;

@RestController
public class FilmController {
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private TicketRepository ticketRepository;
	
	@GetMapping(value="/allFilms")
	public List<Film> allFilms(){
		return filmRepository.findAll();
	}
	
	@GetMapping(value="/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] images(@PathVariable Long id) throws Exception {
		Film film=filmRepository.findById(id).get();
		String photoName=film.getPhotoFilm();
		File file = new File(System.getProperty("user.home")+"/cinema/images/"+photoName);
		Path path = Paths.get(file.toURI());
		return Files.readAllBytes(path);		
	}
	
	@PostMapping("/payerTicket")
	@Transactional
	public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
		List<Ticket> listTicket=new ArrayList<>();
		ticketForm.getTickets().forEach(idTicket->{
			Ticket ticket=ticketRepository.findById(idTicket).get();
			System.out.println(idTicket);
			ticket.setNameClientTicket(ticketForm.getNameClientTicket());
			ticket.setReserve(true);
			ticket.setCodePayment(ticketForm.getCodePayment());
			ticketRepository.save(ticket);
			listTicket.add(ticket);
		});
		return listTicket;	
	}
}

@Data
class TicketForm{
	private String nameClientTicket;
	private int codePayment;
	private List<Long>tickets=new ArrayList<>();
}