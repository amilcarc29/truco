package negocio;

public abstract class Jugador {
	private String nombre = "";
	private static int idJugador = 0;

	public Jugador(String nombre) {
		super();
		setNombre(nombre);
		// TODO levantar el id de la bd.
		this.idJugador = 0;
	}

	public Jugador() {
		super();
		idJugador++;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	// TODO agregar

	public boolean esJugador(int idjugador) {

		return (idJugador == idjugador);
	}

}
