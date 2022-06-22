package it.uniroma3.siw.digital_art_gallery.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.digital_art_gallery.model.Collezione;
import it.uniroma3.siw.digital_art_gallery.model.Opera;
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

		model.addAttribute("collection", collezione);
		this.collezioneService.save(collezione);
		return "admin/insertedCollection";
	}

	@GetMapping("/admin/editCollection/{id}")
	public String editCollection(@PathVariable("id") Long id, Model model) {

		List<Opera> opere = new ArrayList<>(this.operaService.opereSenzaCollezione());
		opere.addAll(this.collezioneService.findCollezioneById(id).getOpere());
		model.addAttribute("artworks", opere);
		model.addAttribute("collection",this.collezioneService.findCollezioneById(id));

		return "admin/editCollection";
	}

	@PostMapping("/admin/editCollection/{id}")
	public String editingCollection(@ModelAttribute("collection") Collezione collezione,
			@RequestParam("artworkList") List<Opera> opere,
			@PathVariable("id") Long id, Model model) {

		// System.out.println(collezione.getId());
		// System.out.println("\n \n \n \n \n \n \n");
		//
		Collezione originalCollezione = this.collezioneService.findCollezioneById(id);

//		if (!opereNonPresenti.isEmpty()) {
//			operePresenti.addAll(opereNonPresenti);
//		}

		originalCollezione.setId(id);
		originalCollezione.setDescrizione(collezione.getDescrizione());
		originalCollezione.setNome(collezione.getNome());
		for (Opera o : originalCollezione.getOpere()) {
			o.setCollezione(null);
		}
		originalCollezione.setOpere(opere);

		this.collezioneService.save(originalCollezione);

		System.out.println("\n \n \n \n \n \n \n");
		System.out.println(this.collezioneService.getAllCollezioni() + "vuotaa");

		model.addAttribute(this.collezioneService.getAllCollezioni());

		return this.adminCollezioni(model);
	}

	@GetMapping("/collectionDetails/{id}")
	public String dettagliCollezione(@PathVariable("id") Long id, Model model) {
		model.addAttribute("collection", this.collezioneService.findCollezioneById(id));

		return "collectionDetails";
	}

}
