package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Juego {

	private int idJuego;
	private List<Pareja> parejas;
	private List<Chico> chicos;
	private Pareja ganador;
	private int puntoBase;
	private Date fecha;
	private boolean activo;
	
	public Juego() {
		super();
		this.parejas = new ArrayList<>();
		this.chicos = new ArrayList<>();
		setPuntoBase(0);
		this.fecha = new Date();
		setActivo(true);
	}


	public abstract void calcularPuntos();

	public int getIdJuego() {
		return idJuego;
	}

	public List<Pareja> getParejas() {
		return parejas;
	}

	public void setParejas(List<Pareja> parejas) {
		this.parejas = parejas;
	}

	public List<Chico> getChicos() {
		return chicos;
	}

	public void setChicos(List<Chico> chicos) {
		this.chicos = chicos;
	}

	public Pareja getGanador() {
		return ganador;
	}

	public void setGanador(Pareja ganador) {
		this.ganador = ganador;
	}

	public int getPuntoBase() {
		return puntoBase;
	}

	public void setPuntoBase(int puntoBase) {
		this.puntoBase = puntoBase;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Pareja obtenerGanador() {
		return null;

	}

	public boolean termino() {
		return false;
	}

	public void finalizarJuego() {

	}

	public Categoria obtenerCategoriaMayor() {
		return null;

	}
	
	// TODO Agregar a Diagrama.
	public void crearChico() {
		Chico chico = new Chico(parejas);
		chicos.add(chico);
	}
}
