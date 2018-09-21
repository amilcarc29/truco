package negocio;

public class FactoryJuegos {
	public Juego getJuego(String tipo) {

		if (tipo.equals("LIBRE"))
			return new ModalidadLibreIndividual();

		if (tipo.equals("CERRADA"))
			return new ModalidadCerrada();

		if (tipo.equals("ENPAREJA"))
			return new ModalidadLibreEnPareja();

		return null;

	}
}
