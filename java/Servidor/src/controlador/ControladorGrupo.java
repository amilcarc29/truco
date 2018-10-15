package controlador;

import java.util.Vector;

import dao.MiembroDAO;
import dto.GrupoDTO;
import dto.UsuarioDTO;
import excepciones.CategoriaException;
import excepciones.GrupoException;
import excepciones.MiembroException;
import excepciones.UsuarioException;
import negocio.Grupo;
import negocio.Miembro;

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
	public Grupo buscarGrupo(String nombre) throws GrupoException {
		for (Grupo grupo : grupos) {
			if (grupo.esGrupo(nombre)) {
				return grupo;
			}
		}
		throw new GrupoException("El grupo con nombre: " + nombre + "no existe.");
	}

	private void esNombreGrupoRepetido(String nombre) throws GrupoException {
		for (Grupo grupo : grupos) {
			if (grupo.esGrupo(nombre)) {
				throw new GrupoException("El nombre de grupo: " + nombre + "ya está en uso.");
			}
		}
	}
	
	public Miembro buscarMiembro(UsuarioDTO u, GrupoDTO g) throws CategoriaException, MiembroException {
		try {
			Miembro m = MiembroDAO.getInstancia().buscarMiembro(u.getIdUsuario(), g.getIdGrupo());
			return m;
		} catch (CategoriaException e) {
			e.printStackTrace();
		} catch (MiembroException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public void altaGrupo(String nombre, String apodoAdministrador, int puntoPorPartida) throws UsuarioException, GrupoException, CategoriaException {
		Grupo grupo = new Grupo();
		// FIXME Se puede mejorar.
		esNombreGrupoRepetido(nombre);
		grupo.setNombre(nombre);
		grupo.setAdministrador(ControladorUsuario.getInstancia().buscarUsuarioPorApodo(apodoAdministrador));
		grupo.setPuntoPorPartida(puntoPorPartida);
		this.grupos.add(grupo);
	}

	public void bajaGrupo(String nombre) throws GrupoException {
		this.grupos.remove(buscarGrupo(nombre));
	}

	public void modificarGrupo(String nombreActual, String nombre, String apodoAdministrador, int puntoPorPartida) throws GrupoException, UsuarioException, CategoriaException {
		Grupo grupo = buscarGrupo(nombreActual);
		grupo.setAdministrador(ControladorUsuario.getInstancia().buscarUsuarioPorApodo(apodoAdministrador));
		grupo.setPuntoPorPartida(puntoPorPartida);
		// FIXME Se puede mejorar
		esNombreGrupoRepetido(nombre);
		grupo.setNombre(nombre);
	}

	public static ControladorGrupo getInstancia() {
		if (instancia == null) {
			instancia = new ControladorGrupo();
		}
		return instancia;
	}
}
