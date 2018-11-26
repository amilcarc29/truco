package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Jugador")
public class JugadorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idJugador;
	@OneToOne
	@JoinColumn(name = "idUsuario")
	private UsuarioEntity usuario;
	@OneToOne
	@JoinColumn(name = "idPareja")
	private ParejaEntity pareja;
	@OneToOne
	@JoinColumn(name = "idMiembro")
	private MiembroEntity miembro;
	private String tipo;
	@OneToOne
	@JoinColumn(name = "idJuego")
	private JuegoEntity juego;

	private boolean tieneTurno;

	private int orden;

	@OneToOne
	@JoinColumn(name = "tanto")
	private TantoEntity tanto;
	
	private int puntosEnvido;

	public JugadorEntity() {
	}

	public JugadorEntity(UsuarioEntity usuario, ParejaEntity pareja, MiembroEntity miembro, String tipo) {
		super();
		this.usuario = usuario;
		this.pareja = pareja;
		this.miembro = miembro;
		this.tipo = tipo;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public JuegoEntity getJuego() {
		return juego;
	}

	public void setJuego(JuegoEntity juego) {
		this.juego = juego;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public ParejaEntity getPareja() {
		return pareja;
	}

	public void setPareja(ParejaEntity pareja) {
		this.pareja = pareja;
	}

	public MiembroEntity getMiembro() {
		return miembro;
	}

	public void setMiembro(MiembroEntity miembro) {
		this.miembro = miembro;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public boolean geTieneTurno() {
		return tieneTurno;
	}

	public void setTieneTurno(boolean tieneTurno) {
		this.tieneTurno = tieneTurno;
	}

	public TantoEntity getTanto() {
		return tanto;
	}

	public void setTanto(TantoEntity tanto) {
		this.tanto = tanto;
	}

	public boolean isTieneTurno() {
		return tieneTurno;
	}

	public void setIdJugador(Integer idJugador) {
		this.idJugador = idJugador;
	}

	public int getPuntosEnvido() {
		return puntosEnvido;
	}

	public void setPuntosEnvido(int puntosEnvido) {
		this.puntosEnvido = puntosEnvido;
	}
	
	

}
