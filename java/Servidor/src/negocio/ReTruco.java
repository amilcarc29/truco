package negocio;

public class ReTruco extends ComponenteTruco {

	private ComponenteTruco dec = null;

	public ReTruco() {
	}

	public void addDec(ComponenteTruco dec) {
		this.dec = dec;
	}

	@Override
	public int getPuntos() {
		if (dec != null)
			return 1+ this.dec.getPuntos();
//esto esta asi porqu por incio el puntaje es 1 punto
		return 2;
	}

	
}
