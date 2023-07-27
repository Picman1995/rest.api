package py.edu.ucsa.rest.api.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import py.edu.ucsa.rest.api.core.dao.AbstractDao;
import py.edu.ucsa.rest.api.core.dao.MedioPagoDao;
import py.edu.ucsa.rest.api.core.entities.MedioPago;

@Repository("medioPagoDao")
public class MedioPagoDaoImpl extends AbstractDao<Integer, MedioPago> implements MedioPagoDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<MedioPago> listarTodos() {
		return this.entityManager.createNamedQuery("MedioPago.findAll").getResultList();
	}

	@Override
	public MedioPago getByCodigo(String codigo) {
		return (MedioPago) 
				this.entityManager.createQuery("SELECT mp FROM MedioPago mp where mp.codigo = :cod")
				.setParameter("cod", codigo)
				.getSingleResult();
	}
}
