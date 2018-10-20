package negocio;

import dao.CategoriaDAO;
import excepciones.CategoriaException;

public class Novato extends Categoria {

	public Novato(int idCategoria, String nombre, int score, int minimoPartidas, int minimoPuntos, int promedioMinimo) {
		super(idCategoria, nombre, score, minimoPartidas, minimoPuntos, promedioMinimo);
	}

	public Novato() {
		super();
		
		
	}

	@Override
	public void actualizar(Usuario usuario) throws CategoriaException {
		// En novato solo puede subir
		Calificado calificado = (Calificado) CategoriaDAO.getInstancia().buscarCategoriaByNombreNegocio("CALIFICADO");
		if (calificado.debeSer(usuario)) {
			usuario.actualizarCategoria(calificado);
		}		
	}

	@Override
	public boolean debeSer(Usuario usuario) {
		if (usuario.getPartidasJugadas() > this.getMinimoPartida() &&
				usuario.getPuntaje() > this.getMinimoPuntos() &&
				usuario.getPromedio() > this.getPromedioMinimo())
			return true;
		else
			return false;
	}
}
