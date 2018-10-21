package negocio;

public class FaltaEnvido extends ComponenteEnvido {
	private ComponenteEnvido dec = null;
	private int puntosParaGanar = 0;

	public void addDec(ComponenteEnvido dec) {
		this.dec = dec;
	}

	public FaltaEnvido(int puntosParaGanar) {
		this.puntosParaGanar = puntosParaGanar;
	}

	@Override
	public int getPuntosQuiero() {
		// TODO Auto-generated method stub
		return this.puntosParaGanar;
	}

	@Override
	public int getPuntosNoQuiero() {
		// TODO Auto-generated method stub
		if (dec != null)
			return this.dec.getPuntosQuiero();

		return 1;
	}

}
