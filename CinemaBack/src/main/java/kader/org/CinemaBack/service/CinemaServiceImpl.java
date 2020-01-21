package kader.org.CinemaBack.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kader.org.CinemaBack.dao.CategorieRepository;
import kader.org.CinemaBack.dao.CinemaRepository;
import kader.org.CinemaBack.dao.FilmRepository;
import kader.org.CinemaBack.dao.PlaceRepository;
import kader.org.CinemaBack.dao.ProjectionFilmRepository;
import kader.org.CinemaBack.dao.SalleRepository;
import kader.org.CinemaBack.dao.SeanceRepository;
import kader.org.CinemaBack.dao.TicketRepository;
import kader.org.CinemaBack.dao.VilleRepository;
import kader.org.CinemaBack.entities.Categorie;
import kader.org.CinemaBack.entities.Cinema;
import kader.org.CinemaBack.entities.Film;
import kader.org.CinemaBack.entities.Place;
import kader.org.CinemaBack.entities.ProjectionFilm;
import kader.org.CinemaBack.entities.Salle;
import kader.org.CinemaBack.entities.Seance;
import kader.org.CinemaBack.entities.Ticket;
import kader.org.CinemaBack.entities.Ville;

@Service
@Transactional
public class CinemaServiceImpl implements ICinemaService {
   
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private CinemaRepository cinemaRepository;
	@Autowired
	private SalleRepository SalleRepository;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private SeanceRepository seanceRepository; 
	@Autowired
	private CategorieRepository CategorieRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private ProjectionFilmRepository projectionFilmRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Override
	public void initVilles() {
	   Stream.of("Paris","Bordeaux","Lyon","Marseille","Nice").forEach(nameVille->{
		   Ville ville=new Ville();
		   ville.setNameVille(nameVille);
		   villeRepository.save(ville);
	   });
	}

	@Override
	public void initCinemas() {
		villeRepository.findAll().forEach(ville->{
			Stream.of("UGC","PATHE").forEach(nameCinema->{
				Cinema cinema=new Cinema();
				cinema.setNameCinema(nameCinema);
				cinema.setNombresSalles(3+(int)Math.random()*7);
				cinema.setVille(ville);
				cinemaRepository.save(cinema);
			});			
		});	
	}

	@Override
	public void initSalles() {
		cinemaRepository.findAll().forEach(cinema->{
               for (int i = 0; i < cinema.getNombresSalles(); i++) {
				 Salle salle=new Salle();
				 salle.setNameSalle("salle" +(i+1));
				 salle.setCinema(cinema);
				 salle.setNombresPlaces(15+(int)(Math.random()*10));
				 SalleRepository.save(salle);
			}			
		});	
	}

	@Override
	public void initPlaces() {
		SalleRepository.findAll().forEach(salle->{
			for (int i = 0; i < salle.getNombresPlaces(); i++) {
				Place place=new Place();
				place.setNumeroPlace(i+1);
				place.setSalle(salle);
				placeRepository.save(place);
			}
		});
		
	}

	@Override
	public void initSeances() {
		DateFormat dateFormat=new SimpleDateFormat("HH:mm");
		Stream.of("10:00","12:00","14:00","16:00").forEach(s->{
			Seance seance=new Seance();
			try {
				seance.setHeureDebutSeance(dateFormat.parse(s));
				seanceRepository.save(seance);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});	
	}

	@Override
	public void initCategories() {
		Stream.of("Historique","Fiction","Policier","Action").forEach(nameCategorie->{
			Categorie categorie=new Categorie();
			categorie.setNameCategorie(nameCategorie);
			CategorieRepository.save(categorie);
		});	
	}

	@Override
	public void initFlims() {
		List<Categorie> categories=CategorieRepository.findAll();
		double[] durees=new double[] {1,2,1,3,2};
		Stream.of("Parasite","Dragons 3","Le Chant du Loup",
				  "Battle Angel","Toy Story 4","Grâce à Dieu").forEach(nameFilm->{
			Film film=new Film();
			film.setTitreFilm(nameFilm);
			film.setPhotoFilm(nameFilm.replaceAll(" ","")+".jpg");
			film.setCategorie(categories.get(new Random().nextInt(categories.size())));
			film.setDureeFilm(durees[new Random().nextInt(durees.length)]);
			filmRepository.save(film);
		});	
	}

	@Override
	public void initProjectionFilms() {
		double[] prices=new double[] {10,20,40,50,80};
		villeRepository.findAll().forEach(ville->{
			ville.getCinemas().forEach(cinema->{
				cinema.getSalles().forEach(salle->{
					filmRepository.findAll().forEach(film->{
						seanceRepository.findAll().forEach(seance->{
							ProjectionFilm projectionFilm=new ProjectionFilm();
							projectionFilm.setSalle(salle);
							projectionFilm.setFilm(film);
							projectionFilm.setSeance(seance);
							projectionFilm.setDateProjectionFilm(new Date());
							projectionFilm.setPrix(prices[new Random().nextInt(prices.length)]);
							projectionFilmRepository.save(projectionFilm);	
						});
					});
				});
			});
		});	
	}

	@Override
	public void initTickets() {
		projectionFilmRepository.findAll().forEach(p->{
			p.getSalle().getPlaces().forEach(place->{
				Ticket ticket=new Ticket();
				ticket.setPlace(place);
				ticket.setProjectionFilm(p);
				ticket.setReserve(false);
				ticket.setPrix(p.getPrix());
				ticketRepository.save(ticket);
			});
		});
		
	}

}
