package it.uniroma3.siw.digital_art_gallery.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.digital_art_gallery.model.Autore;
import it.uniroma3.siw.digital_art_gallery.repository.AutoreRepository;
import it.uniroma3.siw.digital_art_gallery.utility.LocalDateConverter;

@Service
public class AutoreService {
	
	@Autowired
	AutoreRepository autoreRepository;
	
	@Autowired
	LocalDateConverter converter;
	
	public boolean verificaDuplicati(String nome, String Cognome) {
		return this.autoreRepository.existsByNomeAndCognome(nome, Cognome);
	}
	
	public List<Autore> getAllAutori(){
		return (List<Autore>)autoreRepository.findAll();
	}
	
	@Transactional
	public void deleteAuthorById(Long id) {
		this.autoreRepository.deleteById(id);
	}
	
	public Autore findAutoreById(Long id) {
		return this.autoreRepository.findById(id).get();
	}
//	
//	@Transactional
//	public Autore save(Autore autore) {
//		return this.autoreRepository.save(autore);
//	}
//	
	@Transactional
	public Autore save(Autore autore, String date) {
		autore.setDataDiNascita(converter.convert(date));
		return this.autoreRepository.save(autore);
	}
	

}
