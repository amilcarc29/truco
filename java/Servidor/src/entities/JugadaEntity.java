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
	@JoinColumn(name = "idCarta")
	private CartaEntity carta;

	@OneToOne
	@JoinColumn(name = "idBaza")
	private BazaEntity baza;

	
	public JugadaEntity() {
	}


	public JugadaEntity(JugadorEntity jugador, CartaEntity carta, BazaEntity baza) {
		super();
		this.jugador = jugador;
		this.carta = carta;
		this.baza = baza;
	}


	public int getIdJugada() {
		return idJugada;
	}


	public void setIdJugada(int idJugada) {
		this.idJugada = idJugada;
	}


	public JugadorEntity getJugador() {
		return jugador;
	}


	public void setJugador(JugadorEntity jugador) {
		this.jugador = jugador;
	}


	public CartaEntity getCarta() {
		return carta;
	}


	public void setCarta(CartaEntity carta) {
		this.carta = carta;
	}


	public BazaEntity getBaza() {
		return baza;
	}


	public void setBaza(BazaEntity baza) {
		this.baza = baza;
	}
	
	
	
	
}
