package negocio;

public class Carta {

	private int idCarta;
	private int numero;
	private String palo;
	private int pesoTruco;
	private int pesoEnvido;

	public Carta(int idCarta, int numero, String palo, int pesoTruco, int pesoEnvido) {
		super();
		// TODO Levantar el idCarta de la base de datos.
		this.idCarta = idCarta;
		this.numero = numero;
		this.palo = palo;
		this.pesoTruco = pesoTruco;
		this.pesoEnvido = pesoEnvido;
	}

	public Carta() {
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getPalo() {
		return palo;
	}

	public void setPalo(String palo) {
		this.palo = palo;
	}

	public int getPesoTruco() {
		return pesoTruco;
	}

	public void setPesoTruco(int pesoTruco) {
		this.pesoTruco = pesoTruco;
	}

	public int getPesoEnvido() {
		return pesoEnvido;
	}

	public void setPesoEnvido(int pesoEnvido) {
		this.pesoEnvido = pesoEnvido;
	}
}
