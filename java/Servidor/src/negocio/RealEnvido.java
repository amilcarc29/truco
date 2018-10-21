package negocio;

public class RealEnvido extends ComponenteEnvido {

	private ComponenteEnvido dec = null;

	public void addDec(ComponenteEnvido dec) {
		this.dec = dec;
	}

	@Override
	public int getPuntosQuiero() {
		// TODO Auto-generated method stub
		if (dec != null)
			return 3 + this.dec.getPuntosQuiero();

		return 3;
	}

	@Override
	public int getPuntosNoQuiero() {
		// TODO Auto-generated method stub
		if (dec != null)
			return this.dec.getPuntosQuiero();

		return 1;
	}

}
