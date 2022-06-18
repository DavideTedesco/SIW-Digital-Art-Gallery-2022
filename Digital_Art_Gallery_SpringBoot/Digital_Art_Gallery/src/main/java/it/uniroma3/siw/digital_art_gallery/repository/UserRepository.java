package it.uniroma3.siw.digital_art_gallery.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.digital_art_gallery.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
