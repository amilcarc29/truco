package negocio;

import java.util.List;

import dao.JuegoDAO;
import excepciones.CategoriaException;
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
	public void calcularPuntos() {
		// TODO Auto-generated method stub
		
	}
	
	public void save() throws ParejaException {
		JuegoDAO.getInstancia().guardarJuegoLibreEnPareja(this);
	}

}
