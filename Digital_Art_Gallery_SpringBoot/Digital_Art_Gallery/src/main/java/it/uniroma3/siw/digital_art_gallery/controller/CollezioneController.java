package it.uniroma3.siw.digital_art_gallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
