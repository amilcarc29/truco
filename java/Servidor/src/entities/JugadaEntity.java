package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Jugada")
public class JugadaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idJugada;

	@OneToOne
	@JoinColumn(name = "idJugador")
	private JugadorEntity jugador;

	@OneToOne
	@JoinColumn(name = "idBaza")
	private BazaEntity baza;

	@OneToOne
	@JoinColumn(name = "idCarta")
	private CartaEntity carta;

	public JugadaEntity() {
	}
}
