package negocio;

import dao.MiembroDAO;
import dto.MiembroDTO;
import dto.UsuarioDTO;
import excepciones.CategoriaException;
import excepciones.GrupoException;
import excepciones.UsuarioException;

public class Miembro {

	private int idMiembro;
	private Usuario usuario;
	private int puntaje;
	private boolean enGrupo;

	public Miembro(int puntaje, boolean enGrupo, Usuario usuario) {
		super();
		setUsuario(usuario);
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

	public void save(int idGrupo) throws CategoriaException, GrupoException, UsuarioException {

		this.setIdMiembro(MiembroDAO.getInstancia().guardarMiembro(this, idGrupo));
		
	}
}
