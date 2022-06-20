package it.uniroma3.siw.digital_art_gallery.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.digital_art_gallery.model.Collezione;
import it.uniroma3.siw.digital_art_gallery.service.CollezioneService;
import it.uniroma3.siw.digital_art_gallery.service.OperaService;

@Controller
public class CollezioneController {
	
	@Autowired
	CollezioneService collezioneService;
	
	@Autowired
	OperaService operaService;
	
	@GetMapping("/collections")
	public String collezioni(Model model) {
		model.addAttribute("collections", this.collezioneService.getAllCollezioni());
		return "collections";
	}
	
	@GetMapping("/admin/showContent/collections")
	public String adminCollezioni(Model model) {
		model.addAttribute("collections", this.collezioneService.getAllCollezioni());
		return "admin/showContentCollections";
	}
	
	@GetMapping("/admin/removeCollection/{id}")
	public String removeAutore(@PathVariable("id") Long id, Model model) {
		this.collezioneService.deleteCollectionById(id);
		model.addAttribute("authors", this.collezioneService.getAllCollezioni());
		return "admin/showContentCollections";
	}
	
	@GetMapping("/admin/confirmCollectionDeletion/{id}")
	public String confirmDeletionAutore(@PathVariable("id") Long id, Model model) {
		model.addAttribute("collection", this.collezioneService.findCollezioneById(id));
		return "admin/askConfirmCollectionDeletion";
	}
	
	@GetMapping("/admin/insertCollection")
	public String inserisciCollezione(Model model) {
		model.addAttribute("collection", new Collezione());
		model.addAttribute("artworks", this.operaService.opereSenzaCollezione());
		return "admin/insertCollection";
	}
	
	@PostMapping("/admin/insertCollection")
	public String inserimentoCollezione(@ModelAttribute("collection") Collezione collezione, Model model) {
		if(collezione.getOpere() == null)
			System.out.println("tutto vuoto \n\n\n\n\n\n\n\n\n\n\n\n");
		
		model.addAttribute("collection" , collezione);
		this.collezioneService.save(collezione);
		return "admin/insertedCollection";
	}
	
	@GetMapping("/collectionDetails/{id}")
	public String dettagliCollezione(@PathVariable("id") Long id, Model model) {
		model.addAttribute("collection", this.collezioneService.findCollezioneById(id));
		
		return "collectionDetails";
	}

}
