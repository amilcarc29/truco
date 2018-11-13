package dto;

import java.io.Serializable;
import java.util.List;

public class ChicoDTO extends JsonDTO  implements Serializable{
	private int idChico;
	private List<ManoDTO> manos;
	private List<ParejaDTO> parejas;
	private List<PuntuacionDTO> puntosChico;
	private ParejaDTO ganador;
	private int puntosPorGanar;
	private List<JugadorDTO> jugadores;	
	private boolean sePuedeCantarEnvido = true;


	public ChicoDTO(int idChico, List<ManoDTO> manDTO, List<ParejaDTO> parDTO, List<PuntuacionDTO> punDTO, ParejaDTO parejaDTO, int puntosPorGanar, List<JugadorDTO> jugDTO, boolean sePuedeCantarEnvido) {
		// TODO Auto-generated constructor stub
		
		this.idChico=idChico;
		this.manos=manDTO;
		this.parejas=parDTO;
		this.puntosChico=punDTO;
		this.ganador=parejaDTO;
		this.puntosPorGanar=puntosPorGanar;
		this.jugadores=jugDTO;
		this.sePuedeCantarEnvido=sePuedeCantarEnvido;
	}
	
	public int getIdChico() {
		return idChico;
	}

	public void setIdChico(int idChico) {
		this.idChico = idChico;
	}

	public List<ManoDTO> getManos() {
		return manos;
	}

	public void setManos(List<ManoDTO> manos) {
		this.manos = manos;
	}

	public List<ParejaDTO> getParejas() {
		return parejas;
	}

	public void setParejas(List<ParejaDTO> parejas) {
		this.parejas = parejas;
	}

	public List<PuntuacionDTO> getPuntosChico() {
		return puntosChico;
	}

	public void setPuntosChico(List<PuntuacionDTO> puntosChico) {
		this.puntosChico = puntosChico;
	}

	public ParejaDTO getGanador() {
		return ganador;
	}

	public void setGanador(ParejaDTO ganador) {
		this.ganador = ganador;
	}

	public int getPuntosPorGanar() {
		return puntosPorGanar;
	}

	public void setPuntosPorGanar(int puntosPorGanar) {
		this.puntosPorGanar = puntosPorGanar;
	}

	public List<JugadorDTO> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<JugadorDTO> jugadores) {
		this.jugadores = jugadores;
	}

	public boolean isSePuedeCantarEnvido() {
		return sePuedeCantarEnvido;
	}

	public void setSePuedeCantarEnvido(boolean sePuedeCantarEnvido) {
		this.sePuedeCantarEnvido = sePuedeCantarEnvido;
	}
	
	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		 return getJson(this);
	}
}
