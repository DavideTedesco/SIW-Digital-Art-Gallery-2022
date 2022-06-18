package it.uniroma3.siw.digital_art_gallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.digital_art_gallery.service.CollezioneService;

@Controller
public class CollezioneController {
	
	@Autowired
	CollezioneService collezioneService;
	
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
	
	@GetMapping("/admin/removeAuthor/{id}")
	public String removeAutore(@PathVariable("id") Long id, Model model) {
		this.collezioneService.deleteCollectionById(id);
		model.addAttribute("authors", this.collezioneService.getAllCollezioni());
		return "admin/showContentAuthors";
	}
	
	@GetMapping("/admin/confirmAuthorDeletion/{id}")
	public String confirmDeletionAutore(@PathVariable("id") Long id, Model model) {
		model.addAttribute("author", this.collezioneService.findCollezioneById(id));
		return "admin/askConfirmCollectionDeletion";
	}

}
