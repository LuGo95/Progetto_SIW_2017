package it.uniroma3.Progetto_siw_2017.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.Progetto_siw_2017.model.Autore;
import it.uniroma3.Progetto_siw_2017.model.Quadro;


public interface QuadroRepository extends CrudRepository<Quadro, Long> {    //alla fine ho esteso la CrudRepo di Spring

	List <Quadro> findByTitolo(String titolo);
	List <Quadro> findByAutore(Autore autore);
	
	//void update(Quadro q);

}