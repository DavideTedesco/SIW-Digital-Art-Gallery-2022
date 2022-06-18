package it.uniroma3.siw.digital_art_gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.digital_art_gallery.model.Autore;
import it.uniroma3.siw.digital_art_gallery.repository.AutoreRepository;

@Service
public class AutoreService {
	
	@Autowired
	AutoreRepository autoreRepository;
	
	public boolean verificaDuplicati(String nome, String Cognome, java.time.LocalDate dataDiNascita) {
		return this.autoreRepository.existsByNomeAndCognomeAndDataDiNascita(nome, Cognome, dataDiNascita);
	}
	
	public List<Autore> getAllAutori(){
		return (List<Autore>)autoreRepository.findAll();
	}

}
