package negocio;

import java.util.List;

import dao.JuegoDAO;
import excepciones.ParejaException;

public class ModalidadCerrada  extends Juego{

	public ModalidadCerrada() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ModalidadCerrada(List<Pareja> parejas) {
		super(parejas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int calcularPuntos() {
		return 5;
	}

	public void save() throws ParejaException {
		JuegoDAO.getInstancia().guardarJuegoCerrado(this);
	}
}
