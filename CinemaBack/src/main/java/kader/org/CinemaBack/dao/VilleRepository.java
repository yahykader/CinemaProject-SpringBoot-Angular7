package kader.org.CinemaBack.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import kader.org.CinemaBack.entities.Ville;
@RepositoryRestResource
public interface VilleRepository extends JpaRepository<Ville, Long>{

}
