package it.uniroma3.siw.digital_art_gallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.digital_art_gallery.service.AutoreService;

@Controller
public class AutoreController {
	
	@Autowired
	AutoreService autoreService;

	@GetMapping("/authors")
	public String autori(Model model) {
		model.addAttribute("authors",autoreService.getAllAutori());
		return "authors";
	}
	
	@GetMapping("/admin/showContent/authors")
	public String adminAutori(Model model) {
		model.addAttribute("authors", this.autoreService.getAllAutori());
		return "admin/showContentAuthors";
	}
}
