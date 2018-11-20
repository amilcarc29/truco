package negocio;

import dao.CategoriaDAO;
import excepciones.CategoriaException;
import excepciones.UsuarioException;

public class Novato extends Categoria {

	
	public Novato() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Novato(int idCategoria, String nombre, int score, int minimoPartida, int minimoPuntos, int promedioMinimo) {
		super(idCategoria, nombre, score, minimoPartida, minimoPuntos, promedioMinimo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actualizar(Usuario usuario) throws CategoriaException, UsuarioException {
		// En novato solo puede subir
		Calificado calificado = (Calificado) CategoriaDAO.getInstancia().buscarCategoriaByNombreNegocio("CALIFICADO");
		if (calificado.debeSer(usuario)) {
			usuario.actualizarCategoria(calificado);
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
