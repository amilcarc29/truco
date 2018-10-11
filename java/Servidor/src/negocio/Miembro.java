package negocio;

public class Miembro {

	private int idMiembro;
	private Usuario usuario;
	private Grupo grupo;
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

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
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
}
