package negocio;

import dao.CategoriaDAO;
import excepciones.CategoriaException;

public class Calificado  extends Categoria {

	public Calificado(int idCategoria, String nombre, int score, int minimoPartida, int minimoPuntos,
			int promedioMinimo) {
		super(idCategoria, nombre, score, minimoPartida, minimoPuntos, promedioMinimo);
	}

	@Override
	public void actualizar(Usuario usuario) throws CategoriaException {
		// En calificado solo puede subir. No puede volver a novato
		Experto experto = (Experto) CategoriaDAO.getInstancia().buscarCategoriaByNombreNegocio("EXPERTO");
		if (experto.debeSer(usuario)) {
			usuario.actualizarCategoria(experto);
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
