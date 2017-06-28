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
import it.uniroma3.Progetto_siw_2017.model.Quadro;
import it.uniroma3.Progetto_siw_2017.service.AutoreService;
import it.uniroma3.Progetto_siw_2017.service.QuadroService;


@RestController
public class QuadroController  {

	@Autowired
	private QuadroService quadroservice; 
	@Autowired
	private AutoreService autoreservice; 

	@GetMapping("/inserimentoQuadro")
	public String showAddForm(Quadro quadro) {
		return "inserimentoQuadro.html";
	}

	@PostMapping("/inserimentoQuadro")
	public String checkQuadroInfo(@Valid @ModelAttribute Quadro quadro, BindingResult bindingResult, Model model, Long idAutore) {
		if (bindingResult.hasErrors()) {
			return "inserimentoQuadro.html";
		}
		else {
			model.addAttribute("titolo", quadro.getTitolo());
			model.addAttribute("anno", quadro.getAnno());
			model.addAttribute("tecnica", quadro.getTecnica());
			model.addAttribute("lunghezza", quadro.getLunghezza());
			model.addAttribute("larghezza", quadro.getLarghezza());
			Autore a = autoreservice.findAutore(idAutore);
			quadro.setAutore(a);
			a.getQuadri().add(quadro);
			model.addAttribute(quadro);
			quadroservice.inserisciQuadro(quadro); 
			model.addAttribute("quadri", quadroservice.getQuadri());
		}
		return "confermaAggiuntaQuadro.html";
	}

	@GetMapping("/eliminaQuadro")
	public String showDeleteForm(Autore autore) {
		return "eliminazioneQuadro.html";
	}

	@PostMapping("/eliminaQuadro")
	public String deleteAutoreInfo(@Valid @ModelAttribute Quadro quadro, BindingResult bindingResult, Model model, Long idAutore) {
		if (bindingResult.hasErrors()) {
			return "eliminazioneQuadro.html";
		}
		else {
			model.addAttribute("titolo", quadro.getTitolo());
			model.addAttribute("anno", quadro.getAnno());
			model.addAttribute("tecnica", quadro.getTecnica());
			model.addAttribute("lunghezza", quadro.getLunghezza());
			model.addAttribute("larghezza", quadro.getLarghezza());
			model.addAttribute("autore", quadro.getAutore());
			Autore a = autoreservice.findAutore(idAutore);
			a.getQuadri().remove(quadro);
			model.addAttribute(quadro);
			quadroservice.eliminaQuadro(quadro); 
			model.addAttribute("quadri", quadroservice.getQuadri());
		}
		return "confermaEliminazioneQuadro.html";
	}

}
