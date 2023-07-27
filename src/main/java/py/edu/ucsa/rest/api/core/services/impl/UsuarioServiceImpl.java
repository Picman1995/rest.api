package py.edu.ucsa.rest.api.core.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import py.edu.ucsa.rest.api.core.services.UsuarioService;
import py.edu.ucsa.rest.api.web.dto.UsuarioDTO;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	private static final AtomicLong counter = new AtomicLong();
	private static List<UsuarioDTO> usuarios = null;
	
	static {
		usuarios = cargarUsuariosEnMemoria();
	}
	private static List<UsuarioDTO> cargarUsuariosEnMemoria() {
		List<UsuarioDTO> usuarios = new ArrayList<>();
		usuarios.add(new UsuarioDTO(counter.incrementAndGet(), 
				"JUAN", "SANCHEZ", "ASUNCION 234", 35, "juan", "123"));
		usuarios.add(new UsuarioDTO(counter.incrementAndGet(), 
				"ANDY", "SLACHEVSKY", "PITIANTUTA 203", 40, "andy", "andy*"));
		usuarios.add(new UsuarioDTO(counter.incrementAndGet(), 
				"JOSE", "CUBILLA", "ROQUE ALONSO 456", 38, "jose", "bubilka"));
		return usuarios;
	}
	
	@Override
	public UsuarioDTO getById(long id) {
		for (UsuarioDTO u : usuarios) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}


	@Override
	public UsuarioDTO getByUsuario(String usuario) {
		for (UsuarioDTO u : usuarios) {
			if (u.getUsuario().equalsIgnoreCase(usuario)) return u;
		}
		return null;
	}

	@Override
	public void crearUsuario(UsuarioDTO usu) {
		usu.setId(counter.incrementAndGet());
		usuarios.add(usu);
	}

	@Override
	public void actualizarUsuario(UsuarioDTO usu) {
		int indice = usuarios.indexOf(usu);
		usuarios.set(indice, usu);
	}

	@Override
	public void eliminarUsuarioById(long id) {
		for (Iterator<UsuarioDTO> iterator = usuarios.iterator(); iterator.hasNext();) {
			UsuarioDTO usu = iterator.next();
			if (usu.getId() == id) {
				iterator.remove();
				break;
			}
		}
	}

	@Override
	public List<UsuarioDTO> listarTodos() {
		return usuarios;
	}

	@Override
	public void eliminarTodos() {
		usuarios.clear();
	}

	@Override
	public boolean isExisteUsuario(UsuarioDTO user) {
		return !Objects.isNull(getByUsuario(user.getUsuario()));
 	}

}
