package negocio;

import dao.CategoriaDAO;
import excepciones.CategoriaException;
import excepciones.UsuarioException;

public class Experto extends Categoria{

		
	public Experto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Experto(int idCategoria, String nombre, int score, int minimoPartida, int minimoPuntos, int promedioMinimo) {
		super(idCategoria, nombre, score, minimoPartida, minimoPuntos, promedioMinimo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actualizar(Usuario usuario) throws CategoriaException, UsuarioException {
		// En experto puede subir y puede bajar
		Master master = (Master) CategoriaDAO.getInstancia().buscarCategoriaByNombreNegocio("MASTER");
		if (master.debeSer(usuario)) {
			usuario.actualizarCategoria(master);
		} else {
			if (!this.debeSer(usuario)) {
				Calificado calificado = (Calificado) CategoriaDAO.getInstancia().buscarCategoriaByNombreNegocio("CALIFICADO");
				usuario.actualizarCategoria(calificado);
			}
		}		
	}

	@Override
	public boolean debeSer(Usuario usuario) {
		if (usuario.getPartidasJugadas() >= this.getMinimoPartida() &&
				usuario.getPuntaje() >= this.getMinimoPuntos() &&
				usuario.getPromedio() >= this.getPromedioMinimo())
			return true;
		else
			return false;
	}

	
}
