package it.uniroma3.siw.digital_art_gallery.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.uniroma3.siw.digital_art_gallery.model.Credentials;
import it.uniroma3.siw.digital_art_gallery.repository.CredentialsRepository;

public class CredentialsService {
	
 	@Autowired
	   protected PasswordEncoder passwordEncoder;

		@Autowired
		protected CredentialsRepository credentialsRepository;
		
		@Transactional
		public Credentials getCredentials(Long id) {
			Optional<Credentials> result = this.credentialsRepository.findById(id);
			return result.orElse(null);
		}

		@Transactional
		public Credentials getCredentials(String username) {
			Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
			return result.orElse(null);
		}
			
	   @Transactional
	   public Credentials saveCredentials(Credentials credentials) {
	       credentials.setRole(Credentials.DEFAULT_ROLE);
		   //credentials.setRole(Credentials.ADMIN_ROLE);
	       credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
	       return this.credentialsRepository.save(credentials);
	    }

}
