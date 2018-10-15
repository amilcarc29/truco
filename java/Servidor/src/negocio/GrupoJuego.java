package negocio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class GrupoJuego {
	private int id;
	private List<Pareja> parejas;
	private Date fechaCreacion;
	private String tipoJuego;  // ver para que esta



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

	public  GrupoJuego(Pareja p1, Pareja p2) {
		parejas = new LinkedList<Pareja>();

		this.parejas.add(p1);
		this.parejas.add(p2);
		
		fechaCreacion = new Date();

	}

	// TODO Agregar a Diagrama.
	public boolean esGrupoJuego(int idGrupo) {
		return getId() == idGrupo;
	}
}
