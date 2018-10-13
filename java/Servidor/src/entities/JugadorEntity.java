package entities;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class JugadorEntity {
	
	
	@Id
	private int idJugador;
	@OneToOne
	@JoinColumn(name = "idUsuario")
	private UsuarioEntity usuario;
	@OneToOne
	@JoinColumn(name = "idPareja")
	private ParejaEntity pareja;
	@OneToMany
	@JoinColumn(name = "idMiembro")
	private MiembroEntity miembro;
	private String tipo;
	
	public JugadorEntity(int idJugador, UsuarioEntity usuario, ParejaEntity pareja, MiembroEntity miembro,
			String tipo) {
		super();
		this.idJugador = idJugador;
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
	
	

}
