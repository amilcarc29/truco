package negocio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dao.BazaDAO;
import dao.JugadorCartaDAO;
import dto.BazaDTO;
import dto.JugadorDTO;
import excepciones.CategoriaException;
import excepciones.UsuarioException;

public class Baza {
	
	private int idBaza;
	private List<Jugador> jugadores;
	private List<Jugada> jugadas;
	private Jugada jugadaMayor;

	private boolean parda;


	public Baza(List<Jugador> jugadores) {
		super();
		this.jugadores = jugadores;
		jugadas = new LinkedList<Jugada>();
		jugadaMayor = null;
		parda = false;
		
	
	}



	public Jugada jugadaMayor() {
		return this.jugadaMayor;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public List<Jugada> getJugadas() {
		return jugadas;
	}

	public void setJugadas(List<Jugada> jugadas) {
		this.jugadas = jugadas;
	}

	public boolean isParda() {
		return parda;
	}

	public void setParda(boolean parda) {
		this.parda = parda;
	}



	public boolean finalizoBaza() {
		// TODO cuenta desde 0 las jugadas en la jugada simple son 4 maximo
		if (parda)
			return (this.jugadas.size() >= 3);
		return (this.jugadas.size() == 4);
	}

	public void finalizarBaza() {
	}

	// sacado el return. PARA QUE QUEREMOS QUE RETORNE EL ID? (Puede que lo usaban para algo y no se)
	public void save(Mano mano) {
		this.setIdBaza(BazaDAO.getInstancia().guardarBaza(mano));
	}

	public void jugarCarta(Carta carta, Jugador jugador) throws UsuarioException, CategoriaException {
		try { 

			JugadorCartaDAO.getInstancia().guardarCartaJugada(jugador.getId(), carta.getIdCarta());

			Jugada jugada = new Jugada(jugador, carta);
			jugada.save(this);
			this.jugadas.add(jugada);
			
		} catch (UsuarioException e) {
			e.printStackTrace();
		} catch (CategoriaException e1) {
			e1.printStackTrace();
		}
	}
	
	

	public boolean terminoBaza() {
		if (this.getJugadas().size() == 4)
			return true;
		else
			return false;
	}
	


	// FUNCION VIEJA DE JUGAR CARTA
//	public void jugarCarta(int indiceJugador, int numero, String palo) throws JugadorException, CartaException {
//		Jugador jugador = jugadores.get(indiceJugador);
//		// solo para debug
//		if (jugadores.size() > (indiceJugador + 1)) {
//			Jugador nextj = jugadores.get(indiceJugador + 1);
//		}
//		// solo para debug
//		Carta c = jugador.getCarta(numero, palo);
//		Jugada jugada = new Jugada();
//		jugada.setJugador(jugador);
//		jugada.setCarta(c);
//		this.jugadas.add(jugada);
//		if (jugadaMayor == null)
//			jugadaMayor = jugada;
//		else {
//			if (this.jugadaMayor.esMayor(jugada))
//				this.jugadaMayor = jugada;
			// Conflicto con nombre de Jugador
			// System.out.println("jugada mayor " +
			// jugadores.get(this.jugadaMayor.getJugador().getId()).getNombre() + " , "
			// + this.jugadaMayor.getCarta().getNumero() + " " +
			// this.jugadaMayor.getCarta().getPalo());
//		}
//		numero++;
//	}

	public int getIdBaza() {
		return idBaza;
	}

	public void setIdBaza(int idBaza) {
		this.idBaza = idBaza;
	}

	public Jugada getJugadaMayor() {
		return jugadaMayor;
	}

	public void setJugadaMayor(Jugada jugadaMayor) {
		this.jugadaMayor = jugadaMayor;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}
	
	public BazaDTO toDTO() {
		// TODO Auto-generated method stub
		List<JugadorDTO> jugDTO= new ArrayList<>();
		for(Jugador j: jugadores){
			jugDTO.add(j.toDTO());
		}
		
		// NO CREO QUE SEA NECESARIO PASARLE LAS JUGADAS
		//List<JugadaDTO> jugadaDTO= new ArrayList<>();
		//for(Jugada jugada: jugadas){
		//	jugDTO.add(jugada.toDTO());
		//}
		//private Jugada jugadaMayor;

		
		
		return new BazaDTO(idBaza,jugDTO,parda);
	}


}
