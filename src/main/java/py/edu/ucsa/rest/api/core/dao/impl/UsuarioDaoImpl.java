package py.edu.ucsa.rest.api.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import py.edu.ucsa.rest.api.core.dao.AbstractDao;
import py.edu.ucsa.rest.api.core.dao.UsuarioDao;
import py.edu.ucsa.rest.api.core.entities.Usuario;

@Repository("usuarioDao")
public class UsuarioDaoImpl extends AbstractDao<Integer, Usuario> implements UsuarioDao {

	@Override
	public Usuario getByUsuario(String usuario) {
		return (Usuario) this.entityManager.createNamedQuery("Usuario.findByUsername")
				.setParameter("usuario", usuario)
				.getSingleResult();
	}

	@Override
	public void borrarPorUsuario(String usuario) {
		this.eliminar(this.getByUsuario(usuario));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarTodos() {
		return this.entityManager.createNamedQuery("Usuario.findAll").getResultList();
	}

	@Override
	public Usuario getByEmail(String email) {
		return (Usuario) this.entityManager.createQuery("SELECT u from Usuario u where u.email = :email")
				.setParameter(1, email)
				.getSingleResult();
	}
}
