package negocio;

import java.util.List;
import excepciones.JuegoException;
import negocio.Pareja;

public class FactoryJuegos {

	public Juego getJuego(List<Pareja> parejas, String tipo) throws JuegoException {

		if (tipo.equals("LIBRE"))
			return new ModalidadLibreIndividual(parejas);

		if (tipo.equals("CERRADA"))
			return new ModalidadCerrada(parejas);

		if (tipo.equals("ENPAREJA"))
			return new ModalidadLibreEnPareja(parejas);

		throw new JuegoException("El tipo " + tipo + "no existe.");

	}
}
