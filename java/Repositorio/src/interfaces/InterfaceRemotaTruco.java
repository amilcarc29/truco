package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import dto.UsuarioDTO;

public interface InterfaceRemotaTruco extends Remote {

	public UsuarioDTO login(String usuario, String pass)throws RemoteException;
	public void altaUsuario(String apodo, String email, String password)throws RemoteException;
	public void agregarAListaEspera(UsuarioDTO usuario)throws RemoteException;
	public void armarPareja(UsuarioDTO u1, UsuarioDTO u2)throws RemoteException;
}
