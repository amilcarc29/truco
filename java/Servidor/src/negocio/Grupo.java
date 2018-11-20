package negocio;

import java.util.ArrayList;
import java.util.List;

import dao.GrupoDAO;
import dao.MiembroDAO;
import excepciones.CategoriaException;
import excepciones.GrupoException;
import excepciones.MiembroException;
import excepciones.UsuarioException;

public class Grupo {

	private int idGrupo;
	private Usuario administrador;
	private String nombre;
	private int puntoPorPartida;
	private List<Miembro> integrantes;
	private List<ModalidadCerrada> partidas;

	public Grupo(String nombre, Usuario administrador) {
		setNombre(nombre);
		setAdministrador(administrador);
		partidas = new ArrayList<>();
		integrantes = new ArrayList<>();
		this.puntoPorPartida = 5;
	}

	public Grupo() {
		partidas = new ArrayList<>();
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

	public void actualizarRanking(Usuario usuario) {

	}

	public void agregarParticipante(Usuario usuario) throws CategoriaException, MiembroException, UsuarioException, GrupoException {
		Miembro miembro = MiembroDAO.getInstancia().buscarMiembro(usuario.getIdUsuario(), this.getIdGrupo());
		if (miembro == null) {
			Miembro m = new Miembro (0, true, usuario);
			integrantes.add(m);
			m.save(this.getIdGrupo());
		} else {
			throw new MiembroException ("El usuario " + usuario.getApodo() + "ya se encuentra en este Grupo");
		}
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

	public List<Miembro> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<Miembro> integrantes) {
		this.integrantes = integrantes;
	}
	
	public void save () throws CategoriaException {
		this.setIdGrupo(GrupoDAO.getInstancia().guardarGrupo(this));
	}

	public Miembro buscarMiembro(Usuario usuario) throws CategoriaException, MiembroException, UsuarioException {
		Miembro m = null;
		m = MiembroDAO.getInstancia().buscarMiembro(usuario.getIdUsuario(), this.getIdGrupo());
		return m;
	}

	public void bajaGrupo() throws GrupoException, CategoriaException {

		GrupoDAO.getInstancia().bajaGrupo(this);
		
	}
	

//	public GrupoDTO toDTO() {
//		return new GrupoDTO(idGrupo, administrador, nombre, puntoPorPartida, partidas);
//	}
}
