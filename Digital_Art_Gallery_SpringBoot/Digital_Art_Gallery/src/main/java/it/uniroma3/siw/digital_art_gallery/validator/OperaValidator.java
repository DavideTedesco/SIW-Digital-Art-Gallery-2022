package it.uniroma3.siw.digital_art_gallery.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.digital_art_gallery.model.Opera;
import it.uniroma3.siw.digital_art_gallery.service.OperaService;

@Component
public class OperaValidator implements Validator  {
	
	 final Integer MAX_NOME_LENGTH = 100;
	 final Integer MAX_DESCRIZIONE_LENGTH = 500;
	 final Integer MIN_NOME_LENGTH = 2;
	 final Integer MIN_DESCRIZIONE_LENGTH = 10;
	
	@Autowired
	OperaService operaService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Opera.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Opera opera = (Opera)target;
		String nome = opera.getNome();
		String descrizione = opera.getDescrizione();
		
		if(nome.isEmpty())
			errors.rejectValue("nome", "required");
		else if(nome.length() < MIN_NOME_LENGTH || nome.length() > MAX_NOME_LENGTH)
			errors.rejectValue("nome", "size");
		
		if(descrizione.isEmpty())
			errors.rejectValue("descrizione", "required");
		else if(descrizione.length() < MIN_NOME_LENGTH || descrizione.length() > MAX_NOME_LENGTH)
			errors.rejectValue("descrizione", "size");
		
		 if(this.operaService.verificaDuplicatiOpera(nome))
			 errors.reject("duplicate");
	}

}
