package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Grupo")
public class GrupoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idGrupo;
	@OneToOne
	@JoinColumn(name = "idUsuarioAdmin", referencedColumnName = "idUsuario")
	private UsuarioEntity administrador;
	private String nombre;
	private boolean activo;

	public GrupoEntity() {
	}

	public GrupoEntity(int idGrupo, UsuarioEntity administrador, String nombre) {
		super();
		setIdGrupo(idGrupo);
		setAdministrador(administrador);
		setNombre(nombre);
		setActivo(true);
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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	
}
