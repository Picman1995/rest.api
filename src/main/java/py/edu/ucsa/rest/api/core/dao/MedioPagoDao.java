package py.edu.ucsa.rest.api.core.dao;

import py.edu.ucsa.rest.api.core.entities.MedioPago;

public interface MedioPagoDao extends GenericDao<Integer, MedioPago>{
	MedioPago getByCodigo(String codigo);
}
