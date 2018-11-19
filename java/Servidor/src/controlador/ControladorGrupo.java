package controlador;

import java.util.Vector;

import dao.GrupoDAO;
import dao.MiembroDAO;
import dto.GrupoDTO;
import dto.UsuarioDTO;
import excepciones.CategoriaException;
import excepciones.GrupoException;
import excepciones.MiembroException;
import excepciones.UsuarioException;
import negocio.Grupo;
import negocio.Miembro;
import negocio.Usuario;

// TODO Hay nuevos metodos. Agregar a Diagrama.
public class ControladorGrupo {

	private Vector<Grupo> grupos;

	private static ControladorGrupo instancia;

	public ControladorGrupo() {
		grupos = new Vector<>();
	}

	public ControladorGrupo(Vector<Grupo> grupos) {
		setGrupos(grupos);
	}

	public Vector<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(Vector<Grupo> grupos) {
		this.grupos = grupos;
	}

	// FIXME Por qué String nombre si el grupo tiene un id?
	public Grupo buscarGrupo(String nombre) throws GrupoException, CategoriaException, UsuarioException {
		Grupo g = GrupoDAO.getInstancia().buscarGrupoByNombre(nombre);
		
		return g;
	}
	
	public void agregarUsuarioAGrupo (UsuarioDTO user, GrupoDTO grupo) throws CategoriaException, MiembroException, UsuarioException, GrupoException {
		
		Grupo gru = GrupoDAO.getInstancia().buscarGrupoById(grupo.getIdGrupo());
		Usuario usuario = ControladorUsuario.getInstancia().buscarUsuarioPorId(user.getIdUsuario());
		gru.agregarParticipante(usuario);			
		
	}
		
	public Miembro buscarMiembro(UsuarioDTO u, GrupoDTO g) throws GrupoException, CategoriaException, UsuarioException, MiembroException {
		
		Grupo grupo = GrupoDAO.getInstancia().buscarGrupoById(g.getIdGrupo());
		Usuario usuario = ControladorUsuario.getInstancia().buscarUsuarioPorId(u.getIdUsuario());
		Miembro miembro = grupo.buscarMiembro(usuario);
		return miembro;		
		
	}

	public void altaGrupo(String nombre, UsuarioDTO administrador) throws UsuarioException, GrupoException, CategoriaException {
		Grupo g = GrupoDAO.getInstancia().buscarGrupoByNombre(nombre);
		if (g == null) {
			Usuario admin = ControladorUsuario.getInstancia().buscarUsuarioPorApodo(administrador.getApodo());
			Grupo grupo = new Grupo(nombre, admin);
			this.grupos.add(grupo);
			grupo.save();
		} else {
			throw new GrupoException("El nombre de grupo: " + nombre + "ya esta en uso.");
		}
	}

	public void bajaGrupo(GrupoDTO g) throws GrupoException, CategoriaException, UsuarioException {
		Grupo grupo = GrupoDAO.getInstancia().buscarGrupoById(g.getIdGrupo());
		grupo.bajaGrupo();
	}

	public void modificarGrupo(String nombreActual, String nombre, String apodoAdministrador, int puntoPorPartida) throws GrupoException, UsuarioException, CategoriaException {
		Grupo grupo = buscarGrupo(nombreActual);
		grupo.setAdministrador(ControladorUsuario.getInstancia().buscarUsuarioPorApodo(apodoAdministrador));
		grupo.setPuntoPorPartida(puntoPorPartida);
		// FIXME Se puede mejorar
//		esNombreGrupoRepetido(nombre);
		grupo.setNombre(nombre);
	}

	public static ControladorGrupo getInstancia() {
		if (instancia == null) {
			instancia = new ControladorGrupo();
		}
		return instancia;
	}
}
