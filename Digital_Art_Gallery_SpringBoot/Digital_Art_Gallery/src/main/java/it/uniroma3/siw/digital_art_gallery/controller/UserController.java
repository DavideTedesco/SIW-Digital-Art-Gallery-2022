package it.uniroma3.siw.digital_art_gallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.digital_art_gallery.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/admin/showContent/users")
	public String users(Model model) {
		
		model.addAttribute("users", this.userService.getAllUser());
		
		return "admin/showContentUsers";
	}
	
	@GetMapping("/removeUser")
	public String removeUser(Model model) {
		//TODO
		return "removedUser";
	}
	
	@PostMapping("removeUser")
	public String removingUser(Model model) {
		//TODO
		return "removedUser";
	}
}
