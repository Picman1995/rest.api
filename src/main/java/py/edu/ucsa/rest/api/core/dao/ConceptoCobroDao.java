package py.edu.ucsa.rest.api.core.dao;

import py.edu.ucsa.rest.api.core.entities.ConceptoCobro;
import py.edu.ucsa.rest.api.core.entities.Usuario;

public interface ConceptoCobroDao extends GenericDao<Integer, ConceptoCobro>{
	Usuario getByConcepto(String concepto);
}
