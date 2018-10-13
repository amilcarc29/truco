package dao;

import excepciones.JugadorException;
import negocio.Jugador;

public class JugadorDAO {

	private static JugadorDAO instancia;

	public JugadorDAO() {
	}

	public static JugadorDAO getInstancia() {
		if (instancia == null) {
			instancia = new JugadorDAO();
		}
		return instancia;
	}

	public Jugador buscarJugadorById(int idJugador) throws JugadorException {
		return null;
	}
}
