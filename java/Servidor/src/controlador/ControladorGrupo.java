package controlador;

import java.util.Vector;

import negocio.GrupoJuego;

public class ControladorGrupo {

	private Vector<GrupoJuego> grupos;

	private static ControladorGrupo instancia;

	public ControladorGrupo() {
		grupos = new Vector<>();
	}

	public ControladorGrupo(Vector<GrupoJuego> grupos) {
		setGrupos(grupos);
	}

	public Vector<GrupoJuego> getGrupos() {
		return grupos;
	}

	public void setGrupos(Vector<GrupoJuego> grupos) {
		this.grupos = grupos;
	}

	public static ControladorGrupo getInstancia() {
		if (instancia == null) {
			instancia = new ControladorGrupo();
		}
		return instancia;
	}
}
