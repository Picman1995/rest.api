package py.edu.ucsa.rest.api.core.dao;

import py.edu.ucsa.rest.api.core.entities.Usuario;

public interface UsuarioDao extends GenericDao<Integer, Usuario>{
	Usuario getByUsuario(String usuario);
	void borrarPorUsuario(String usuario);
	Usuario getByEmail(String email);
}
