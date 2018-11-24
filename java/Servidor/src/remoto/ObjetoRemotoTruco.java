package remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import controlador.ControladorArmadoJuegos;
import controlador.ControladorGrupo;
import controlador.ControladorJuego;
import controlador.ControladorUsuario;
import dto.CartaDTO;
import dto.GrupoDTO;
import dto.JuegoDTO;
import dto.ParejaDTO;
import dto.UsuarioDTO;
import excepciones.BazaException;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.ChicoException;
import excepciones.ErrorCode;
import excepciones.GrupoException;
import excepciones.JuegoException;
import excepciones.JugadaException;
import excepciones.JugadorException;
import excepciones.ManoException;
import excepciones.MiembroException;
import excepciones.ParejaException;
import excepciones.UsuarioException;
import interfaces.InterfaceRemotaTruco;

public class ObjetoRemotoTruco extends UnicastRemoteObject implements InterfaceRemotaTruco {

	private static final long serialVersionUID = 7432516509801597745L;

	public ObjetoRemotoTruco() throws RemoteException {
	}

	@Override
	public UsuarioDTO login(String apodo, String password) throws RemoteException {
		UsuarioDTO us = null;
		try {
			us = ControladorUsuario.getInstancia().loggearUsuario(apodo, password);
		} catch (UsuarioException e) {
			ErrorCode usuario = ErrorCode.USUARIO_NO_ENCONTRADO;
			usuario.setDescripcion(e.getMessage());
			System.out.println(usuario.toString());
		} catch (CategoriaException e) {
			ErrorCode categoria = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			categoria.setDescripcion(e.getMessage());
			System.out.println(categoria.toString());
		}
		return us;
	}

	@Override
	public void altaUsuario(String apodo, String email, String password) throws RemoteException {
		try {
			ControladorUsuario.getInstancia().altaUsuario(apodo, email, password);
		} catch (UsuarioException e) {
			ErrorCode usuario = ErrorCode.USUARIO_NO_ENCONTRADO;
			usuario.setDescripcion(e.getMessage());
			System.out.println(usuario.toString());
		} catch (CategoriaException e) {
			ErrorCode categoria = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			categoria.setDescripcion(e.getMessage());
			System.out.println(categoria.toString());
		}
	}

