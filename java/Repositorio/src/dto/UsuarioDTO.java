package dto;

import java.io.Serializable;

import com.google.gson.JsonObject;

public class UsuarioDTO implements Serializable{

	private static final long serialVersionUID = 913850522288957303L;

	private int idUsuario;
	private int partidasGanadas;
	private int partidasPerdidas;
	private int puntaje;
	private String apodo;
	private String pass;
	private String email;
	private CategoriaDTO categoria;
	private boolean activo;
	public JsonObject toJson() {
		
		JsonObject us = new JsonObject();
		us.add("", value);
		return null;

	}
	public UsuarioDTO(int idUsuario, int partidasGanadas, int partidasPerdidas, int puntaje, String apodo, String pass,
			String email, CategoriaDTO categoria, boolean activo) {
		super();
		this.idUsuario = idUsuario;
		this.partidasGanadas = partidasGanadas;
		this.partidasPerdidas = partidasPerdidas;
		this.puntaje = puntaje;
		this.apodo = apodo;
		this.pass = pass;
		this.email = email;
		this.categoria = categoria;
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

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
