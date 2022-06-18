package it.uniroma3.siw.digital_art_gallery.service;

import java.util.List;

import javax.transaction.Transactional;

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
	
	@Transactional
	public void deleteArtworkById(Long id) {
		this.operaRepository.deleteById(id);
	}
	
	public Opera getOperaById(Long id) {
		return this.operaRepository.findById(id).get();
	}
}
