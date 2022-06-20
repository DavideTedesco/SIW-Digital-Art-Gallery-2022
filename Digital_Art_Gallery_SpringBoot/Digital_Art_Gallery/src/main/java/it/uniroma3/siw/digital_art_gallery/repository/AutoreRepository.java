package it.uniroma3.siw.digital_art_gallery.repository;


import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.digital_art_gallery.model.Autore;

public interface AutoreRepository extends CrudRepository<Autore, Long> {

		boolean existsByNomeAndCognomeAndDataDiNascita(String nome, String Cognome, 
				LocalDate dataDiNascita);
		
		Optional<Autore> findById(Long id);
}
