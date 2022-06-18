package it.uniroma3.siw.digital_art_gallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.siw.digital_art_gallery.model.Credentials;
import it.uniroma3.siw.digital_art_gallery.model.User;
import it.uniroma3.siw.digital_art_gallery.service.CredentialsService;
import it.uniroma3.siw.digital_art_gallery.validator.CredentialsValidator;
import it.uniroma3.siw.digital_art_gallery.validator.UserValidator;

@Controller
public class AuthController {
	
	@Autowired
	UserValidator userValidator;
	
	@Autowired
	CredentialsValidator credentialsValidator;
	
	@Autowired
	CredentialsService credentialsService;
	
	@GetMapping("/login")
	public String login(Model model) {
		
		return "login";
	}
	
	@GetMapping("/register")
	public String registrazione(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "register";
	}
	
	@PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,
                 BindingResult userBindingResult,
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {

        // validate user and credentials fields
        this.userValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credentials, credentialsBindingResult);

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            return "login";
        }
        return "register";
    }
	
	@GetMapping("/default")
	public String defaultAfterLogin( RedirectAttributes redirectAttr){
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	redirectAttr.addAttribute("credentials", credentials);
    	User user = credentials.getUser();
    	redirectAttr.addAttribute("user", user);
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "redirect:admin/welcomePageAdmin";
        }
        return "redirect:welcomePage";
	}
	
	@GetMapping("/admin/welcomePageAdmin")
	public String adminWelcomePage(Model model) {
		return "admin/welcomePageAdmin";
		
	}
	
	@GetMapping("/welcomePage")
	public String welcomePage(Model model) {
		return "welcomePage";
		
	}

}
