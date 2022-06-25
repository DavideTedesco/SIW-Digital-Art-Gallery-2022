package it.uniroma3.siw.digital_art_gallery.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.digital_art_gallery.model.Autore;
import it.uniroma3.siw.digital_art_gallery.service.AutoreService;

@Component
public class AutoreValidator implements Validator {
	
	 final Integer MAX_NOME_LENGTH = 100;
	 final Integer MAX_COGNOME_LENGTH = 100;
	 final Integer MIN_NOME_LENGTH = 2;
	 final Integer MIN_COGNOME_LENGTH = 2;
	 
	 @Autowired
	 AutoreService autoreService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Autore.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		 Autore autore = (Autore) o;
	        String nome = autore.getNome().trim();
	        String cognome = autore.getCognome().trim();

	        String luogoDiNascita = autore.getLuogoDiNascita().trim();

	        if (nome.isEmpty())
	            errors.rejectValue("nome", "required");
	        else if (nome.length() < MIN_NOME_LENGTH || nome.length() > MAX_NOME_LENGTH)
	            errors.rejectValue("nome", "size");

	        if (cognome.isEmpty())
	            errors.rejectValue("cognome", "required");
	        else if (cognome.length() < MIN_COGNOME_LENGTH || cognome.length() > MAX_COGNOME_LENGTH)
	            errors.rejectValue("cognome", "size");
	        
	        if(luogoDiNascita.isEmpty())
	        	errors.rejectValue("luogoDiNascita", "required");
	        
	        if(this.autoreService.verificaDuplicati(nome, cognome)) {
	        	errors.reject("duplicate");
	        }

	}

}
