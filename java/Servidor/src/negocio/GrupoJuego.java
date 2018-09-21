package negocio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class GrupoJuego {
	private int id;
	private List<Pareja> pareja;
	private Date fechaCreacion;
	private String tipoJuego;

	public GrupoJuego() {
		pareja = new LinkedList<Pareja>();
		fechaCreacion = new Date();
	}

	public int getId() {
		return id;
	}

	public List<Pareja> getPareja() {
		return pareja;
	}

	public void setPareja(List<Pareja> pareja) {
		this.pareja = pareja;
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

	public boolean agregarJugador(Jugador jugador) {

		for (int i = 0; i < this.pareja.size(); i++) {
			if (this.pareja.get(i).tieneLugar()) {
				this.pareja.get(i).addJugador(jugador);
				break;
			}
		}

		return false;
	}

	public boolean agregarPareja(Pareja pareja) {

		if (this.pareja.size() < 2) {

			this.pareja.add(pareja);
			return true;
		}
		return false;

	}
}
