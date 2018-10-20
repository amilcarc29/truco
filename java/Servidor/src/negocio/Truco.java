package negocio;

public class Truco extends ComponenteTruco {

	private ComponenteTruco dec = null;

	public Truco() {
	}

	public void addDec(ComponenteTruco dec) {
		this.dec = dec;
	}

	public int getPuntosQuiero() {
		if (dec != null)
			return this.dec.getPuntosQuiero();
		return 2;
	}

	public int getPuntosNoQuiero() {
		if (dec != null)
			return this.dec.getPuntosNoQuiero();
		return 1;
	}
}
