package servidor;

import java.util.LinkedList;
import java.util.List;

import negocio.FactoryJuegos;
import negocio.GrupoJuego;
import negocio.Juego;

public class ControladorJuego {

	private List<Juego> juegos;
	private static FactoryJuegos fcJuegos;

	public ControladorJuego() {
		juegos = new LinkedList<Juego>();
		fcJuegos = new FactoryJuegos();

	}

	public void iniciarJuego(GrupoJuego grupo) {
		Juego j = fcJuegos.getJuego(grupo.getTipoJuego());
		if (j != null)
			juegos.add(j);
	}

}
