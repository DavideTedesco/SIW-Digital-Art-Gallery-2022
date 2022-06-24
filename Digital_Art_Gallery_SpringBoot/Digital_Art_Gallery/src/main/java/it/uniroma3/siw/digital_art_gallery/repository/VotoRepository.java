package it.uniroma3.siw.digital_art_gallery.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.digital_art_gallery.model.Voto;

public interface VotoRepository extends CrudRepository<Voto, Long> {
	
	@Query(value = "SELECT * FROM voto WHERE user_id = :user_id AND opera_id = :opera_id", nativeQuery = true )
	Voto votoUserOpera(@Param("user_id") Long idu, @Param("opera_id") Long ido );
	
	void deleteById(Long id);
}
