package dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class JuegoDTO extends JsonDTO  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3203339374060504339L;
	private int idJuego;
	private List<ParejaDTO> parejas;
	private List<ChicoDTO> chicos;
	private ParejaDTO ganador;
	private Date fecha;
	
	

	public JuegoDTO(int idJuego,List<ParejaDTO> parejas,List<ChicoDTO> chicos,ParejaDTO ganador,Date fec) {
		super();
		this.idJuego = idJuego;
		this.setParejas(parejas);
		this.setChicos(chicos);
		this.setGanador(ganador);
		this.setFecha(fec);
	}


	public int getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		 return getJson(this);
	}

	public List<ParejaDTO> getParejas() {
		return parejas;
	}


	public void setParejas(List<ParejaDTO> parejas) {
		this.parejas = parejas;
	}


	public List<ChicoDTO> getChicos() {
		return chicos;
	}


	public void setChicos(List<ChicoDTO> chicos) {
		this.chicos = chicos;
	}


	public ParejaDTO getGanador() {
		return ganador;
	}


	public void setGanador(ParejaDTO ganador) {
		this.ganador = ganador;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
