package it.uniroma3.Progetto_siw_2017.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.Progetto_siw_2017.model.Quadro;
import it.uniroma3.Progetto_siw_2017.repository.QuadroRepository;

@Service
public class QuadroService {

	@Autowired
	private QuadroRepository quadroRepository; 


	@Transactional
	public void inserisciQuadro(Quadro q) {
		this.quadroRepository.save(q);
	}

	@Transactional
	public void eliminaQuadro(Quadro q) {
		this.quadroRepository.delete(q);
	}

	public Iterable<Quadro> getQuadri() {
		return this.quadroRepository.findAll();
	}

	public Quadro findQuadro(long id) {
		return this.quadroRepository.findOne(id);
	}

	//@Transactional
	//public void aggiornaQuadro(Quadro q) {
	//this.quadroRepository.update(q);
	//}

}
