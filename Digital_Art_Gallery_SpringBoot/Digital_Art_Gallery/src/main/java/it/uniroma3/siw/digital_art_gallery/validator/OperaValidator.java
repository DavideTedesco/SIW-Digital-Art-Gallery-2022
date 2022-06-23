package it.uniroma3.siw.digital_art_gallery.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.digital_art_gallery.model.Opera;
import it.uniroma3.siw.digital_art_gallery.service.OperaService;

@Component
public class OperaValidator implements Validator  {
	
	@Autowired
	OperaService operaService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Opera.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Opera opera = (Opera)target;
		 if(this.operaService.verificaDuplicatiOpera(opera.getNome()))
			 errors.reject("opera.duplicate", "opera duplicata");
	}

}
