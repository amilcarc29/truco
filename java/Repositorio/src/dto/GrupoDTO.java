package dto;

import java.util.List;

import negocio.ModalidadCerrada;
import negocio.Usuario;

public class GrupoDTO {

	private int idGrupo;
	private Usuario administrador;
	private String nombre;
	private int puntoPorPartida;
	private List<ModalidadCerrada> partidas;

	public GrupoDTO(int idGrupo, Usuario administrador, String nombre, int puntoPorPartida,
			List<ModalidadCerrada> partidas) {
		super();
		setIdGrupo(idGrupo);
		setAdministrador(administrador);
		setNombre(nombre);
		setPuntoPorPartida(puntoPorPartida);
		setPartidas(partidas);
	}

	public int getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Usuario getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Usuario administrador) {
		this.administrador = administrador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntoPorPartida() {
		return puntoPorPartida;
	}

	public void setPuntoPorPartida(int puntoPorPartida) {
		this.puntoPorPartida = puntoPorPartida;
	}

	public List<ModalidadCerrada> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<ModalidadCerrada> partidas) {
		this.partidas = partidas;
	}
}
