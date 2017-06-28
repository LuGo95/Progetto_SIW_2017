package it.uniroma3.Progetto_siw_2017.repository;

import java.util.List;

import it.uniroma3.Progetto_siw_2017.model.Autore;

import org.springframework.data.repository.CrudRepository;


public interface AutoreRepository extends CrudRepository<Autore,Long> {   //alla fine ho esteso la CrudRepo di Spring

	List<Autore> findByNome(String Nome);
	List<Autore> findByCognome(String cognome);

	//void update(Autore a);

}