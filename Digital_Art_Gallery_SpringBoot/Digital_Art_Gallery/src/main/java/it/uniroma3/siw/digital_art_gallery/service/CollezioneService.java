package it.uniroma3.siw.digital_art_gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.digital_art_gallery.model.Collezione;
import it.uniroma3.siw.digital_art_gallery.repository.CollezioneRepository;

@Service
public class CollezioneService {
	
	@Autowired
	CollezioneRepository collezioneRepository;
	
	public List<Collezione> getAllCollezioni(){
		return (List<Collezione>) collezioneRepository.findAll();
	}
	
}
