package it.uniroma3.siw.digital_art_gallery.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.digital_art_gallery.model.Collezione;
import it.uniroma3.siw.digital_art_gallery.service.CollezioneService;

@Component
public class CollezioneValidator implements Validator {
	
	 final Integer MAX_NOME_LENGTH = 100;
	 final Integer MAX_DESCRIZIONE_LENGTH = 500;
	 final Integer MIN_NOME_LENGTH = 2;
	 final Integer MIN_DESCRIZIONE_LENGTH = 10;
	
	@Autowired
	CollezioneService collezioneService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Collezione.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Collezione collezione = (Collezione)target;
		String nome = collezione.getNome().trim();
		String descrizione = collezione.getDescrizione().trim();
		
		if(nome.isEmpty())
			errors.rejectValue("nome", "required");
		else if(nome.length() < MIN_NOME_LENGTH || nome.length() > MAX_NOME_LENGTH)
			errors.rejectValue("nome", "size");
		
		if(descrizione.isEmpty())
			errors.rejectValue("descrizione", "required");
		else if(descrizione.length() < MIN_NOME_LENGTH || descrizione.length() > MAX_NOME_LENGTH)
			errors.rejectValue("descrizione", "size");
		
		if(nome != null && this.collezioneService.verificaDuplicatiCollezione(nome)) 
			errors.reject("duplicate");
	}
	
	public void validateEdit(Object target, Errors errors) {
		Collezione collezione = (Collezione)target;
		String nome = collezione.getNome().trim();
		String descrizione = collezione.getDescrizione().trim();
		
		if(nome.isEmpty())
			errors.rejectValue("nome", "required");
		else if(nome.length() < MIN_NOME_LENGTH || nome.length() > MAX_NOME_LENGTH)
			errors.rejectValue("nome", "size");
		
		if(descrizione.isEmpty())
			errors.rejectValue("descrizione", "required");
		else if(descrizione.length() < MIN_NOME_LENGTH || descrizione.length() > MAX_NOME_LENGTH)
			errors.rejectValue("descrizione", "size");
		
	}
	
	

}
