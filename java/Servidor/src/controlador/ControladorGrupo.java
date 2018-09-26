package controlador;

import java.util.Vector;

import excepciones.GrupoException;
import negocio.Grupo;

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

	public static ControladorGrupo getInstancia() {
		if (instancia == null) {
			instancia = new ControladorGrupo();
		}
		return instancia;
	}
}
