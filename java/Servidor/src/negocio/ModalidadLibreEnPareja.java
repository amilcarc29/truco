package negocio;

import java.util.List;

import dao.JuegoDAO;
import excepciones.ParejaException;

public class ModalidadLibreEnPareja extends Juego{

	public ModalidadLibreEnPareja() {
		super();
	}

	public ModalidadLibreEnPareja(List<Pareja> parejas) {
		super(parejas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int calcularPuntos() {
		return 10;
	}

	public void save() throws ParejaException {
		JuegoDAO.getInstancia().guardarJuegoLibreEnPareja(this);
	}
}
