package negocio;

public class Envido extends ComponenteEnvido {
	private ComponenteEnvido dec = null;

	public void addDec(ComponenteEnvido dec) {
		this.dec = dec;
	}

	public Envido() {

	}

	@Override
	public int getPuntosQuiero() {
		// TODO Auto-generated method stub
		if (dec != null)
			return 2 + this.dec.getPuntosQuiero();

		return 2;
	}

	@Override
	public int getPuntosNoQuiero() {
		if (dec != null)
			return 1 + this.dec.getPuntosQuiero();

		return 1;
	}
}
