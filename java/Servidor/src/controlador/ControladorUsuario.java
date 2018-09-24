package controlador;

import java.util.Vector;

import negocio.Usuario;

public class ControladorUsuario {

	private Vector<Usuario> usuarios;

	private static ControladorUsuario instancia;

	public ControladorUsuario() {
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

	public void altaUsuario(String apodo, String email, String password) {
		Usuario usuario = new Usuario(apodo, email, password);
		this.usuarios.add(usuario);
	}

	public Usuario buscarUsuarioPorApodo(String apodo) {
		for (Usuario usuario : getUsuarios()) {
			if (usuario.esUsuario(apodo)) {
				return usuario;
			}
		}
		return null;
	}

	public Usuario buscarUsuarioPorEmail(String email) {
		// TODO
		return null;
	}

	// TODO Agregar a Diagrama.
	public void modificarUsuario(String apodo, String email, String password) {
		Usuario usuario = buscarUsuarioPorApodo(apodo);
		usuario.setEmail(email);
		usuario.setPass(password);
	}

	// TODO Agregar a Diagrama. Y Modificar
	public void loggearUsuario(String apodo, String password) {
		Usuario usuario = buscarUsuarioPorApodo(apodo);
		if (usuario.validarLogin(password)) {
			System.out.println("Usuario: " + usuario.getApodo() + "se loggeó.");
		} else {
			System.out.println("Usuario: " + usuario.getApodo() + "incorrecto.");
		}
	}

	public static ControladorUsuario getInstancia() {
		if (instancia == null) {
			instancia = new ControladorUsuario();
		}
		return instancia;
	}
}
