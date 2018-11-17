package negocio;

import dto.MiembroDTO;
import dto.UsuarioDTO;

public class Miembro {

	private int idMiembro;
	private Usuario usuario;
	private int puntaje;
	private boolean enGrupo;

	public Miembro(int idMiembro, int puntaje, boolean enGrupo) {
		super();
		setIdMiembro(idMiembro);
		setPuntaje(puntaje);
		setEnGrupo(enGrupo);
	}

	public int getIdMiembro() {
		return idMiembro;
	}

	public void setIdMiembro(int idMiembro) {
		this.idMiembro = idMiembro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
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
	
	public MiembroDTO toDTO() {
		return new MiembroDTO(this.getIdMiembro(), this.getUsuario().toDTO(), this.getPuntaje(), this.isEnGrupo());
	}
}
