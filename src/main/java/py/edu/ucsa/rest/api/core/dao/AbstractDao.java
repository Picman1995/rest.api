package py.edu.ucsa.rest.api.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractDao<PK extends Serializable, T> implements GenericDao<PK, T>{
	
	private final Class<T> persistentClass;
	
	protected Logger logger = null;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		this.logger = LoggerFactory.getLogger(persistentClass);
	}
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	public T getById(PK id) {
		return (T)this.entityManager.find(persistentClass, id);
	}
	
	public void persistir(T entity) {
		this.entityManager.persist(entity);
	}
	
	public void actualizar(T entity) {
		this.entityManager.merge(entity);
	}
	
	public void eliminar(T entity) {
		this.entityManager.remove(entity);
	}
	
}
