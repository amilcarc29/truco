package negocio;

import java.util.Date;
import java.util.List;

public class GrupoJuego {
	private int id;
	private List<Pareja> pareja;
	private Date fechaCreacion;
	private String tipoJuego;

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

	public void agregarJugador(Jugador jugador) {

	}

	public void agregarPareja(Pareja pareja) {

	}
}
