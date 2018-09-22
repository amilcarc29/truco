package negocio;

public class Carta {

	private int idCarta = 0;
	private int numero;
	private String palo;
	private int pesoTruco;
	private int pesoEnvido;
	private static int cnt = 0;

	private static int getID() {
		return cnt++;
	}

	public Carta(int numero, String palo, int pesoTruco, int pesoEnvido) {
		super();
		// TODO Levantar el idCarta de la base de datos.
		this.numero = numero;
		this.palo = palo;
		this.pesoTruco = pesoTruco;
		this.pesoEnvido = pesoEnvido;
		idCarta = getID();
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

	public boolean esCarta(int numero, String palo) {
		// TODO Auto-generated method stub
		return (this.numero == numero) && (this.palo.equals(palo));
	}


}
