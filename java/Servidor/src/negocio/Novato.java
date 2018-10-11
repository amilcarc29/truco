package negocio;

public class Novato extends Categoria{

	public Novato(int idCategoria, String nombre, int score, int minimoPartida, int minimoPuntos, int promedioMinimo) {
		super(idCategoria, nombre, score, minimoPartida, minimoPuntos, promedioMinimo);
	}

	@Override
	public void actualizar() {
	}
}
