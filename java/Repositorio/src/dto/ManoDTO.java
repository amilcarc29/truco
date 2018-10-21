package dto;

import java.util.List;



public class ManoDTO {
	
	private int idMano;
	private List<ParejaDTO> parejas;
	private List<BazaDTO> bazas;
	private List<JugadorDTO> jugadores;
	

	private int puntoParaTerminarChico;
	
	private boolean seCantoEnvido;
	private boolean seCantoTruco;

	public ManoDTO(int idMano, List<ParejaDTO> parDTO, List<BazaDTO> bazDTO, List<JugadorDTO> jugDTO, int puntoParaTerminarChico, boolean seCantoEnvido, boolean seCantoTruco) {
		// TODO Auto-generated constructor stub
		this.idMano=idMano;
		this.parejas=parDTO;
		this.bazas=bazDTO;
		this.jugadores=jugDTO;
		
		this.puntoParaTerminarChico=puntoParaTerminarChico;
		this.seCantoEnvido=seCantoEnvido;
		this.seCantoTruco=seCantoTruco;
		
	}

	public int getIdMano() {
		return idMano;
	}

	public void setIdMano(int idMano) {
		this.idMano = idMano;
	}

	public List<ParejaDTO> getParejas() {
		return parejas;
	}

	public void setParejas(List<ParejaDTO> parejas) {
		this.parejas = parejas;
	}

	public List<BazaDTO> getBazas() {
		return bazas;
	}

	public void setBazas(List<BazaDTO> bazas) {
		this.bazas = bazas;
	}

	public List<JugadorDTO> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<JugadorDTO> jugadores) {
		this.jugadores = jugadores;
	}



	public int getPuntoParaTerminarChico() {
		return puntoParaTerminarChico;
	}

	public void setPuntoParaTerminarChico(int puntoParaTerminarChico) {
		this.puntoParaTerminarChico = puntoParaTerminarChico;
	}

	public boolean isSeCantoEnvido() {
		return seCantoEnvido;
	}

	public void setSeCantoEnvido(boolean seCantoEnvido) {
		this.seCantoEnvido = seCantoEnvido;
	}

	public boolean isSeCantoTruco() {
		return seCantoTruco;
	}

	public void setSeCantoTruco(boolean seCantoTruco) {
		this.seCantoTruco = seCantoTruco;
	}
	
	
	
	

}
