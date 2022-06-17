package it.uniroma3.siw.digital_art_gallery.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.digital_art_gallery.service.CredentialsService;

public class CollezioneValidator implements Validator {
	
	@Autowired
	CredentialsService credentialsService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

	}

}
