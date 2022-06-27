package it.uniroma3.siw.digital_art_gallery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.digital_art_gallery.model.Autore;
import it.uniroma3.siw.digital_art_gallery.model.Opera;
import it.uniroma3.siw.digital_art_gallery.service.AutoreService;
import it.uniroma3.siw.digital_art_gallery.utility.LocalDateConverter;
import it.uniroma3.siw.digital_art_gallery.validator.AutoreValidator;

@Controller
public class AutoreController {

	@Autowired
	AutoreService autoreService;

	@Autowired
	AutoreValidator autoreValidator;

	@Autowired
	LocalDateConverter converter;

	@GetMapping("/authors")
	public String autori(Model model) {
		model.addAttribute("authors", autoreService.getAllAutori());
		return "authors";
	}

	@GetMapping("/admin/showContent/authors")
	public String adminAutori(Model model) {
		model.addAttribute("authors", this.autoreService.getAllAutori());
		return "admin/showContentAuthors";
	}

	@GetMapping("/admin/removeAuthor/{id}")
	public String removeAutore(@PathVariable("id") Long id, Model model) {
		List<Opera> listaOpere = this.autoreService.findAutoreById(id).getOpere();
		// remove all authors artworks form all collection
		for (Opera o : listaOpere) {
			if (o.getCollezione() != null) {
				o.getCollezione().getOpere().remove(o);
			}
		}
		this.autoreService.deleteAuthorById(id);
		model.addAttribute("authors", this.autoreService.getAllAutori());
		return "admin/showContentAuthors";
	}

	@GetMapping("/admin/confirmAuthorDeletion/{id}")
	public String confirmDeletionAutore(@PathVariable("id") Long id, Model model) {
		model.addAttribute("author", this.autoreService.findAutoreById(id));
		return "admin/askConfirmAuthorDeletion";
	}

	@GetMapping("/admin/insertAuthor")
	public String insertAuthor(Model model) {
		model.addAttribute("author", new Autore());
		return "admin/insertAuthor";
	}

	@PostMapping("/admin/insertAuthor")
	public String insertingAuthor(@ModelAttribute("author") Autore autore, BindingResult autoreBindingResults,
			@RequestParam("date") String date, Model model) {

		this.autoreValidator.validate(autore, autoreBindingResults);
		if (!autoreBindingResults.hasErrors()) {
			this.autoreService.save(autore, date);
			model.addAttribute("authors", this.autoreService.getAllAutori());
			return "admin/showContentAuthors";
		}

		model.addAttribute("author", autore);
		return "admin/insertAuthor";
	}

	@GetMapping("/admin/editAuthor/{id}")
	public String editAuthor(@PathVariable("id") Long id, Model model) {
		Autore autore = this.autoreService.findAutoreById(id);
		model.addAttribute("data", this.converter.revertConversion(autore.getDataDiNascita()));
		model.addAttribute("author", autore);
		return "admin/editAuthor";
	}

	@PostMapping("/admin/editAuthor/{id}")
	public String editingAuthor(@PathVariable("id") Long id, @RequestParam("date") String date,
			@ModelAttribute("author") Autore autore, Model model, BindingResult authorBindingResults) {

		Autore autoreOriginale = this.autoreService.findAutoreById(id);
		autoreOriginale.setNome(autore.getNome());
		autoreOriginale.setCognome(autore.getCognome());
		autoreOriginale.setLuogoDiNascita(autore.getLuogoDiNascita());

		// this.autoreValidator.validate(autoreOriginale, authorBindingResults);

		if (!authorBindingResults.hasErrors()) {
			this.autoreService.save(autoreOriginale, date);
			model.addAttribute("authors", autoreService.getAllAutori());
			return "admin/showContentAuthors";
		}

		model.addAttribute("author", autoreService.findAutoreById(id));
		return "admin/editAuthor";
	}

	@GetMapping("/authorDetails/{id}")
	public String dettagliAutore(@PathVariable("id") Long id, Model model) {
		Autore autore = this.autoreService.findAutoreById(id);
		model.addAttribute("data", this.converter.revertConversion(autore.getDataDiNascita()));
		model.addAttribute("author", autore);
		return "authorDetails";
	}

	@GetMapping("/author/{id}/artworks")
	public String operePerAutore(@PathVariable("id") Long id, Model model) {
		model.addAttribute("author", this.autoreService.findAutoreById(id));
		model.addAttribute("artworks", this.autoreService.findAutoreById(id).getOpere());
		return "artworksOfAuthor";
	}
}
