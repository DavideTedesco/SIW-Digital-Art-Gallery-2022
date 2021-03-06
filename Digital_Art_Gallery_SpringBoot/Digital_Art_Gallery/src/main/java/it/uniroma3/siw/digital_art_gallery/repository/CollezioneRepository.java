package it.uniroma3.siw.digital_art_gallery.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.digital_art_gallery.model.Collezione;

public interface CollezioneRepository extends CrudRepository<Collezione, Long> {

	boolean existsByNome(String nome);

}
