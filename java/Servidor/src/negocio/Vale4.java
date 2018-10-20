package negocio;

public class Vale4 extends ComponenteTruco {

	private ComponenteTruco dec = null;

	public Vale4() {
	}

	public void addDec(ComponenteTruco dec) {
		this.dec = dec;
	}

	@Override
	public int getPuntos() {
		if (dec != null)
			return 1+  this.dec.getPuntos();
		
		return 3;
	}


}
