package it.uniroma3.siw.digital_art_gallery.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.digital_art_gallery.model.Opera;
import it.uniroma3.siw.digital_art_gallery.repository.OperaRepository;
import it.uniroma3.siw.digital_art_gallery.utility.LocalDateConverter;

@Service
public class OperaService {
	
	@Autowired
	OperaRepository operaRepository;
	
	@Autowired
	LocalDateConverter converter;
	
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
	
	public Opera findOperaById(Long id) {
		return this.operaRepository.findById(id).get();
	}
	
	public List<Opera> opereSenzaCollezione(){
		return this.operaRepository.opereSenzaCollezione();
	}
	
//	@Transactional
//	public Opera save(Opera opera) {
//		opera.getCollezione().getOpere().add(opera);
//		return this.operaRepository.save(opera);
//	}
	
	@Transactional
	public Opera save(Opera opera, String date) {
		//opera.getCollezione().getOpere().add(opera);
		opera.setAnnoDiRealizzazione(this.converter.convert(date));
		return this.operaRepository.save(opera);
	}
	
	public boolean verificaDuplicatiOpera(String nome) {
		return this.operaRepository.existsByNome(nome);
	}
}
