package br.com.caelum.vraptor.backend.dao;

import java.util.List;

/**
 * @author fidelis.guimaraes
 *
 * @param <T>
 */
public interface GenericDao<T> {

	T load(Integer id);

	T load(Long id);

	void persist(T t);

	void update(T t);

	void saveOrUpdate(T t);

	void delete(T t);

	void refresh(T t);

	List<T> listAll();

}
