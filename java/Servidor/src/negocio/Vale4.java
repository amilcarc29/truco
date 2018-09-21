package negocio;

public class Vale4 extends ComponenteTruco {

	private ComponenteTruco dec = null;

	public Vale4() {
	}

	public void addDec(ComponenteTruco dec) {
		this.dec = dec;
	}

	@Override
	public int getPuntosQuiero() {
		if (dec != null)
			return this.dec.getPuntosQuiero();
		return 4;
	}

	@Override
	public int getPuntosNoQuiero() {
		if (dec != null)
			return this.dec.getPuntosNoQuiero();
		return 3;
	}
}
