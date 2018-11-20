package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "Miembro")
public class MiembroEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idMiembro;
	@OneToOne
	@JoinColumn(name = "idGrupo")
	private GrupoEntity grupo;
	@OneToOne
	@JoinColumn(name = "idUsuario")
	private UsuarioEntity usuario;
	private int puntaje;
	private boolean enGrupo;
	
	
	public MiembroEntity() {}
	
	public MiembroEntity(GrupoEntity grupo, UsuarioEntity usuario, int puntaje, boolean enGrupo) {
		super();
		this.grupo = grupo;
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

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
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

	public GrupoEntity getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoEntity grupo) {
		this.grupo = grupo;
	}

	public void setIdMiembro(Integer idMiembro) {
		this.idMiembro = idMiembro;
	}
	
	
}
