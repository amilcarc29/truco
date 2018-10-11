package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Usuarios")
public class UsuarioEntity {

	@Id
	private int idUsuario;
	private int partidasGanadas;
	private int partidasPerdidas;
	private int puntaje;

	private String apodo;
	private String pass;
	private String email;

	@OneToMany
	@JoinColumn(name = "idUsuario")
	private CategoriaEntity categoria;
	private boolean activo;

	public UsuarioEntity() {
	}

	public UsuarioEntity(int idUsuario, int partidasGanadas, int partidasPerdidas, int puntaje, String apodo,
			String pass, String email, boolean activo) {
		super();
		setIdUsuario(idUsuario);
		setPartidasGanadas(partidasGanadas);
		setPartidasPerdidas(partidasPerdidas);
		setPuntaje(puntaje);
		setApodo(apodo);
		setPass(pass);
		setEmail(email);
		setActivo(activo);
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

	public int getPartidasPerdidas() {
		return partidasPerdidas;
	}

	public void setPartidasPerdidas(int partidasPerdidas) {
		this.partidasPerdidas = partidasPerdidas;
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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
