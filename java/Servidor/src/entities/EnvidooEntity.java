package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Envite")
public class EnvidooEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idEnvite;
	
	@OneToOne
	@JoinColumn(name = "idMano")
	private ManoEntity mano;
	
	@OneToOne
	@JoinColumn(name = "idJugador")
	private JugadorEntity jugador;
	
	private int puntosQuiero;
	
	private int puntosNoQuiero;
	
	public EnvidooEntity() {}

	public EnvidooEntity(ManoEntity mano, JugadorEntity jugador, int puntosQuiero,
			int puntosNoQuiero) {
		super();
		this.mano = mano;
		this.jugador = jugador;
		this.puntosQuiero = puntosQuiero;
		this.puntosNoQuiero = puntosNoQuiero;
	}

	public Integer getIdEnvite() {
		return idEnvite;
	}

	public void setIdEnvite(Integer idEnvite) {
		this.idEnvite = idEnvite;
	}

	public ManoEntity getMano() {
		return mano;
	}

	public void setMano(ManoEntity mano) {
		this.mano = mano;
	}

	public JugadorEntity getJugador() {
		return jugador;
	}

	public void setJugador(JugadorEntity jugador) {
		this.jugador = jugador;
	}

	public int getPuntosQuiero() {
		return puntosQuiero;
	}

	public void setPuntosQuiero(int puntosQuiero) {
		this.puntosQuiero = puntosQuiero;
	}

	public int getPuntosNoQuiero() {
		return puntosNoQuiero;
	}

	public void setPuntosNoQuiero(int puntosNoQuiero) {
		this.puntosNoQuiero = puntosNoQuiero;
	}
	
	
	
	
}
