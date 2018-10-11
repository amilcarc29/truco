package negocio;

public class Master extends Categoria{

	public Master(int idCategoria, String nombre, int score, int minimoPartida, int minimoPuntos, int promedioMinimo) {
		super(idCategoria, nombre, score, minimoPartida, minimoPuntos, promedioMinimo);
	}

	@Override
	public void actualizar() {
	}
}
