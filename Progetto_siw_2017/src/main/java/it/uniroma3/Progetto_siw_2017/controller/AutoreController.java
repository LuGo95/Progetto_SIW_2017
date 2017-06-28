package it.uniroma3.Progetto_siw_2017.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.Progetto_siw_2017.model.Autore;
import it.uniroma3.Progetto_siw_2017.service.AutoreService;


@RestController
public class AutoreController  {

	@Autowired
	private AutoreService autoreservice; 

	@GetMapping("/inserimentoAutore")
	public String showAddForm(Autore autore) {
		return "inserimentoAutore.html";
	}

	@PostMapping("/inserimentoAutore")
	public String checkAutoreInfo(@Valid @ModelAttribute Autore autore, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "inserimentoAutore.html";
		}
		else {
			model.addAttribute("nome", autore.getNome());
			model.addAttribute("cognome", autore.getCognome());
			model.addAttribute("nazionalita", autore.getNazionalita());
			model.addAttribute("data_nascita", autore.getData_nascita());
			model.addAttribute("data_morte", autore.getData_morte());
			model.addAttribute("quadri", autore.getQuadri());
			model.addAttribute(autore);
			autoreservice.inserisciAutore(autore); 
			model.addAttribute("autori", autoreservice.getAutori());
		}
		return "confermaAggiuntaAutore.html";
	}

	@GetMapping("/eliminaAutore")
	public String showDeleteForm(Autore autore) {
		return "eliminazioneAutore.html";
	}

	@PostMapping("/eliminaAutore")
	public String deleteAutoreInfo(@Valid @ModelAttribute Autore autore, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "eliminazioneAutore.html";
		}
		else {
			model.addAttribute("nome", autore.getNome());
			model.addAttribute("cognome", autore.getCognome());
			model.addAttribute("nazionalita", autore.getNazionalita());
			model.addAttribute("data_nascita", autore.getData_nascita());
			model.addAttribute("data_morte", autore.getData_morte());
			model.addAttribute("quadri", autore.getQuadri());
			model.addAttribute(autore);
			autoreservice.eliminaAutore(autore);
			model.addAttribute("autori", autoreservice.getAutori());
		}
		return "confermaEliminazioneAutore.html";
	}

}
