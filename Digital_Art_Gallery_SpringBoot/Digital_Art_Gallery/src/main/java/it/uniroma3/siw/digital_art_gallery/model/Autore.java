package it.uniroma3.siw.digital_art_gallery.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table( uniqueConstraints = {
		@UniqueConstraint( name = "UniqueAutore", columnNames = {"nome", "cognome", "dataDiNascita"})})
public class Autore {
	
	public Autore(List<Opera> lista) {
		this.opere = lista;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cognome;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate dataDiNascita;
	
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@NotNull
//	private LocalDate dataDiMorte;
	
	@NotBlank
	private String luogoDiNascita;
	
	@OneToMany(mappedBy = "autore", cascade = CascadeType.REMOVE)
	private List<Opera> opere;
	
	
}
