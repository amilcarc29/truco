package negocio;

import excepciones.JuegoException;

public class FactoryJuegos {
	public Juego getJuego(String tipo) throws JuegoException {

		if (tipo.equals("LIBRE"))
			return new ModalidadLibreIndividual();

		if (tipo.equals("CERRADA"))
			return new ModalidadCerrada();

		if (tipo.equals("ENPAREJA"))
			return new ModalidadLibreEnPareja();

		throw new JuegoException("El tipo " + tipo + "no existe.");

	}
}
