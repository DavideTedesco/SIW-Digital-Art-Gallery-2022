package it.uniroma3.siw.digital_art_gallery.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.digital_art_gallery.model.Credentials;
import it.uniroma3.siw.digital_art_gallery.model.Opera;
import it.uniroma3.siw.digital_art_gallery.model.User;
import it.uniroma3.siw.digital_art_gallery.model.Voto;
import it.uniroma3.siw.digital_art_gallery.service.AutoreService;
import it.uniroma3.siw.digital_art_gallery.service.CollezioneService;
import it.uniroma3.siw.digital_art_gallery.service.CredentialsService;
import it.uniroma3.siw.digital_art_gallery.service.OperaService;
import it.uniroma3.siw.digital_art_gallery.service.S3BucketService;
import it.uniroma3.siw.digital_art_gallery.service.VotoService;
import it.uniroma3.siw.digital_art_gallery.utility.LocalDateConverter;
import it.uniroma3.siw.digital_art_gallery.validator.OperaValidator;

@Controller
public class OperaController {
	
	@Autowired
	OperaService operaService;
	
	@Autowired
	AutoreService autoreService;
	
	@Autowired
	CollezioneService collezioneService;
	
	@Autowired
	LocalDateConverter converter;
	
	@Autowired
	OperaValidator operaValidator;
	
	@Autowired
	S3BucketService bucketService;
	
	@Autowired
	VotoService votoService;
	
	@Autowired
	CredentialsService credentialsService;
	
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
		if(o.getCollezione() != null) {
			o.getCollezione().getOpere().remove(o);
		}
		//remove image from directory
		//FileUploader.deleteFile(IMAGE_DIR, o.getImmagine());
		this.bucketService.deleteFile(o.getImmagine());
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
	public String insertArtwork(Model model) {
		
		model.addAttribute("artwork", new Opera());
		model.addAttribute("authors", this.autoreService.getAllAutori());

		return "admin/insertArtwork";
	}
	

	@PostMapping("/admin/insertArtwork")
	public String insertingArtwork(@ModelAttribute("artwork") Opera opera, 
										BindingResult operaBindingResult,
										@RequestParam("image") MultipartFile multiPartFile,
										@RequestParam("date") String date,
										Model model) throws IOException {
		this.operaValidator.validate(opera, operaBindingResult);
		if(!operaBindingResult.hasErrors()) {
			String imageName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
			this.bucketService.uploadFile(imageName, multiPartFile);
			opera.setImmagine(imageName);
			Opera savedOpera =this.operaService.save(opera, date);
			
			//FileUploader.saveFile(IMAGE_DIR, imageName, multiPartFile);
			
			model.addAttribute("artwork",savedOpera);
			model.addAttribute("date", date);
			return "admin/insertedArtwork";
		}
		model.addAttribute("authors", this.autoreService.getAllAutori());
		model.addAttribute("artwork", opera);
		return "admin/insertArtwork";
	}
	
	@GetMapping("/admin/editArtwork/{id}")
	public String editArtwork(@PathVariable("id") Long id, Model model) {
		Opera opera = this.operaService.findOperaById(id);
		//System.out.println(this.converter.revertConversion(opera.getAnnoDiRealizzazione()) + "\n\n\n\n\n\n\n\n\n\n");
		model.addAttribute("date", converter.revertConversion(opera.getAnnoDiRealizzazione()));
		model.addAttribute("artwork", opera);
		model.addAttribute("collections",this.collezioneService.getAllCollezioni());
		return "admin/editArtwork";
	}
	
	@PostMapping("/admin/editArtwork/{id}")
	public String editingArtwork(@PathVariable("id") Long id,
									@ModelAttribute("artwork") Opera opera,
									//@RequestParam("image") MultipartFile multiPartFile,
									@RequestParam("date") String date,
									Model model) throws IOException {
		//String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
		Opera originalOpera = this.operaService.findOperaById(id);
		originalOpera.setNome(opera.getNome());
		originalOpera.setDescrizione(opera.getDescrizione());
		originalOpera.setCollezione(opera.getCollezione());
		//originalOpera.setAutore(opera.getAutore());
		//originalOpera.setImmagine(fileName);
		
		//FileUploader.deleteFile(IMAGE_DIR, opera.getImmagine());
		//FileUploader.saveFile(IMAGE_DIR, fileName, multiPartFile);
		
		this.operaService.save(originalOpera, date);
		
		model.addAttribute("artworks", this.operaService.getAllOpere());
		
		
		return "admin/showContentArtworks";
	}
	
	@GetMapping("/artworkDetails/{id}")
	public String dettagliOpera(@PathVariable("id") Long id, Model model) {
		Opera opera = this.operaService.findOperaById(id);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		User user = credentials.getUser();
		Voto voto = this.votoService.votoUserOpera(user.getId(), opera.getId());
		model.addAttribute("voto", voto);
		model.addAttribute("date", converter.revertConversion(opera.getAnnoDiRealizzazione()));
		model.addAttribute("artwork", opera);
		return "artworkDetails";
	}

}
