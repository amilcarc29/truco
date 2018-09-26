package negocio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class GrupoJuego {
	private int id;
	private List<Pareja> parejas;
	private Date fechaCreacion;
	private String tipoJuego;

	public GrupoJuego() {
		parejas = new LinkedList<Pareja>();
		fechaCreacion = new Date();
	}

	public int getId() {
		return id;
	}

	public List<Pareja> getParejas() {
		return parejas;
	}

	public void setPareja(List<Pareja> pareja) {
		this.parejas = pareja;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTipoJuego() {
		return tipoJuego;
	}

	public void setTipoJuego(String tipoJuego) {
		this.tipoJuego = tipoJuego;
	}

	public boolean seCompleto() {
		return false;
	}

	public Categoria obtenerCategoriaMayor() {
		return null;
	}

	// TODO addJugadores YA NO ESTA

	public boolean agregarJugador(Jugador jugador1, Jugador jugador2) {

		Pareja p = new Pareja();
		p.addJugador(jugador1);
		p.addJugador(jugador2);
		return agregarPareja(p);

	}

	public boolean agregarPareja(Pareja pareja) {

		if (this.parejas.size() < 2) {

			this.parejas.add(pareja);
			return true;
		}
		return false;

	}

	// TODO Agregar a Diagrama.
	public boolean esGrupoJuego(int idGrupo) {
		return getId() == idGrupo;
	}
}
