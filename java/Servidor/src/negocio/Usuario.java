package negocio;

import dao.UsuarioDAO;
import dto.UsuarioDTO;
import excepciones.CategoriaException;
import utils.HashUtil;

public class Usuario {
	private int idUsuario;
	private int partidasGanadas;
	private int partidasJugadas;
	private int puntaje;
	private String apodo;
	private String pass;
	private String email;
	private Categoria categoria;
	boolean activo;

	public Usuario() {
	}

	public Usuario(String apodo, String pass, String email) {
		super();
		this.partidasGanadas = 0;
		this.partidasJugadas = 0;
		this.puntaje = 0;
		this.apodo = apodo;
		this.pass = pass;
		this.email = email;
		this.activo = true;
		//categoria = new Novato(idUsuario, email, idUsuario, idUsuario, idUsuario);
	}

	public boolean esUsuario(String apodo) {
		return (this.getApodo().equalsIgnoreCase(apodo));
	}

	public boolean validarLogin(String password) {
		return (this.getPass().equals(HashUtil.hashString(password)));
	}

	public void save() throws CategoriaException {
		this.setIdUsuario(UsuarioDAO.getInstancia().guardarUsuario(this));
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

	public void actualizarPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas += partidasGanadas;
	}

	public int getPartidasJugadas() {
		return partidasJugadas;
	}

	public void setPartidasJugadas(int partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}

	public void actualizarPartidasJugadas(int partidasJugadas) {
		this.partidasJugadas += partidasJugadas;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public void actualizarPuntaje(int puntaje) {
		this.puntaje += puntaje;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public float getPromedio() {
		return (this.getPuntaje() / this.getPartidasJugadas());
	}

	public void actualizarCategoria (Categoria categoria) {
		this.setCategoria(categoria);
		UsuarioDAO.getInstancia().actualizarCategoria(this);
		// ver como actualizar en la parte WEB si sigue logueado
	}

	public UsuarioDTO toDTO() {
		return new UsuarioDTO(idUsuario, partidasGanadas, partidasJugadas, puntaje, apodo, pass, email, getCategoria().toDTO(), activo);
	}
}