	@Override
	public void agregarAListaEspera(UsuarioDTO usuario) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ControladorArmadoJuegos.getInstancia().agregarJugadorLibreAEspera(usuario);
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
	}

	public void armarPareja(UsuarioDTO u1, UsuarioDTO u2) throws RemoteException {
		try {
			ControladorArmadoJuegos.getInstancia().armarPareja(u1, u2);
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
	}

	@Override
	public List<JuegoDTO> getJuegosActivo(UsuarioDTO usuario) throws RemoteException {
		try {
			return ControladorArmadoJuegos.getInstancia().getJuegosActivo(usuario);
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
		// Devuelve una lista vacia en lugar de null
		return new ArrayList<JuegoDTO>();
	}

	@Override
	public JuegoDTO getJuegosById(int idJuego) throws RemoteException {
		try {
			return ControladorJuego.getInstancia().buscarJuego(idJuego).toDTO();
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (JuegoException e) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
		return null;
	}

	@Override
	public boolean esMiTurno(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			return ControladorJuego.getInstancia().turnoJugador(juego, usuario);
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (JuegoException e) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
		return false;
	}

	@Override
	public List<CartaDTO> getCartas(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			return ControladorJuego.getInstancia().getCartas(juego, usuario, false);
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (JuegoException e) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
		return null;
	}

	@Override
	public void cantarEnvido(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			ControladorJuego.getInstancia().cantarEnvido(juego, usuario);
		} catch (JuegoException e) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ManoException e) {
			ErrorCode error = ErrorCode.MANO_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
	}

	@Override
	public void cantarRealEnvido(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			ControladorJuego.getInstancia().cantarRealEnvido(juego, usuario);
		} catch (JuegoException e) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ManoException e) {
			ErrorCode error = ErrorCode.MANO_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
	}

	@Override
	public void cantarFaltaEnvido(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			ControladorJuego.getInstancia().cantarFaltaEnvido(juego, usuario);
		} catch (JuegoException e) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ManoException e) {
			ErrorCode error = ErrorCode.MANO_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
	}

	public void quieroEnvido(JuegoDTO juego) throws RemoteException {
		try {
			ControladorJuego.getInstancia().quieroEnvido(juego);
		} catch (JuegoException e2) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e2.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e3) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e3.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e4) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e4.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ManoException e) {
			ErrorCode error = ErrorCode.MANO_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (CartaException e) {
			ErrorCode error = ErrorCode.CARTA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (MiembroException e) {
			ErrorCode error = ErrorCode.MIEMBRO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ChicoException e) {
			ErrorCode error = ErrorCode.CHICO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
	}

	public void noQuieroEnvido(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			ControladorJuego.getInstancia().noQuieroEnvido(juego, usuario);
		} catch (JuegoException e2) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e2.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e3) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e3.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e4) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e4.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ManoException e) {
			ErrorCode error = ErrorCode.MANO_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (CartaException e) {
			ErrorCode error = ErrorCode.CARTA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (MiembroException e) {
			ErrorCode error = ErrorCode.MIEMBRO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ChicoException e) {
			ErrorCode error = ErrorCode.CHICO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
	}

	@Override
	public void jugarCarta(JuegoDTO juego, CartaDTO carta, UsuarioDTO usuario) throws RemoteException {
		try {
			ControladorJuego.getInstancia().jugarCarta(juego, carta, usuario);
		} catch (JugadorException e) {
			ErrorCode error = ErrorCode.JUGADOR_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (CartaException e1) {
			ErrorCode error = ErrorCode.CARTA_NO_ENCONTRADA;
			error.setDescripcion(e1.getMessage());
			System.out.println(error.toString());
		} catch (JuegoException e2) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e2.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e3) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e3.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e4) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e4.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (MiembroException e) {
			ErrorCode error = ErrorCode.MIEMBRO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ManoException e) {
			ErrorCode error = ErrorCode.MANO_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (BazaException e) {
			ErrorCode error = ErrorCode.BAZA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ChicoException e) {
			ErrorCode error = ErrorCode.CHICO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (JugadaException e) {
			ErrorCode error = ErrorCode.JUGADA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
	}

	@Override
	public void modificarUsario(String apodo, String password, String nuevoEmail, String nuevaPass, String nuevoApodo)
			throws RemoteException {

		try {
			ControladorUsuario.getInstancia().modificarUsuario(apodo, password, nuevoEmail, nuevaPass, nuevoApodo);
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}

	}

	@Override
	public void cantarTruco(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			ControladorJuego.getInstancia().cantarTruco(juego, usuario);
		} catch (JuegoException e2) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e2.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e3) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e3.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e4) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e4.getMessage());
			System.out.println(error.toString());
		} catch (ManoException e) {
			ErrorCode error = ErrorCode.MANO_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}

	}

	@Override
	public void cantarReTruco(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			ControladorJuego.getInstancia().cantarReTruco(juego, usuario);
		} catch (JuegoException e2) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e2.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e3) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e3.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e4) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e4.getMessage());
			System.out.println(error.toString());
		} catch (ManoException e) {
			ErrorCode error = ErrorCode.MANO_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}

	}

	@Override
	public void cantarValeCuatro(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			ControladorJuego.getInstancia().cantarVale4(juego, usuario);
		} catch (JuegoException e2) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e2.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e3) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e3.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e4) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e4.getMessage());
			System.out.println(error.toString());
		} catch (ManoException e) {
			ErrorCode error = ErrorCode.MANO_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}

	}

	@Override
	public void quieroTruco(JuegoDTO juego) throws RemoteException {
		try {
			ControladorJuego.getInstancia().quieroTruco(juego);
		} catch (JuegoException e2) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e2.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e3) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e3.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e4) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e4.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
	}

	@Override
	public void noQuieroTruco(JuegoDTO juego, UsuarioDTO us1) throws RemoteException {
		try {
			ControladorJuego.getInstancia().noQuieroTruco(us1, juego);
		} catch (JuegoException e2) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e2.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e3) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e3.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e4) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e4.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ManoException e) {
			ErrorCode error = ErrorCode.MANO_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (CartaException e) {
			ErrorCode error = ErrorCode.CARTA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (MiembroException e) {
			ErrorCode error = ErrorCode.MIEMBRO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ChicoException e) {
			ErrorCode error = ErrorCode.CHICO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}

	}

	@Override
	public String tengoQueContestar(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			return ControladorJuego.getInstancia().responderJugador(juego, usuario);
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (JuegoException e) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
		return null;
	}

	@Override
	public boolean alguienTieneQueContestar(JuegoDTO juego) throws RemoteException {
		try {
			return ControladorJuego.getInstancia().alguienTieneQueContestar(juego);
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (JuegoException e) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
		return false;
	}

	@Override
	public void altaGrupo(String nombre, UsuarioDTO administrador) throws RemoteException {
		try {
			ControladorGrupo.getInstancia().altaGrupo(nombre, administrador);
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (GrupoException e) {
			// Falta errores para Grupo
			e.printStackTrace();
		}		
	}

	@Override
	public void agregarUsuarioAGrupo(UsuarioDTO user, GrupoDTO grupo) throws RemoteException {
		try {
			ControladorGrupo.getInstancia().agregarUsuarioAGrupo(user, grupo);
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (MiembroException e) {
			ErrorCode error = ErrorCode.MIEMBRO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (GrupoException e) {
			// Falta errores para Grupo
			e.printStackTrace();
		}		
	}

	@Override
	public ParejaDTO agregarParejaLibreAEspera(UsuarioDTO usuario1, UsuarioDTO usuario2) throws RemoteException {
		ParejaDTO par = null;
		try {
			par = ControladorArmadoJuegos.getInstancia().agregarParejaLibreAEspera(usuario1, usuario2);
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
		return par;
	}

	@Override
	public void cancelarEsperaJugador(UsuarioDTO usuario) throws RemoteException {
		try {
			ControladorArmadoJuegos.getInstancia().cancelarEsperaJugador(usuario);
		} catch (JugadorException e) {
			ErrorCode error = ErrorCode.JUGADOR_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}		
	}

	@Override
	public void cancelarEsperaPareja(ParejaDTO pareja) throws RemoteException {
		try {
			ControladorArmadoJuegos.getInstancia().cancelarEsperaPareja(pareja);
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}		
	}

	@Override
	public void salirJuego(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			ControladorJuego.getInstancia().salirJuego(juego, usuario);
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (JuegoException e) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (UsuarioException e) {
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ChicoException e) {
			ErrorCode error = ErrorCode.CHICO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (MiembroException e) {
			ErrorCode error = ErrorCode.MIEMBRO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
		
	}

	@Override
	public List<UsuarioDTO> usuariosLibres() throws RemoteException {
	
		
		return 	ControladorUsuario.getInstancia().listarUsuarios();
	}	
	
}
