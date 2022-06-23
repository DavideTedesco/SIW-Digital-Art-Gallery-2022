package it.uniroma3.siw.digital_art_gallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
public class DigitalArtGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalArtGalleryApplication.class, args);
	}

}