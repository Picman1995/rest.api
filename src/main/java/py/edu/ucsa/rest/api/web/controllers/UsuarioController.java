package py.edu.ucsa.rest.api.web.controllers;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import py.edu.ucsa.rest.api.core.services.UsuarioService;
import py.edu.ucsa.rest.api.web.dto.ErrorDTO;
import py.edu.ucsa.rest.api.web.dto.UsuarioDTO;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	public static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	// ================ RECUPERAMOS TODOS LOS USUARIOS ================ 
	@GetMapping(value = "/")
	public ResponseEntity<?> listarUsuarios() {
		List<UsuarioDTO> usuarios = usuarioService.listarTodos();
		if (usuarios.isEmpty()) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("NO SE ENCONTRARON USUARIOS"), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UsuarioDTO>>(usuarios, HttpStatus.OK);
	}

	// ================ RECUPERAMOS UN USUARIO A PARTIR DE SU ID ================ 
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, 
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getById(@PathVariable("id") long x) {
		UsuarioDTO usu = usuarioService.getById(x);
		if (Objects.isNull(usu)) {
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("NO SE ENCONTRO EL USUARIO CON ID: " + x),
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UsuarioDTO>(usu, HttpStatus.OK);
	}

	// ================ RECUPERAMOS UN USUARIO A PARTIR DE SU USUARIO
	// ================ 
	@RequestMapping(value = "/usuario/{usuario}", method = RequestMethod.GET, 
			produces= MediaType.APPLICATION_JSON_VALUE, 
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getByUsuario(@PathVariable("usuario") String usuario) {
		UsuarioDTO usu = usuarioService.getByUsuario(usuario);
		if (Objects.isNull(usu))
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("NO SE ENCONTRO EL USUARIO: " + usuario),
					HttpStatus.NO_CONTENT);
		return new ResponseEntity<UsuarioDTO>(usu, HttpStatus.OK);
	}

	// ================ INSERTAMOS LOS DATOS DE UN USUARIO ================ 
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crear(@RequestBody UsuarioDTO usu, UriComponentsBuilder uriBuilder) {
		LOGGER.info("Creando el usuario: {}", usu);
		if (usuarioService.isExisteUsuario(usu)) {
			String msg = "Inserción fallida, ya existe un registro con el usuario " + usu.getUsuario();
			LOGGER.error(msg);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO(msg), HttpStatus.CONFLICT);
		}
		usuarioService.crearUsuario(usu);
		LOGGER.info("Inserción exitosa del usuario " + usu);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/usuario/{id}").buildAndExpand(usu.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ================ ACTUALIZAMOS LOS DATOS DE UN USUARIO ================
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizar(@PathVariable("id") long id, @RequestBody UsuarioDTO usu) {
		LOGGER.info("Actualizando el usuario con id {}", id);
		UsuarioDTO usuarioBD = usuarioService.getById(id);
		if (Objects.isNull(usuarioBD)) {
			String msg = "Actualización fallida, no existe el usuario con id {}";
			LOGGER.error(msg, id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO(String.format(msg, id)), HttpStatus.NOT_FOUND);
		}
		usuarioBD.setApellido(usu.getApellido());
		usuarioBD.setDireccion(usu.getDireccion());
		usuarioBD.setEdad(usu.getEdad());
		usuarioBD.setNombre(usu.getNombre());
		usuarioService.actualizarUsuario(usuarioBD);
		return new ResponseEntity<UsuarioDTO>(usuarioBD, HttpStatus.OK);
	}

	// ================ ELIMINAMOS LOS DATOS DE UN USUARIO ================
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminar(@PathVariable("id") long id) {
		LOGGER.info("Obteniendo y eliminando el usuario con id {}", id);
		UsuarioDTO usu = usuarioService.getById(id);
		if (Objects.isNull(usu)) {
			String msg = "Eliminación fallida, no existe el usuario con id {}";
			LOGGER.error(msg, id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO(String.format(msg, id)), HttpStatus.NOT_FOUND);
		}
		usuarioService.eliminarUsuarioById(id);
		return new ResponseEntity<String>("Usuario eliminado exitosamente", HttpStatus.OK);
	}
	
	// ================ ELIMINAMOS TODOS LOS USUARIOS ================
		@RequestMapping(value = "/", method = RequestMethod.DELETE)
		public ResponseEntity<?> eliminar() {
			LOGGER.info("Borrando todos los usuarios");
			usuarioService.eliminarTodos();
			return new ResponseEntity<String>("Todos los usuarios han sido eliminados exitosamente", HttpStatus.OK);
		}
}
