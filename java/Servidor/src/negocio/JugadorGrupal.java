package negocio;

public class JugadorGrupal extends Jugador {
	private Miembro miembro;
	

	public JugadorGrupal(Miembro miembro) {
		super();
		this.miembro = miembro;
	}

	public Miembro getMiembro() {
		return miembro;
	}

	public void setMiembro(Miembro miembro) {
		this.miembro = miembro;
	}
}
