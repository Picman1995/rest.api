package py.edu.ucsa.rest.api.core.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<PK extends Serializable, T> {
	T getById(PK id);
	
	void persistir(T entity);
	
	void actualizar(T entity);
	
	void eliminar(T entity);
	
	List<T> listarTodos();
}
