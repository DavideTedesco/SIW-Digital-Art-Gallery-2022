package it.uniroma3.siw.digital_art_gallery.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.digital_art_gallery.model.Voto;
import it.uniroma3.siw.digital_art_gallery.repository.VotoRepository;

@Service
public class VotoService {
	
	@Autowired
	VotoRepository votoRepository;
	
	@Transactional
	public Voto save(Voto voto) {
		return this.votoRepository.save(voto);
	}
	
	@Transactional
	public Voto votoUserOpera(Long userId, Long operaId) {
		
		return this.votoRepository.votoUserOpera(userId, operaId);
	}
	
	public Voto findVotoBtId(Long id) {
		return this.votoRepository.findById(id).get();
	}
	
	@Transactional
	public void deleteVotoById(Long id) {
		this.votoRepository.deleteById(id);
	}
}
