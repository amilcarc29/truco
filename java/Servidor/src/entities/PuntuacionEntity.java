package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Puntuacion")
public class PuntuacionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPuntuacion;
	@OneToOne
	@JoinColumn(name = "idChico")
	private ChicoEntity chico;
	@OneToOne
	@JoinColumn(name = "idPareja")
	private ParejaEntity pareja;
	private int puntos;
	
	public PuntuacionEntity() {}

	public PuntuacionEntity(ChicoEntity chico, ParejaEntity pareja, int puntuacion) {
		super();
		this.chico = chico;
		this.pareja = pareja;
		this.puntos = puntuacion;
	}	

	public int getPuntuacion() {
		return puntos;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntos = puntuacion;
	}

	public Integer getIdPuntuacion() {
		return idPuntuacion;
	}

	public void setIdPuntuacion(Integer idPuntuacion) {
		this.idPuntuacion = idPuntuacion;
	}

	public ChicoEntity getChico() {
		return chico;
	}

	public void setChico(ChicoEntity chico) {
		this.chico = chico;
	}

	public ParejaEntity getPareja() {
		return pareja;
	}

	public void setPareja(ParejaEntity pareja) {
		this.pareja = pareja;
	}
	
	
}
