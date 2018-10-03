package negocio;

public class JugadorIndividual extends Jugador {

	private Usuario usuario;

	public JugadorIndividual(Usuario usuario) {
		setUsuario(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
