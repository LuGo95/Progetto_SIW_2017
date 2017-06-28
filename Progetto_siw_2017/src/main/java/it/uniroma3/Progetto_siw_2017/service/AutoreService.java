package it.uniroma3.Progetto_siw_2017.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.Progetto_siw_2017.model.Autore;
import it.uniroma3.Progetto_siw_2017.repository.AutoreRepository;


@Service
public class AutoreService {

	@Autowired
	private AutoreRepository autoreRepository; 


	@Transactional
	public void inserisciAutore(Autore a) {
		this.autoreRepository.save(a);
	}

	@Transactional
	public void eliminaAutore(Autore a) {
		this.autoreRepository.delete(a);
	}

	public Iterable<Autore> getAutori() {
		Iterable<Autore> autori= new ArrayList<>();
		//TypedQuery<Autore> query = em.createNamedQuery("findAll", Autore.class);
		//autori = query.getResultList();
		autori = this.autoreRepository.findAll();
		return autori;
	}

	public Autore findAutore(long id) {
		return this.autoreRepository.findOne(id);
	}

	//@Transactional
	//public void aggiornaAutore(Autore a) {
	//this.autoreRepository.update(a);
	//}

}
