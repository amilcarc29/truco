package negocio;

import java.util.List;

import dao.JuegoDAO;
import dao.UsuarioDAO;
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
		this.setId(JuegoDAO.getInstancia().guardarJuegoLibreEnPareja(this));
	}

	@Override
	public void finalizarJuego() throws UsuarioException, CategoriaException, ParejaException, MiembroException {
		
	}

	@Override
	public Categoria obtenerCategoriaMayor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calcularPuntos(Usuario usuario) {
		// TODO Auto-generated method stub
		return 0;
	}
}
