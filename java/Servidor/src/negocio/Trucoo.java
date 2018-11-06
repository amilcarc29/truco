package negocio;

import dao.TrucoDAO;
import excepciones.ManoException;

public class Trucoo {
	
	private int id;	
	private int puntosQuiero;
	private int puntosNoQuiero;
	
	public Trucoo() {
		this.puntosQuiero = 1;
		this.puntosNoQuiero = 1;
	}

		
	public int getPuntosQuiero() {
		return puntosQuiero;
	}



	public void setPuntosQuiero(int puntosQuiero) {
		this.puntosQuiero = puntosQuiero;
	}



	public int getPuntosNoQuiero() {
		return puntosNoQuiero;
	}



	public void setPuntosNoQuiero(int puntosNoQuiero) {
		this.puntosNoQuiero = puntosNoQuiero;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void save(Mano mano) throws ManoException {

		this.setId(TrucoDAO.getInstancia().guardarTruco(this, mano));
		
	}
	
	

}
