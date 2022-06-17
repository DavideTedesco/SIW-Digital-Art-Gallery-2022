package it.uniroma3.siw.digital_art_gallery.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.digital_art_gallery.model.Credentials;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {

	Optional<Credentials> findByUsername(String username);
	

}
