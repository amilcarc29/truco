package negocio;

public class Truco extends ComponenteTruco {

	private ComponenteTruco dec = null;

	public Truco() {
	}

	public void addDec(ComponenteTruco dec) {
		this.dec = dec;
	}

	public int getPuntos() {
		if (dec != null)
			return 1 + this.dec.getPuntos();
		
		
		return 1;
	}


}
