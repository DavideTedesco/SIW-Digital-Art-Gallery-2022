package it.uniroma3.siw.digital_art_gallery.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.digital_art_gallery.model.User;
import it.uniroma3.siw.digital_art_gallery.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	@Transactional
	public List<User> getAllUser(){
		return (List<User>) userRepository.findAll();
	}

}
