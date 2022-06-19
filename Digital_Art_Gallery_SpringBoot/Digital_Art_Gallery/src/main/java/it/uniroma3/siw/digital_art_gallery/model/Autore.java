package it.uniroma3.siw.digital_art_gallery.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cognome;
	
	@NotBlank
	private java.time.LocalDate dataDiNascita;
	
	@NotBlank
	private String luogoDiNascita;
	
	@OneToMany(mappedBy = "autore")
	@Cascade({CascadeType.DELETE, CascadeType.PERSIST})
	private List<Opera> opere;
	
	
}
