package it.uniroma3.siw.digital_art_gallery.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy")
	private java.time.Year annoDiRealizzazione;
	
	@NotBlank
	private String descrizione;
	
	@NotBlank
	private java.net.URL immagine;
	
	@ManyToMany 
	private List<Autore> autori;
	
	@OneToOne
	private Collezione collezione;
	
}
