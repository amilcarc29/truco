package negocio;

import java.util.List;

import dao.JuegoDAO;
import excepciones.CategoriaException;
import excepciones.MiembroException;
import excepciones.ParejaException;
import excepciones.UsuarioException;

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

	@Override
	public void finalizarJuego() throws UsuarioException, CategoriaException, ParejaException, MiembroException {
		// TODO Auto-generated method stub
		// FALTA
		
	}
}
