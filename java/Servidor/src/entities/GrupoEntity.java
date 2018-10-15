package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class GrupoEntity {

	@Id
	private int idGrupo;
	@OneToMany
	@JoinColumn(name = "idGrupo")
	@Column(name = "usuarioAdmin")
	private UsuarioEntity administrador;
	private String nombre;

	public GrupoEntity() {
	}

	public GrupoEntity(int idGrupo, UsuarioEntity administrador, String nombre) {
		super();
		setIdGrupo(idGrupo);
		setAdministrador(administrador);
		setNombre(nombre);
	}

	public int getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}

	public UsuarioEntity getAdministrador() {
		return administrador;
	}

	public void setAdministrador(UsuarioEntity administrador) {
		this.administrador = administrador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
