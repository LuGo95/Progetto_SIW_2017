package it.uniroma3.Progetto_siw_2017.controller;

import java.security.Principal;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

// controller to access the login page
@RestController
public class LoginController { //implements ErrorController {

	//private static final String PATH = "/error";

	// Login form
	@RequestMapping("/login")
	public String login() {
		return "login";   //menuAmministratore.html?
	}

	// Login form without error
	//@RequestMapping("/login")
	//public String loginNoError(Model model) {
		//model.addAttribute("loginOk", true);
		//return "menuAmministratore.html";
	//}

	// Login form with error
	@RequestMapping("/login?error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
	
	// for 403 access denied page
		@RequestMapping(value = "/403", method = RequestMethod.GET)
		public ModelAndView accesssDenied(Principal user) {
			ModelAndView model = new ModelAndView();
			if (user != null) {
				model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
			} 
			else {
				model.addObject("msg", "You do not have permission to access this page!");
			}
			model.setViewName("403");
			return model;
		}


	//@RequestMapping(value = PATH)
	//public String error() {
	//return "Errore!!! Contattare l' amministratore";
	//}

	//@Override
	//public String getErrorPath() {
	//return PATH;
	//}

	//server.error.whitelabel.enabled=false (in application.properties)

}