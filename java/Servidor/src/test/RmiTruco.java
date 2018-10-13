package test;

import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import excepciones.CartaException;
import excepciones.JuegoException;
import excepciones.JugadorException;
import servidor.Server;
import servidor.ServidorTruco;

public class RmiTruco {
	public static void main(String[] args) {
		try {
			new ServidorTruco();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "No pude arrancar el servidor");
		}
	}

}
