package it.uniroma3.siw.digital_art_gallery.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.digital_art_gallery.model.Collezione;
import it.uniroma3.siw.digital_art_gallery.service.CollezioneService;

@Component
public class CollezioneValidator implements Validator {
	
	@Autowired
	CollezioneService collezioneService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Collezione.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Collezione collezione = (Collezione)target;
		if(this.collezioneService.verificaDuplicatiCollezione(collezione.getNome())) 
			errors.reject("collezione.duplicate", "collezione duplicata");
		

	}

}
