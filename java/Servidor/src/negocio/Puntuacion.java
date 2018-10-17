package negocio;

import dao.PuntuacionDAO;
import excepciones.ParejaException;

public class Puntuacion {
	private int idPuntuacion;
	private Pareja pareja;
	private int puntos;
	
	

	public Puntuacion(Pareja pareja) {
		super();
		this.pareja = pareja;
		this.puntos = 0;
	}
	

	public int getIdPuntuacion() {
		return idPuntuacion;
	}



	public void setIdPuntuacion(int idPuntuacion) {
		this.idPuntuacion = idPuntuacion;
	}



	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}



	public Pareja getPareja() {
		return pareja;
	}

	public void setPareja(Pareja pareja) {
		this.pareja = pareja;
	}

	public int getPuntos() {
		return puntos;
	}
	public void sumarPuntos(Puntuacion puntuacion) {
		this.puntos += puntuacion.getPuntos();
	}

	public void sumarPuntos(int puntos) {
		this.puntos += puntos;
	}

	public boolean esPuntuacion(Puntuacion puntuacion) {
		// TODO Auto-generated method stub
		return this.pareja.esPareja(puntuacion.getPareja().getIdPareja());
	}

	public boolean tieneJugador(int idJugador) {
		// TODO Auto-generated method stub
		return this.pareja.tieneJugador(idJugador);
	}
	
	public void save (Chico chico) throws ParejaException {
		try {
			this.setIdPuntuacion(PuntuacionDAO.getInstancia().guardarPuntuacion(chico, this.getPareja()));
		} catch (ParejaException e) {
			e.printStackTrace();
		}
	}
}
