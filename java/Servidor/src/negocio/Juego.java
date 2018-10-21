package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import controlador.ControladorUsuario;
import dao.JuegoDAO;
import dao.JugadorDAO;
import dao.ParejaDAO;
import dao.UsuarioDAO;
import dto.JuegoDTO;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.JugadorException;
import excepciones.MiembroException;
import excepciones.ParejaException;
import excepciones.UsuarioException;

public abstract class Juego {

	private int idJuego;
	private List<Pareja> parejas;
	private List<Chico> chicos;
	private Pareja ganador;
	private int puntoBase;
	private Date fecha;
	private boolean activo;

	public Juego(List<Pareja> parejas) {
		super();
		this.parejas = parejas;
		this.chicos = new ArrayList<>();
		setPuntoBase(0);
		this.fecha = new Date();
		setActivo(true);
	}

	public Juego() {
	}

	public int getId() {
		return this.idJuego;
	}

	public void setId(int id) {
		this.idJuego = id;
	}

	public abstract int calcularPuntos();

	public boolean sosJuego(Juego juego) {
		return (this.idJuego == juego.getId());
	}

	public Pareja getPareja1() {
		return this.parejas.get(0);
	}

	public Pareja getPareja2() {
		return this.parejas.get(1);
	}

	public List<Pareja> getParejas() {
		return parejas;
	}

	public void setParejas(List<Pareja> parejas) {
		this.parejas = parejas;
	}

	public Pareja getGanador() {
		return ganador;
	}

	public void setGanador(Pareja ganador) {
		this.ganador = ganador;
	}

	public int getPuntoBase() {
		return puntoBase;
	}

