package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Pareja")
public class ParejaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPareja;
	@OneToOne
	@JoinColumn(name = "idJugador1", referencedColumnName = "idJugador")
	private JugadorEntity jugador1;
	@OneToOne
	@JoinColumn(name = "idJugador2", referencedColumnName = "idJugador")
	private JugadorEntity jugador2;

	public ParejaEntity(JugadorEntity jugador1, JugadorEntity jugador2) {
		super();
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
	}

	public int getIdPareja() {
		return idPareja;
	}

	public void setIdPareja(int idPareja) {
		this.idPareja = idPareja;
	}

	public JugadorEntity getJugador1() {
		return jugador1;
	}

	public void setJugador1(JugadorEntity jugador1) {
		this.jugador1 = jugador1;
	}

	public JugadorEntity getJugador2() {
		return jugador2;
	}

	public void setJugador2(JugadorEntity jugador2) {
		this.jugador2 = jugador2;
	}
}
