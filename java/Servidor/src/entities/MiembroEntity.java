package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class MiembroEntity {

	@Id
	private int idMiembro;
	@OneToMany
	@JoinColumn(name = "idMiembro")
	private UsuarioEntity usuario;
	@OneToMany
	@JoinColumn(name = "idMiembro")
	private GrupoEntity grupo;
	private int puntaje;
	private boolean enGrupo;

	public MiembroEntity(int idMiembro, int puntaje, boolean enGrupo) {
		super();
		this.idMiembro = idMiembro;
		this.puntaje = puntaje;
		this.enGrupo = enGrupo;
	}

	public int getIdMiembro() {
		return idMiembro;
	}

	public void setIdMiembro(int idMiembro) {
		this.idMiembro = idMiembro;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public GrupoEntity getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoEntity grupo) {
		this.grupo = grupo;
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
}
