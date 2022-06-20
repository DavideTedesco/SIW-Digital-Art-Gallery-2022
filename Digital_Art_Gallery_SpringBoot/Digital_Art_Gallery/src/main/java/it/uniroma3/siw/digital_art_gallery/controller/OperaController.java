package it.uniroma3.siw.digital_art_gallery.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.digital_art_gallery.model.Autore;
import it.uniroma3.siw.digital_art_gallery.model.Opera;
import it.uniroma3.siw.digital_art_gallery.service.AutoreService;
import it.uniroma3.siw.digital_art_gallery.service.OperaService;

@Controller
public class OperaController {
	
	@Autowired
	OperaService operaService;
	
	@Autowired
	AutoreService autoreService;
	
	@GetMapping("/artworks")
	public String opere(Model model) {
		model.addAttribute("artworks",this.operaService.getAllOpere());
		return "artworks";
	}
	
	@GetMapping("/admin/showContent/artworks")
	public String adminOpere(Model model) {
		model.addAttribute("artworks",this.operaService.getAllOpere());
		return "/admin/showContentArtworks";
	}
	
	@GetMapping("/admin/removeArtwork/{id}")
	public String removeOpera(@PathVariable("id") Long id, Model model) {
		Opera o = this.operaService.findOperaById(id);
		//remove opera from the author
		 o.getAutore().getOpere().remove(o);
		
		//remove the opera for the collection
		o.getCollezione().getOpere().remove(o);
		this.operaService.deleteArtworkById(id);
		model.addAttribute("artworks", this.operaService.getAllOpere());
		return "admin/showContentArtworks";
	}
	
	@GetMapping("/admin/confirmArtworkDeletion/{id}")
	public String confirmArtworkDeletion(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artwork", this.operaService.getOperaById(id));
		return "admin/askConfirmArtworkDeletion";
	}
	
	@GetMapping("/admin/insertArtwork")
	public String insertArtworkForAuthor(Model model) {
		
		model.addAttribute("artwork", new Opera());
		model.addAttribute("authors", this.autoreService.getAllAutori());
		return "admin/insertArtworksForAuthor";
	}
	

	@PostMapping("/admin/insertArtwork")
	public String insertArtworkForAuthor( @ModelAttribute("artwork") Opera opera, Model model) {
		
		this.operaService.save(opera);
		
		return "admin/showContentArtworks";
	}
	
	@GetMapping("/artworkDetails/{id}")
	public String dettagliOpera(@PathVariable("id") Long id, Model model) {
		Opera opera = this.operaService.findOperaById(id);
		model.addAttribute("artwork", opera);
		return "artworkDetails";
	}

}
