package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "JugadorCarta")
public class JugadorCartaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idJugadorCarta;

	@OneToOne
	@JoinColumn(name = "idJugador")
	private JugadorEntity Jugador;

	@OneToOne
	@JoinColumn(name = "idCarta")
	private CartaEntity Carta;

	
	@OneToOne
	@JoinColumn(name = "idBaza")
	private BazaEntity baza;

	
	private boolean cartaJugada;

	public JugadorCartaEntity() {

	}
	public void setBaza(BazaEntity baza) {
		this.baza = baza;
	}
	public JugadorCartaEntity(JugadorEntity jugador, CartaEntity carta) {
		this.Jugador = jugador;
		this.Carta = carta;
		this.setCartaJugada(false);

	}

	public CartaEntity getCarta() {

		return this.Carta;
	}

	public boolean isCartaJugada() {
		return cartaJugada;
	}

	public void setCartaJugada(boolean cartaJugada) {
		this.cartaJugada = cartaJugada;
	}
}
