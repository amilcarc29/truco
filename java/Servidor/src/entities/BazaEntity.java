package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Baza")
public class BazaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idBaza;

	@OneToOne
	@JoinColumn(name = "idMano")
	private ManoEntity mano;

	@OneToOne
	@JoinColumn(name = "idJugadaMayor", referencedColumnName = "idJugada")
	private JugadaEntity jugadaMayor;

	public BazaEntity() {
	}

	public BazaEntity(ManoEntity mano, JugadaEntity jugadaMayor) {
		super();
		this.mano = mano;
		this.jugadaMayor = jugadaMayor;
	}

	public int getIdBaza() {
		return idBaza;
	}

	public void setIdBaza(int idBaza) {
		this.idBaza = idBaza;
	}

	public ManoEntity getMano() {
		return mano;
	}

	public void setMano(ManoEntity mano) {
		this.mano = mano;
	}

	public JugadaEntity getJugadaMayor() {
		return jugadaMayor;
	}

	public void setJugadaMayor(JugadaEntity jugadaMayor) {
		this.jugadaMayor = jugadaMayor;
	}
	
	
}
