package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dto.CartaDTO;
import dto.GrupoDTO;
import dto.JuegoDTO;
import dto.JugadorDTO;
import dto.UsuarioDTO;
import excepciones.CategoriaException;
import excepciones.GrupoException;
import excepciones.MiembroException;
import excepciones.UsuarioException;

public interface InterfaceRemotaTruco extends Remote {

	public UsuarioDTO login(String usuario, String pass) throws RemoteException;
	public void altaUsuario(String apodo, String email, String password) throws RemoteException;
	public void modificarUsario(String apodo, String password, String nuevoEmail, String nuevaPass, String nuevoApodo) throws RemoteException;
	public void agregarAListaEspera(UsuarioDTO usuario) throws RemoteException;
	public void armarPareja(UsuarioDTO u1, UsuarioDTO u2) throws RemoteException;
	public List<JuegoDTO> getJuegosActivo(UsuarioDTO usuario) throws RemoteException;
	public JuegoDTO getJuegosById(int idJuego) throws RemoteException ;
	public boolean esMiTurno(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException;
	public void jugarCarta(JuegoDTO juego, CartaDTO carta, UsuarioDTO usuario) throws RemoteException;	
	public List<CartaDTO> getCartas(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException;
	public void cantarEnvido(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException;
	public void cantarRealEnvido(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException;
	public void cantarFaltaEnvido(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException;
	public void quieroEnvido(JuegoDTO juego) throws RemoteException, MiembroException;
	public void noQuieroEnvido(JuegoDTO juego, UsuarioDTO us1) throws RemoteException, MiembroException;
	public void cantarTruco(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException;
	public void cantarReTruco(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException;
	public void cantarValeCuatro(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException;
	public void noQuieroTruco(JuegoDTO juego, UsuarioDTO us1) throws RemoteException, MiembroException;
	public String tengoQueContestar(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException;
	public boolean alguienTieneQueContestar(JuegoDTO juego) throws RemoteException;
	public void quieroTruco(JuegoDTO juego) throws RemoteException;
	public void altaGrupo(String nombre, UsuarioDTO administrador) throws RemoteException;
	public void agregarUsuarioAGrupo (UsuarioDTO user, GrupoDTO grupo) throws RemoteException;
	public void agregarParejaLibreAEspera(UsuarioDTO usuario1, UsuarioDTO usuario2) throws RemoteException;
}
