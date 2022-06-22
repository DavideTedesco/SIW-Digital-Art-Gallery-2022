package it.uniroma3.siw.digital_art_gallery.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.digital_art_gallery.model.Opera;
import it.uniroma3.siw.digital_art_gallery.service.AutoreService;
import it.uniroma3.siw.digital_art_gallery.service.CollezioneService;
import it.uniroma3.siw.digital_art_gallery.service.OperaService;
import it.uniroma3.siw.digital_art_gallery.utility.FileUploader;
import static it.uniroma3.siw.digital_art_gallery.constant.PathConstant.IMAGE_DIR;

@Controller
public class OperaController {
	
	@Autowired
	OperaService operaService;
	
	@Autowired
	AutoreService autoreService;
	
	@Autowired
	CollezioneService collezioneService;
	
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
	public String removeOpera(@PathVariable("id") Long id, Model model) throws IOException {
		Opera o = this.operaService.findOperaById(id);
		//remove opera from the author
		 o.getAutore().getOpere().remove(o);
		
		//remove the opera for the collection
		o.getCollezione().getOpere().remove(o);
		//remove image from directory
		FileUploader.deleteFile(IMAGE_DIR, o.getImmagine());
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
		model.addAttribute("collections", this.collezioneService.getAllCollezioni());
		return "admin/insertArtwork";
	}
	

	@PostMapping("/admin/insertArtwork")
	public String insertArtworkForAuthor( @ModelAttribute("artwork") Opera opera, 
										@RequestParam("image") MultipartFile multiPartFile,
										@RequestParam("date") String date,Model model) throws IOException {
		
		String imageName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
		opera.setImmagine(imageName);
		Opera savedOpera =this.operaService.save(opera, date);
		
		FileUploader.saveFile(IMAGE_DIR, imageName, multiPartFile);
		
		model.addAttribute("artwork",savedOpera);
		
		return "admin/insertedArtwork";
	}
	
	@GetMapping("/artworkDetails/{id}")
	public String dettagliOpera(@PathVariable("id") Long id, Model model) {
		Opera opera = this.operaService.findOperaById(id);
		model.addAttribute("artwork", opera);
		return "artworkDetails";
	}

}
