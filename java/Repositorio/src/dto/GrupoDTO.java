package dto;

import java.io.Serializable;
import java.util.List;

public class GrupoDTO implements Serializable {

	private static final long serialVersionUID = -3914554002626879933L;

	private int idGrupo;
	private UsuarioDTO administrador;
	private String nombre;
	private int puntoPorPartida;
	private List<ModalidadCerradaDTO> partidas;

	public GrupoDTO(int idGrupo, UsuarioDTO administrador, String nombre, int puntoPorPartida,
			List<ModalidadCerradaDTO> partidas) {
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

	public UsuarioDTO getAdministrador() {
		return administrador;
	}

	public void setAdministrador(UsuarioDTO administrador) {
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

	public List<ModalidadCerradaDTO> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<ModalidadCerradaDTO> partidas) {
		this.partidas = partidas;
	}

	public String toJson() {
		return JsonDTO.getJson(this);
	}
}
