package it.uniroma3.siw.digital_art_gallery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table( uniqueConstraints = {
		@UniqueConstraint( name = "UniqueOpera", columnNames = {"nome", "annoDiRealizzazione"})})
public class Opera {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private java.time.LocalDate annoDiRealizzazione;
	
	@NotBlank
	private String descrizione;
	
	@NotBlank
	private java.net.URL immagine;
	
	@OneToOne 
	private Autore autore;
	
	@OneToOne
	private Collezione collezione;
	
}
