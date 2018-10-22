package negocio;

import java.util.List;

import dao.JuegoDAO;
import excepciones.CategoriaException;
import excepciones.MiembroException;
import excepciones.ParejaException;

public class ModalidadLibreIndividual extends Juego{

	public ModalidadLibreIndividual() {
		super();
	}

	public ModalidadLibreIndividual(List<Pareja> parejas) {
		super(parejas);
	}

	@Override
	public int calcularPuntos() {
		return 10;
	}

	public void save() throws ParejaException, CategoriaException, MiembroException {
		this.setId(JuegoDAO.getInstancia().guardarJuegoLibreIndividual(this));
	}
}
