package it.uniroma3.siw.digital_art_gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.digital_art_gallery.model.Opera;
import it.uniroma3.siw.digital_art_gallery.repository.OperaRepository;

@Service
public class OperaService {
	
	@Autowired
	OperaRepository operaRepository;
	
	public List<Opera> getAllOpere(){
		return (List<Opera>) operaRepository.findAll();
	}
}
