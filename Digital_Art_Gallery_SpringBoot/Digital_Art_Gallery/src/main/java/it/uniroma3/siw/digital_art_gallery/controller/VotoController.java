package it.uniroma3.siw.digital_art_gallery.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.digital_art_gallery.model.Credentials;
import it.uniroma3.siw.digital_art_gallery.model.Opera;
import it.uniroma3.siw.digital_art_gallery.model.User;
import it.uniroma3.siw.digital_art_gallery.model.Voto;
import it.uniroma3.siw.digital_art_gallery.service.CredentialsService;
import it.uniroma3.siw.digital_art_gallery.service.OperaService;
import it.uniroma3.siw.digital_art_gallery.service.VotoService;

@Controller
public class VotoController {
	
	@Autowired
	OperaService operaService;
	
	@Autowired
	VotoService votoService;
	
	@Autowired
	CredentialsService credentialsService;
	
	@GetMapping("/votazione/{id}")
	public String effettuaVotazione(@PathVariable("id") Long id, Model model) {
		
		model.addAttribute("voto", new Voto());
		model.addAttribute("artwork", this.operaService.findOperaById(id));
		return "effettuaVotazione";
	}
	
	@Transactional
	@PostMapping("/votazione/{id}")
	public String effettuandoVotazione(@PathVariable("id") Long id,
										@ModelAttribute("voto") Voto voto,
										Model model) {
		Opera opera = this.operaService.findOperaById(id);
//		opera.getVoti().add(voto);
		voto.setOpera(opera);
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		User user = credentials.getUser();
		voto.setUser(user);
		
		this.votoService.save(voto, opera);
		
//		model.addAttribute("artwork", voto.getOpera());
		model.addAttribute("artworks", this.operaService.getAllOpere());
		return "artworks";
	}
	
	@GetMapping("/editVoto/{id}")
	public String editVoto(@PathVariable("id") Long id, Model model) {
		Voto voto = this.votoService.findVotoBtId(id);
		model.addAttribute("voto", voto);
		model.addAttribute("artwork", voto.getOpera());
		return "editVoto";
	}
	
	@Transactional
	@PostMapping("/editVoto/{id}")
	public String editingVoto(@PathVariable("id") Long id,
								@ModelAttribute("voto") Voto voto, Model model) {
		
		Voto originalVoto = this.votoService.findVotoBtId(id);
		originalVoto.setValutazione(voto.getValutazione());
		originalVoto.setId(id);
		this.votoService.save(originalVoto);
//		model.addAttribute("voto", originalVoto);
//		model.addAttribute("artwork", originalVoto.getOpera());
//		return "artworkDetails";
		model.addAttribute("artworks", this.operaService.getAllOpere());
		return "artworks";
	}
	
	@GetMapping("/removeVoto/{id}")
	public String removeVoto(@PathVariable("id") Long id, Model model) {
		Voto voto = this.votoService.findVotoBtId(id);
		model.addAttribute("voto", voto);
		model.addAttribute("artwork", voto.getOpera());
		return "confirmDeletionVoto";
	}
	
	@Transactional
	@GetMapping("/deletionVoto/{id}")
	public String removingVoto(@PathVariable("id") Long id, Model model) {
		Voto voto = this.votoService.findVotoBtId(id);
//		model.addAttribute("artwork", voto.getOpera());
		this.votoService.deleteVotoById(id, voto);
//		model.addAttribute("voto", null);
		model.addAttribute("artworks", this.operaService.getAllOpere());
		return "artworks";
	}
}
