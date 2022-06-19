package it.uniroma3.siw.digital_art_gallery.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.digital_art_gallery.model.Autore;
import it.uniroma3.siw.digital_art_gallery.model.Opera;
import it.uniroma3.siw.digital_art_gallery.service.OperaService;

@Controller
public class OperaController {
	
	@Autowired
	OperaService operaService;
	
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
	
	@GetMapping("/artworkDetails/{id}")
	public String dettagliOpera(@PathVariable("id") Long id, Model model) {
		Opera opera = this.operaService.findOperaById(id);
		model.addAttribute("artwork", opera);
		model.addAttribute("author", opera.getAutore());
		return "artworkDetails";
	}

}
