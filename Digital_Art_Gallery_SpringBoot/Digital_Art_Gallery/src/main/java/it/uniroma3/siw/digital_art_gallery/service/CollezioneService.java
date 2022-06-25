package it.uniroma3.siw.digital_art_gallery.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.digital_art_gallery.model.Collezione;
import it.uniroma3.siw.digital_art_gallery.model.Opera;
import it.uniroma3.siw.digital_art_gallery.repository.CollezioneRepository;

@Service
public class CollezioneService {

	@Autowired
	CollezioneRepository collezioneRepository;

	@Transactional
	public Collezione save(Collezione collezione) {
		if(collezione.getOpere()!=null) {
		for(Opera o : collezione.getOpere()) {
			o.setCollezione(collezione);
		}
		}
		return this.collezioneRepository.save(collezione);
	}

	public List<Collezione> getAllCollezioni() {
		return (List<Collezione>) collezioneRepository.findAll();
	}

	@Transactional
	public void deleteCollectionById(Long id) {
		List<Opera> listaOpera = this.findCollezioneById(id).getOpere();
		for (Opera o : listaOpera) {
			o.setCollezione(null);
		}
		this.collezioneRepository.deleteById(id);
	}

	public Collezione findCollezioneById(Long id) {
		return this.collezioneRepository.findById(id).get();
	}

	public boolean verificaDuplicatiCollezione(String nome) {
		return this.collezioneRepository.existsByNome(nome);
	}

}
