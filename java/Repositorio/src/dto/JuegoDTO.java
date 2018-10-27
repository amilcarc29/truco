package dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class JuegoDTO implements Serializable {
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
		this.parejas=parejas;
		this.chicos=chicos;
		this.ganador=ganador;
		this.fecha=fec;
	}


	public int getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

	public String toJson() {
		return JsonDTO.getJson(this);
	}
}
