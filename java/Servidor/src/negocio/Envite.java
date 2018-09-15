package negocio;

public abstract class Envite {
	private int idEnvite;
	private int puntos;
	private int orden;

	public void setIdEnvite(int idEnvite) {
		this.idEnvite = idEnvite;
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
