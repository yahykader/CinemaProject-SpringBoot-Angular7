package kader.org.CinemaBack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import kader.org.CinemaBack.entities.Film;
import kader.org.CinemaBack.entities.ProjectionFilm;
import kader.org.CinemaBack.entities.Salle;
import kader.org.CinemaBack.entities.Seance;
import kader.org.CinemaBack.service.ICinemaService;

@SpringBootApplication
public class CinemaBackApplication implements CommandLineRunner{
   
	@Autowired
	private ICinemaService cinemaService;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	public static void main(String[] args) {
		SpringApplication.run(CinemaBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Film.class,ProjectionFilm.class,Seance.class,Salle.class);
        cinemaService.initVilles();
        cinemaService.initCinemas();
        cinemaService.initSalles();
        cinemaService.initPlaces();
        cinemaService.initSeances();
        cinemaService.initCategories();
        cinemaService.initFlims();
        cinemaService.initProjectionFilms();
        cinemaService.initTickets();
	}

}
