package negocio;

import dao.JuegoDAO;
import excepciones.CategoriaException;
import excepciones.UsuarioException;

public class ModalidadLibreIndividual extends Juego{

	@Override
	public void calcularPuntos() {
		// TODO Auto-generated method stub
		
	}
	public void save(String tipo) throws UsuarioException, CategoriaException {
		super.save(tipo);
	}

}
