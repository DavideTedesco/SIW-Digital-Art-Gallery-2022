package it.uniroma3.siw.digital_art_gallery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.digital_art_gallery.model.Opera;

public interface OperaRepository extends CrudRepository<Opera, Long> {
	
	void deleteById(Long id);
	
	@Query(value = "SELECT * FROM opera WHERE collezione_id IS null", nativeQuery = true )
	List<Opera> opereSenzaCollezione();
	
}
