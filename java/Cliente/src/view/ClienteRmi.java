package view;

import javax.swing.JOptionPane;

import delegado.BusinessDelegate;
import delegado.BusinessDelegateTruco;
import excepciones.ComunicacionException;

public class ClienteRmi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new BusinessDelegateTruco().login("pepe","pepe");
		} catch (ComunicacionException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
