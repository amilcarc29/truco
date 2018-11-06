package dto;

import java.io.Serializable;

public class PuntuacionDTO  implements Serializable{
	
	private int idPuntuacion;
	private ParejaDTO pareja;
	private int puntos;

	public PuntuacionDTO(int idPuntuacion, ParejaDTO parejaDTO, int puntos) {
		// TODO Auto-generated constructor stub
		
		this.idPuntuacion=idPuntuacion;
		this.pareja=parejaDTO;
		this.puntos=puntos;
	}
	
	
	public int getIdPuntuacion() {
		return idPuntuacion;
	}

	public void setIdPuntuacion(int idPuntuacion) {
		this.idPuntuacion = idPuntuacion;
	}

	public ParejaDTO getPareja() {
		return pareja;
	}

	public void setPareja(ParejaDTO pareja) {
		this.pareja = pareja;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String toJson() {
		return JsonDTO.getJson(this);
	}
}
