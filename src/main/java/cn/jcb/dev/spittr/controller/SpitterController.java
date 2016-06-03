package cn.jcb.dev.spittr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.jcb.dev.spittr.data.SpitterRepository;
import cn.jcb.dev.spittr.domain.Spitter;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
	
	private SpitterRepository spitterRepository;
	
	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		this.spitterRepository = spitterRepository;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new Spitter());
		return "registerForm";
	}
	
	/**
	 * 
	 * @param profilePicture If the user submits the form without selecting a file, then the array will be empty (but not null).
	 * @param unsaveSpitter
	 * @param errors
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegistration(@Valid Spitter unsaveSpitter, Errors errors) 
//	public String processRegistration(@RequestPart("profilePicture") MultipartFile profilePicture, @Valid Spitter unsaveSpitter, Errors errors) 
	{
		if (errors.hasErrors()) {
			return "registerForm";
		}
		
		Spitter spitter = spitterRepository.save(unsaveSpitter);
		return "redirect:/spitter/" + spitter.getUsername();
	}

	@RequestMapping(value="/{username}", method = RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model){
		Spitter spitter = spitterRepository.findByUsername(username);
		if (spitter==null){
			throw new SpitterNotFoundException();
		}
		model.addAttribute(spitter);
		return "profile";		
	}
}
