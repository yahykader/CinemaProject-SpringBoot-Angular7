package kader.org.CinemaBack.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import kader.org.CinemaBack.entities.Film;
@RepositoryRestResource
public interface FilmRepository extends JpaRepository<Film, Long>{

}