	public void setPuntoBase(int puntoBase) {
		this.puntoBase = puntoBase;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Pareja obtenerGanador() {
		return getUltimoChico().getParejaGanadora();
	}

	public void finalizarJuego() throws UsuarioException, CategoriaException, ParejaException, MiembroException {
		getParejas().forEach(pareja -> {
			// Pareja que no gano.
			if (!getGanador().esPareja(pareja.getIdPareja())) {
				pareja.getJugadores().forEach(jugador -> {
					try {
						Usuario usuario = UsuarioDAO.getInstancia().toNegocio(JugadorDAO.getInstancia().buscarJugadorById(jugador.getId()).getUsuario());
						actualizarPuntosUsuario(usuario, -1, 1, calcularPuntosSegunCategoria(usuario, false));
						ControladorUsuario.getInstancia().verificarCategoriaJugador(usuario.getApodo());
					} catch (UsuarioException e) {
						e.printStackTrace();
					} catch (CategoriaException e) {
						e.printStackTrace();
					}
				});
			} else {
				// Pareja que gano.
				pareja.getJugadores().forEach(jugador -> {
					try {
						Usuario usuario = UsuarioDAO.getInstancia().toNegocio(JugadorDAO.getInstancia().buscarJugadorById(jugador.getId()).getUsuario());
						actualizarPuntosUsuario(usuario, 1, 1, calcularPuntosSegunCategoria(usuario, true));
						ControladorUsuario.getInstancia().verificarCategoriaJugador(usuario.getApodo());
					} catch (UsuarioException e) {
						e.printStackTrace();
					} catch (CategoriaException e) {
						e.printStackTrace();
					}
				});
			}
		});
		setActivo(false);
		save();
	}

	private void actualizarPuntosUsuario(Usuario usuario, int partidasGanadas, int partidasJugadas, int puntaje) throws CategoriaException, UsuarioException {
		usuario.actualizarPartidasGanadas(partidasJugadas);
		usuario.actualizarPartidasJugadas(partidasJugadas);
		usuario.actualizarPuntaje(puntaje);
	}

	private int calcularPuntosSegunCategoria(Usuario usuario, boolean ganador) throws CategoriaException, UsuarioException {
		int puntosAgregados = 0;
		if (ganador && esCategoriaInferior(usuario.getCategoria().getNombre())) {
			puntosAgregados = 2;
		}
		return calcularPuntos() + puntosAgregados;
	}

	private boolean esCategoriaInferior(String nombreCategoria) throws CategoriaException, UsuarioException {
		switch (nombreCategoria) {
		case "NOVATO":
			return obtenerCategoriaMayor().getNombre().equalsIgnoreCase("CALIFICADO") ? true : false;
		case "CALIFICADO":
			return obtenerCategoriaMayor().getNombre().equalsIgnoreCase("EXPERTO") ? true : false;
		case "EXPERTO":
			return obtenerCategoriaMayor().getNombre().equalsIgnoreCase("MASTER") ? true : false;
		default:
			return false;
		}
	}

	public Categoria obtenerCategoriaMayor() throws CategoriaException, UsuarioException {
		if (getPareja1().obtenerMayorCategoria().getNombre().equalsIgnoreCase(getPareja2().obtenerMayorCategoria().getNombre())) {
			return getPareja1().obtenerMayorCategoria();
		}
		return getPareja2().obtenerMayorCategoria();
	}

	// TODO tener en cuenta el orden para cada mano
	public void crearChico() throws UsuarioException, CategoriaException, ParejaException {
		Chico chico = new Chico(parejas);
		chico.save(this);
		chico.altaMano(chico.getPuntosParaTerminar());
		chicos.add(chico);
	}

	// TODO Agregar a Diagrama.

	public void cantarTruco() throws CategoriaException {
		Jugador jugador = JugadorDAO.getInstancia().getJugadorConTurno(this);
		chicos.get(chicos.size() - 1).cantarTruco(jugador);
	}

	public void cantarReTruco() throws CategoriaException {
		Jugador jugador = JugadorDAO.getInstancia().getJugadorConTurno(this);
		chicos.get(chicos.size() - 1).cantarReTruco(jugador);
	}

	public void cantarVale4() throws CategoriaException {
		Jugador jugador = JugadorDAO.getInstancia().getJugadorConTurno(this);
		chicos.get(chicos.size() - 1).cantarVale4(jugador);
	}

	public void cantarQuieroEnvido(boolean quieroSiNo) throws CategoriaException {
		// TODO Auto-generated method stub

		Jugador jugador = JugadorDAO.getInstancia().getJugadorConTurno(this);

		chicos.get(chicos.size() - 1).cantarQuieroEnvido(jugador, quieroSiNo);
	}

	public void cantarQuieroTruco(boolean quieroSiNo) throws CategoriaException {
		// TODO Auto-generated method stub
		Jugador jugador = JugadorDAO.getInstancia().getJugadorConTurno(this);
		chicos.get(chicos.size() - 1).cantarQuieroTruco(jugador, quieroSiNo);
	}

	public void cantarEnvido() throws CategoriaException {
		Jugador jugador = JugadorDAO.getInstancia().getJugadorConTurno(this);
		chicos.get(chicos.size() - 1).cantarEnvido(jugador);
	}

	public void sinCantar() {
		// TODO Auto-generated method stub
		chicos.get(chicos.size() - 1).sinCantar();

	}

	public void jugarCarta(Carta carta) throws JugadorException, CartaException, UsuarioException, CategoriaException {

		// TODO ver si se pasa a baza
		Jugador jugador = JugadorDAO.getInstancia().getJugadorConTurno(this);

		chicos.get(chicos.size() - 1).jugarCarta(carta, jugador);

		JugadorDAO.getInstancia().setTurnoSigJugador(this);
	}

	public boolean sePuedeCantarEnvido() {
		return chicos.get(chicos.size() - 1).sePuedeCantarEnvido();
	}

	public boolean terminoJuego() {
		// Es al mejor de 3
		if (this.getChicos().size() >= 3)
			return true;
		else {
			Pareja pareja = this.getChicos().get(0).getGanador();
			Pareja pareja1 = this.getChicos().get(1).getGanador();
			if (pareja.getIdPareja() == pareja1.getIdPareja())
				return true;
		}
		return false;
	}

	public int getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

	public List<Chico> getChicos() {
		return chicos;
	}

	public void setChicos(List<Chico> chicos) {
		this.chicos = chicos;
	}


	public abstract void save() throws ParejaException, CategoriaException, MiembroException;

	public JuegoDTO toDTO() {
		JuegoDTO j = new JuegoDTO(this.idJuego);
		return j;
	}

	public void setChico(List<Chico> chicos) {
		this.chicos = chicos;
	}

	public List<Carta> getCartas(Jugador jug) {
		// TODO Auto-generated method stub
		return chicos.get(chicos.size() - 1).getCartas(jug);
	}

	public Mano getUltimaMano() {
	
		return  this.getUltimoChico().getUltimaMano();
	}
	public Baza getUltimaBaza() {
		
		return  this.getUltimaMano().getUltimaBaza();
	}
	
	public Chico getUltimoChico() {
		
		return chicos.get(chicos.size() - 1);
	}

	public void armarNuevoChico() throws UsuarioException, CategoriaException, ParejaException {
		// TODO Auto-generated method stub
		
		this.getUltimoChico().cambiarOrden();

		List<Jugador> jugadores = this.getUltimoChico().getJugadores();
		Pareja p1 = ParejaDAO.getInstancia().buscarParejaDeUnJugador(jugadores.get(0).getId());
		Pareja p2 = ParejaDAO.getInstancia().buscarParejaDeUnJugador(jugadores.get(1).getId());

		this.parejas  = new ArrayList<>();
		this.parejas.add(p1);
		this.parejas.add(p2);

		Chico chico = new Chico(parejas);
		chico.save(this);
		chico.altaMano(chico.getPuntosParaTerminar());
		chicos.add(chico);
	}
}
