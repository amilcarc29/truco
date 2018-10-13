package negocio;

public class Novato extends Categoria {

	public Novato(int idCategoria, String nombre, int score, int minimoPartidas, int minimoPuntos, int promedioMinimo) {
		super(idCategoria, nombre, score, minimoPartidas, minimoPuntos, promedioMinimo);
	}

	public Novato() {
		super();
		
		
	}

	@Override
	public void actualizar() {
	}
}
