package test;

import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import servidor.ServidorTruco;

//SERVIDOR
public class RmiTruco {
	public static void main(String[] args) {
		try {
			new ServidorTruco();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "No pude arrancar el servidor");
		}
	}

}
