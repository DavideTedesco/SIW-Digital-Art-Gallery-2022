package it.uniroma3.siw.digital_art_gallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
public class DigitalArtGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalArtGalleryApplication.class, args);
	}

}