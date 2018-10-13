package controlador;

import java.util.Vector;

import dao.CategoriaDAO;
import dao.UsuarioDAO;
import dto.UsuarioDTO;
import excepciones.CategoriaException;
import excepciones.UsuarioException;
import negocio.Usuario;

public class ControladorUsuario {

	private Vector<Usuario> usuarios;

	private static ControladorUsuario instancia;

	public ControladorUsuario() {
		usuarios = new Vector<>();
		usuarios.add(new Usuario("Emiliano", "Emiliano", "pepe"));
		usuarios.add(new Usuario("Debi", "Debi", "pepe"));
		usuarios.add(new Usuario("Lucas", "Lucas", "pepe"));
		usuarios.add(new Usuario("Amilcar", "Amilcar", "pepe"));

	}

	public ControladorUsuario(Vector<Usuario> usuarios) {
		super();
		setUsuarios(usuarios);
	}

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
		Usuario us = UsuarioDAO.getInstancia().buscarUsuarioByApodo(apodo);
		return us;
	}

	public Usuario buscarUsuarioPorEmail(String email) throws UsuarioException {
		for (Usuario usuario : getUsuarios()) {
			if (usuario.getEmail().equals(email)) {
				return usuario;
			}
		}
		throw new UsuarioException("El usuario: " + email + "no existe.");
	}

	public Usuario buscarUsuarioPorId(int id) throws UsuarioException {
		for (Usuario usuario : getUsuarios()) {
			if (usuario.getIdUsuario() == id) {
				return usuario;
			}
		}
		throw new UsuarioException("El usuario: " + id + "no existe.");
	}

	// TODO Agregar a Diagrama.
	public void modificarUsuario(String apodo, String email, String password)
			throws UsuarioException, CategoriaException {
		Usuario usuario = buscarUsuarioPorApodo(apodo);
		usuario.setEmail(email);
		usuario.setPass(password);
	}

	// TODO Agregar a Diagrama. Y Modificar
	public void loggearUsuario(String apodo, String password) throws UsuarioException, CategoriaException {
		Usuario usuario = buscarUsuarioPorApodo(apodo);
		if ((usuario != null) && (usuario.validarLogin(password))) {

			System.out.println("Usuario: " + usuario.getApodo() + " se loggeó.");
		} else {
			System.out.println("Usuario o Contraseña incorrecta para: " + usuario.getApodo());
		}
	}

	public static ControladorUsuario getInstancia() {
		if (instancia == null) {
			instancia = new ControladorUsuario();
		}
		return instancia;
	}
}
