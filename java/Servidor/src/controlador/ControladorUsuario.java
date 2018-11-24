
package controlador;

import java.util.Vector;

import dao.CategoriaDAO;
import dao.UsuarioDAO;
import dto.UsuarioDTO;
import excepciones.CategoriaException;
import excepciones.UsuarioException;
import negocio.Categoria;
import negocio.Usuario;

public class ControladorUsuario {

	private Vector<Usuario> usuarios = new Vector<>();

	private static ControladorUsuario instancia;

	public Vector<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Vector<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void altaUsuario(String apodo, String email, String password) throws UsuarioException, CategoriaException {
		int error = 0;
		String campoEnUso = null;
		Usuario usuario = null;
		try {
			buscarUsuarioPorApodo(apodo);
			error = 1;
			campoEnUso = "apodo";
		} catch (UsuarioException e) {
			try {
				buscarUsuarioPorEmail(email);
				error = 2;
				campoEnUso = "email";
			} catch (UsuarioException e2) {
				usuario = new Usuario(apodo, email, password);
				usuario.save();
			}
		}
		if (usuario == null) {
			throw new UsuarioException(String.format("El %s: %s ya está en uso.", campoEnUso, error == 1 ? apodo : email), error);
		}
	}

	public Usuario buscarUsuarioPorApodo(String apodo) throws UsuarioException, CategoriaException {
		return UsuarioDAO.getInstancia().buscarUsuarioByApodo(apodo);
	}

	public Usuario buscarUsuarioPorEmail(String email) throws UsuarioException, CategoriaException {
		return UsuarioDAO.getInstancia().buscarUsuarioByEmail(email);
	}

	public Usuario buscarUsuarioPorId(int id) throws UsuarioException, CategoriaException {
		Usuario us = UsuarioDAO.getInstancia().buscarUsuarioById(id);
		return us;
	}

	// REVISAR
	public void modificarUsuario(String apodo, String password, String nuevoEmail, String nuevaPass, String nuevoApodo)
			throws UsuarioException, CategoriaException {

		// busco el usuario que se quiere modificar por apodo y password
		Usuario usuario = buscarUsuarioPorApodo(apodo);
		if ((usuario != null) && (usuario.validarLogin(password))) {

			if (nuevoEmail != null)
				usuario.setEmail(nuevoEmail);

			if (nuevaPass != null)
				usuario.setPass(nuevaPass);

			if (nuevoApodo != null)
				usuario.setApodo(nuevoApodo);

			UsuarioDAO.getInstancia().acutualizarUsuario(usuario);

		} else {
			System.out.println("Usuario o Contraseña incorrecta para: " + apodo);
		}

	}

	// REVISAR
	public UsuarioDTO loggearUsuario(String apodo, String password) throws UsuarioException, CategoriaException {
		Usuario usuario = buscarUsuarioPorApodo(apodo);
		if ((usuario != null) && (usuario.validarLogin(password))) {
			System.out.println("Usuario: " + usuario.getApodo() + " se loggeó.");
			return usuario.toDTO();
		} else {
			System.out.println("Usuario o Contraseña incorrecta para: " + apodo);
		}
		throw new UsuarioException("Usuario no encontrado: " + apodo);
	}
	
	// REVISAR. CREO QUE NO HACE FALTA.
	public void verificarCategoriaJugador(String apodo) throws UsuarioException, CategoriaException {
		Usuario usuario = buscarUsuarioPorApodo(apodo);
		Categoria siguienteCategoria = CategoriaDAO.getInstancia()
				.buscarCategoriaByNombreNegocio(siguienteCategoria(usuario.getCategoria().getNombre()));
		if (siguienteCategoria.debeSer(usuario)) {
			usuario.actualizarCategoria(siguienteCategoria);
		}
	}
	
	// REVISAR. CREO QUE NO HACE FALTA.
	private String siguienteCategoria(String nombreActual) {
		switch (nombreActual) {
		case "NOVATO":
			return "CALIFICADO";
		case "CALIFICADO":
			return "EXPERTO";
		case "EXPERTO":
			return "MASTER";
		default:
			return "MASTER";
		}
	}

	public static ControladorUsuario getInstancia() {
		if (instancia == null) {
			instancia = new ControladorUsuario();
		}
		return instancia;
	}
}
