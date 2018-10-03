package negocio;

import java.util.ArrayList;
import java.util.List;

public class Grupo {

	private int idGrupo;
	private Usuario administrador;
	private String nombre;
	private int puntoPorPartida;
	private List<ModalidadCerrada> partidas;

	public Grupo() {
		partidas = new ArrayList<>();
	}

	public int getIdGrupo() {
		return idGrupo;
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

	public void actualizarRanking(Usuario usuario) {

	}

	public void agregarParticipante(Usuario usuario) {

	}

	public boolean esGrupo(int id) {
		return this.idGrupo == id;
	}

	public boolean esGrupo(String nombre) {
		return getNombre().equalsIgnoreCase(nombre);
	}

	public boolean sePuedeJugar() {
		return false;
	}

}
