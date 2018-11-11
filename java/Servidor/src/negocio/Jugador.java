package negocio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dao.JugadorCartaDAO;
import dao.JugadorDAO;
import dto.CartaDTO;
import dto.JugadorDTO;
import entities.Tanto;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.UsuarioException;

public abstract class Jugador {
	// sacado nombre. Nombre es atributo de Usuario no de Jugador.
	private int idJugador;
	private List<Carta> cartas;
	private boolean tieneTurno;
	private String apodo;

	private int orden;

	private String tanto;

	// private static int cnt = 0;
	//
	// private static int getID() {
	// return cnt++;
	// }

	public Jugador() {
		super();
		// TODO levantar el id de la bd.
		// id autogenerado en la BD
		// this.idJugador = 0;
		cartas = new LinkedList<>();
	}

	// public Jugador() {
	// super();
	// idJugador = getID();
	// cartas = new LinkedList<Carta>();
	// }
	public void setId(int idjugador) {
		this.idJugador = idjugador;
	}

	public int getId() {
		return this.idJugador;
	}

	public boolean esJugador(int idjugador) {

		return (idJugador == idjugador);
	}

	public boolean tieneCartas() {

		return (cartas.size() > 0);
	}

	public void actualizarTurno() throws UsuarioException, CategoriaException {
		JugadorDAO.getInstancia().actualizarTurno(this);
	}

	public Carta getCarta(int numero, String palo) throws CartaException {

		for (Carta carta : cartas) {
			if (carta.esCarta(numero, palo)) {
				this.cartas.remove(carta);
				return carta;
			}
		}

		throw new CartaException("No se encontró a la carta: " + numero + " de " + palo);
	}

	public void dbgCartas() {
		for (Carta carta : cartas) {
			System.out.println("Carta " + carta.getNumero() + " " + carta.getPalo());
		}
	}

	public void setCartas(List<Carta> cartas) {
		// TODO Auto-generated method stub
		this.cartas = cartas;
	}

	public void guardarCartas(List<Carta> cartas) throws UsuarioException, CategoriaException, CartaException {
		this.setCartas(cartas);
		JugadorCartaDAO.getInstancia().guardarCartas(this.cartas, this);

	}

	// calcula cuanto tiene de envido un jugador
	public int getTantoEnvido() {
		// TODO Auto-generated method stub

		String palo = cartas.get(0).getPalo();
		int rep = 0;
		int mayotenvido = 0;
		int envido = cartas.get(0).getPesoEnvido();

		for (int i = 1; i < cartas.size(); i++) {

			if (palo.equals(cartas.get(i).getPalo())) {
				rep++;
				envido += cartas.get(i).getPesoEnvido();
			}

			if (mayotenvido < cartas.get(i).getPesoEnvido())
				mayotenvido = cartas.get(i).getPesoEnvido();
		}

		if (rep == 2) {
			if (cartas.get(1).getPesoEnvido() < cartas.get(2).getPesoEnvido()) {
				envido += cartas.get(2).getPesoEnvido();
			} else {
				envido += cartas.get(1).getPesoEnvido();

			}
		}

		if (rep == 0) {
			return mayotenvido;
		} else if (rep == 1) {
			return envido + 20;
		} else if (rep == 2)
			return envido + 20;

		return 0;
	}

	public int getTantoTruco() {
		// TODO Auto-generated method stub

		int truco = cartas.get(0).getPesoTruco();

		for (int i = 1; i < cartas.size(); i++) {
			if (truco < cartas.get(i).getPesoTruco())
				truco = cartas.get(i).getPesoTruco();
		}

		return truco;
	}

	public List<Carta> getCartasMismoPalo() {
		List<Carta> resultado = new LinkedList<Carta>();
		for (int i = 0; i < 3; i++) {
			for (int j = i + 1; j < 2; j++) {
				if (this.cartas.get(i).getPalo().equalsIgnoreCase(this.cartas.get(j).getPalo()))
					if (this.cantidadElementos(resultado) == 0) {
						resultado.add(this.cartas.get(i));
						resultado.add(this.cartas.get(j));
					} else {
						resultado.add(this.cartas.get(j));
					}
			}
		}
		if (this.cantidadElementos(resultado) > 2)
			resultado.remove(this.obtenerCartaMenorPuntajeEnvido(resultado));
		return resultado;
	}

	public int obtenerCartaMenorPuntajeEnvido(List<Carta> lista) {
		int pesoEnvido = 100;
		for (Carta carta : lista) {
			if (carta.getPesoEnvido() < pesoEnvido)
				pesoEnvido = carta.getPesoEnvido();
		}
		for (Carta carta : lista)
			if (carta.getPesoEnvido() == pesoEnvido)
				return lista.indexOf(carta);
		return 0;
	}

	public int obtenerMayorPuntajeEnvido(List<Carta> lista) {
		int pesoEnvido = 0;
		for (Carta carta : lista) {
			if (carta.getPesoEnvido() > pesoEnvido)
				pesoEnvido = carta.getPesoEnvido();
		}
		return pesoEnvido;
	}

	public int cantidadElementos(List<Carta> lista) {
		// VER SI ARRANCA EN 0 o en 1
		int i = 0;
		for (Carta carta : lista)
			i++;
		return i;
	}

	public int obtenerPuntosEnvido() {
		int puntos = 0;
		List<Carta> cartas = this.getCartasMismoPalo();
		if (cartas.isEmpty()) {
			puntos = this.obtenerMayorPuntajeEnvido(this.cartas);
		} else {
			puntos = 20;
			for (Carta carta : cartas) {
				puntos += carta.getPesoEnvido();
			}
		}
		return puntos;
	}

	public List<Carta> getCartas() {
		return this.cartas;

	}

	public void setTieneTurno(boolean tieneTurno) {
		this.tieneTurno = tieneTurno;
	}

	public JugadorDTO toDTO() {
		// TODO Auto-generated method stub
		JugadorDTO j = new JugadorDTO(this.idJugador);
		j.setApodo(this.apodo);
		List<Carta> c = JugadorCartaDAO.getInstancia().getCartasbyJugador(this, true);
		List<CartaDTO> cdto = new ArrayList<>();

		for (Carta carta : c) {
			cdto.add(carta.toDTO());
		}
		j.setCartas(cdto);

		return j;
	}

	public boolean isTieneTurno() {
		return tieneTurno;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public String getTanto() {
		return tanto;
	}

	public void setTanto(String tanto) {
		this.tanto = tanto;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

}
