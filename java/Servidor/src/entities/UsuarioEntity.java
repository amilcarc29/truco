package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Usuario")
public class UsuarioEntity {

	@Id
	private int idUsuario;
	private int partidasGanadas;
	private int partidasJugadas;
	private int puntaje;
	private String apodo;
	private String pass;
	private String email;
	
	@OneToOne
	@JoinColumn(name = "idCategoria")
	private CategoriaEntity categoria;
	
	
	private boolean activo;

	public UsuarioEntity() {
	}

	public UsuarioEntity(int partidasGanadas, int partidasJugadas, int puntaje, String apodo,
			String pass, String email, boolean activo) {
		super();
		this.partidasGanadas = partidasGanadas;
		this.partidasJugadas = partidasJugadas;
		this.puntaje = puntaje;
		this.apodo = apodo;
		this.pass = pass;
		this.email = email;
		this.activo = activo;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getPartidasGanadas() {
		return partidasGanadas;
	}

	public void setPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}

	public int getPartidasJugadas() {
		return partidasJugadas;
	}

	public void setPartidasJugadas(int partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CategoriaEntity getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	
}
