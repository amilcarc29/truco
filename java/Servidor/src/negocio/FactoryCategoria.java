package negocio;

import excepciones.CategoriaException;

public class FactoryCategoria {

	public static Categoria getCategoria(int idCategoria, String nombre, int score, int minimoPartida,
			int minimoPuntos, int promedioMinimo) throws CategoriaException {
		switch (nombre) {
		case "NOVATO":
			return new Novato(idCategoria, nombre, score,  minimoPartida, minimoPuntos, promedioMinimo);
		case "CALIFICADO":
			return new Calificado(idCategoria, nombre, score,  minimoPartida, minimoPuntos, promedioMinimo);
		case "MASTER":
			return new Master(idCategoria, nombre, score, minimoPartida, minimoPuntos, promedioMinimo);
		case "EXPERTO":
			return new Experto(idCategoria, nombre, score, minimoPartida, minimoPuntos, promedioMinimo);
		default:
			throw new CategoriaException("El tipo " + nombre + "no existe.");
		}
	}
}
