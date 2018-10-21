
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

	public void altaUsuario(String apodo, String password, String email) throws UsuarioException, CategoriaException {
		if (buscarUsuarioPorApodo(apodo) == null) {

			Usuario usuario = new Usuario(apodo, email, password);

			try {
				usuario.save();
			} catch (CategoriaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.usuarios.add(usuario);

		} else {
			throw new UsuarioException("El usuario: " + apodo + " existe.");
		}
	}

	public Usuario buscarUsuarioPorApodo(String apodo) throws UsuarioException, CategoriaException {
		return UsuarioDAO.getInstancia().buscarUsuarioByApodo(apodo);
	}

	public Usuario buscarUsuarioPorEmail(String email) throws UsuarioException {
		for (Usuario usuario : getUsuarios()) {
			if (usuario.getEmail().equals(email)) {
				return usuario;
			}
		}
		throw new UsuarioException("El usuario: " + email + "no existe.");
	}

	public Usuario buscarUsuarioPorId(int id) throws UsuarioException, CategoriaException {
		Usuario us = UsuarioDAO.getInstancia().buscarUsuarioById(id);
		return us;
	}

	// TODO Agregar a Diagrama.
	public void modificarUsuario(String apodo, String email, String password)
			throws UsuarioException, CategoriaException {
		Usuario usuario = buscarUsuarioPorApodo(apodo);
		usuario.setEmail(email);
		usuario.setPass(password);
	}

	// TODO Agregar a Diagrama. Y Modificar
	public UsuarioDTO loggearUsuario(String apodo, String password) throws UsuarioException, CategoriaException {
		Usuario usuario = buscarUsuarioPorApodo(apodo);
		if ((usuario != null) && (usuario.validarLogin(password))) {
			System.out.println("Usuario: " + usuario.getApodo() + " se loggeó.");
			return usuario.toDTO();
		} else {
			System.out.println("Usuario o Contraseña incorrecta para: " + apodo);
		}
		return null;
	}

	public void verificarCategoriaJugador(String apodo) throws UsuarioException, CategoriaException {
		Usuario usuario = buscarUsuarioPorApodo(apodo);
		Categoria siguienteCategoria = CategoriaDAO.getInstancia().buscarCategoriaByNombreNegocio(siguienteCategoria(usuario.getCategoria().getNombre()));
		if (siguienteCategoria.debeSer(usuario)) {
			usuario.actualizarCategoria(siguienteCategoria);
		}
	}

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
