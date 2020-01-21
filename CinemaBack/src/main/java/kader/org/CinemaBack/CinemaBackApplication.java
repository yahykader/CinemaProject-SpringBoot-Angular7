package kader.org.CinemaBack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kader.org.CinemaBack.service.ICinemaService;

@SpringBootApplication
public class CinemaBackApplication implements CommandLineRunner{
   
	@Autowired
	private ICinemaService cinemaService;
	
	public static void main(String[] args) {
		SpringApplication.run(CinemaBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        
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
