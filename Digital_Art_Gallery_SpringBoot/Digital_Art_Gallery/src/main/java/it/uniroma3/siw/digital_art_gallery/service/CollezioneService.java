package it.uniroma3.siw.digital_art_gallery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.digital_art_gallery.repository.CollezioneRepository;

@Service
public class CollezioneService {
	
	@Autowired
	CollezioneRepository collezioneRepository;
}
