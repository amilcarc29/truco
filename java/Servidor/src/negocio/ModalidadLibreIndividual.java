package negocio;

import java.util.List;

import dao.JuegoDAO;
import dao.JugadorDAO;
import dao.UsuarioDAO;
import excepciones.CategoriaException;
import excepciones.MiembroException;
import excepciones.ParejaException;
import excepciones.UsuarioException;

public class ModalidadLibreIndividual extends Juego{

	public ModalidadLibreIndividual() {
		super();
	}

	public ModalidadLibreIndividual(List<Pareja> parejas) {
		super(parejas);
	}

	@Override
	public int calcularPuntos() {
		return 10;
	}

	public void save() throws ParejaException, CategoriaException, MiembroException {
		this.setId(JuegoDAO.getInstancia().guardarJuegoLibreIndividual(this));
	}

	@Override
	public void finalizarJuego() throws UsuarioException, CategoriaException, ParejaException, MiembroException {
		this.setGanador(this.obtenerGanador());
		Pareja ganadora = this.getGanador();
		for (Pareja p : this.getParejas()) {
			for (Jugador j : p.getJugadores()) {
				Usuario usuario = UsuarioDAO.getInstancia().obtenerUsuarioJuegoIndividual(j);
				if (ganadora.tieneJugador(j.getId())) {
					int puntos = this.calcularPuntos(usuario);
					usuario.actualizarPuntos(1, 1, puntos);
				}
				else {
					usuario.actualizarPuntos(0, 1, 0);
				}
			}
		}
		this.getUltimaMano().finalizarMano();
		JuegoDAO.getInstancia().finalizarJuego(this);
	}

	@Override
	public Categoria obtenerCategoriaMayor() {
		Categoria mayor = this.getPareja1().getCategoriaMayor();
		if (mayor.getScore() < this.getPareja2().getCategoriaMayor().getScore())
			mayor = this.getPareja2().getCategoriaMayor();
		return mayor;
	}

	@Override
	public int calcularPuntos(Usuario usuario) {
		int puntosAgregados = 0;
		if (esCategoriaInferior(usuario.getCategoria())) {
			puntosAgregados = 2;
		}
		return calcularPuntos() + puntosAgregados;
	}
	
	public boolean esCategoriaInferior(Categoria categoria) {
		Categoria mayor = this.obtenerCategoriaMayor();
		return (categoria.getScore() < mayor.getScore());
	}
}
