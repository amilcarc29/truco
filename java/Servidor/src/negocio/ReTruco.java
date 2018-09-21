package negocio;

public class ReTruco extends ComponenteTruco {

	private ComponenteTruco dec = null;

	public ReTruco() {

	}

	public void addDec(ComponenteTruco dec) {
		this.dec = dec;
	}

	@Override
	public int getPuntosQuiero() {
		// TODO Auto-generated method stub
		if (dec != null)
			return this.dec.getPuntosQuiero();

		return 3;
	}

	@Override
	public int getPuntosNoQuiero() {
		// TODO Auto-generated method stub
		if (dec != null)
			return this.dec.getPuntosNoQuiero();

		return 2;
	}
}
