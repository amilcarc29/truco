package negocio;

public class Truco {
	private int idTruco;
	private int puntos;
	private int orden;

	public void setIdEnvite(int idTruco) {
		this.idTruco = idTruco;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
}
