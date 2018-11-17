package dto;

import java.io.Serializable;

public class MiembroDTO extends JsonDTO implements Serializable{
	
	
	private static final long serialVersionUID = -1871741286516852122L;
	
	
	private int idMiembro;
	private UsuarioDTO usuario;
	private int puntaje;
	private boolean enGrupo;
	
	
	public MiembroDTO(int idMiembro, UsuarioDTO usuario, int puntaje, boolean enGrupo) {
		super();
		this.idMiembro = idMiembro;
		this.usuario = usuario;
		this.puntaje = puntaje;
		this.enGrupo = enGrupo;
	}


	public int getIdMiembro() {
		return idMiembro;
	}


	public void setIdMiembro(int idMiembro) {
		this.idMiembro = idMiembro;
	}


	public UsuarioDTO getUsuario() {
		return usuario;
	}


	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}


	public int getPuntaje() {
		return puntaje;
	}


	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}


	public boolean isEnGrupo() {
		return enGrupo;
	}


	public void setEnGrupo(boolean enGrupo) {
		this.enGrupo = enGrupo;
	}


	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return getJson(this);
	}
	
	
	
	
}
