package negocio;

import dao.UsuarioDAO;
import dto.UsuarioDTO;
import excepciones.CategoriaException;
import excepciones.UsuarioException;
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
	private boolean loggeado;

	public Usuario() {
	}

	public Usuario(String apodo, String email, String pass) {
		super();
		this.partidasGanadas = 0;
		this.partidasJugadas = 0;
		this.puntaje = 0;
		this.apodo = apodo;
		this.pass = pass;
		this.email = email;
		this.activo = true;
		this.loggeado = false;
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

	public void actualizarCategoria (Categoria categoria) throws UsuarioException, CategoriaException {
		this.setCategoria(categoria);
		UsuarioDAO.getInstancia().actualizarCategoria(this);
		// ver como actualizar en la parte WEB si sigue logueado
	}
	
	public void actualizarPuntos(int partidasGanadas, int partidasJugadas, int puntaje)
			throws CategoriaException, UsuarioException {
		this.actualizarPartidasGanadas(partidasGanadas);
		this.actualizarPartidasJugadas(partidasJugadas);
		this.actualizarPuntaje(puntaje);
		UsuarioDAO.getInstancia().actualizarPuntajesUsuario(this);
		this.getCategoria().actualizar(this);
	}

	public UsuarioDTO toDTO() {
		return new UsuarioDTO(idUsuario, partidasGanadas, partidasJugadas, puntaje, apodo, pass, email, getCategoria().toDTO(), activo);
	}

	public boolean sosElUsuario(int idUsuario2) {
		return (this.getIdUsuario() == idUsuario2);
	}

	public boolean isLoggeado() {
		return loggeado;
	}

	public void setLoggeado(boolean loggeado) {
		this.loggeado = loggeado;
	}

	public void loggear() throws UsuarioException, CategoriaException {
		this.setLoggeado(true);
		UsuarioDAO.getInstancia().actualizarLoggeado(this);		
	}

	public void desloggear() throws UsuarioException, CategoriaException {
		this.setLoggeado(false);
		UsuarioDAO.getInstancia().actualizarLoggeado(this);			
	}
	
	
}
