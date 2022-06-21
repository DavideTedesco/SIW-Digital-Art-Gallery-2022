package it.uniroma3.siw.digital_art_gallery.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static it.uniroma3.siw.digital_art_gallery.constant.PathConstant.IMAGE_DIR;

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
	
	@NotNull
	private LocalDate annoDiRealizzazione;
	
	@NotBlank
	private String descrizione;
	
	@NotBlank
	private String immagine;
	
	@OneToOne//(cascade = CascadeType.PERSIST)
	private Autore autore;
	
	@OneToOne
	private Collezione collezione;
	
	@Transient
	public String getImagePath() {
		if(immagine == null)
			return null;
		
		return IMAGE_DIR + immagine;
	}
	
	
}
