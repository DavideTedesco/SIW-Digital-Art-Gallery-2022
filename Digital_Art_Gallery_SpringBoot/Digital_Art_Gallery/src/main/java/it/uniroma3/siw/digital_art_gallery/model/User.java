package it.uniroma3.siw.digital_art_gallery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users") // cambiamo nome perchè in postgres user e' una parola riservata
public class User {
    
	public User(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank
    private String nome;
    
    @NotBlank
    private String cognome;
    
   /* public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCognome() {
        return cognome;
    }
    
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }*/
}