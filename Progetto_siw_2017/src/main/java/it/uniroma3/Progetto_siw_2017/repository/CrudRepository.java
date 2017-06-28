package it.uniroma3.Progetto_siw_2017.repository;

import java.util.List;

public interface CrudRepository<T> {

	public T save (T entity);   
	public void delete (T entity);
	public void deleteAll();
	public T findOne (Long id);    
	public List<T> findAll();
	public T update (T entity);

}
