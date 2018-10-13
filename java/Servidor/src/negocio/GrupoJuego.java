package negocio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class GrupoJuego {
	private int id;
	private List<Pareja> parejas;
	private Date fechaCreacion;
	private String tipoJuego;



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

	public  GrupoJuego(Jugador jugador1, Jugador jugador2, Jugador jugador3, Jugador jugador4) {
		parejas = new LinkedList<Pareja>();

		Pareja p1 = new Pareja();
		p1.addJugador(jugador1);
		p1.addJugador(jugador2);

		Pareja p2 = new Pareja();
		p2.addJugador(jugador3);
		p2.addJugador(jugador4);

		this.parejas.add(p1);
		this.parejas.add(p2);
		
		fechaCreacion = new Date();

	}

	// TODO Agregar a Diagrama.
	public boolean esGrupoJuego(int idGrupo) {
		return getId() == idGrupo;
	}
}
