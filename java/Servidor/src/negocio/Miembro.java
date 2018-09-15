package negocio;

public class Miembro {
	private Usuario usuario;
	private int puntaje;
	private boolean enGrupo;

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
