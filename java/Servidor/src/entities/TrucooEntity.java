package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Truco")
public class TrucooEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idTruco;
	
	@OneToOne
	@JoinColumn(name = "idMano")
	private ManoEntity mano;
	
	@OneToOne
	@JoinColumn(name = "idJugador")
	private JugadorEntity jugador;
	
	private int puntosQuiero;
	private int puntosNoQuiero;
	
	public TrucooEntity() {}

	

	public TrucooEntity(ManoEntity mano, JugadorEntity jugador, int puntosQuiero, int puntosNoQuiero) {
		super();
		this.mano = mano;
		this.jugador = jugador;
		this.puntosQuiero = puntosQuiero;
		this.puntosNoQuiero = puntosNoQuiero;
	}



	public Integer getIdTruco() {
		return idTruco;
	}

	public void setIdTruco(Integer idTruco) {
		this.idTruco = idTruco;
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
