package negocio;

public class Calificado  extends Categoria {

	public Calificado(int idCategoria, String nombre, int score, int minimoPartida, int minimoPuntos,
			int promedioMinimo) {
		super(idCategoria, nombre, score, minimoPartida, minimoPuntos, promedioMinimo);
	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
	}
}
