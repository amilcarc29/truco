package view;

import javax.swing.JOptionPane;

import delegado.BusinessDelegate;
import delegado.BusinessDelegateTruco;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;

public class ClienteRmiUs1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			UsuarioDTO us1 = new BusinessDelegateTruco().login("Emiliano", "pepe");

			new BusinessDelegateTruco().agregarAListaEspera(us1);
			
			
			
			

		} catch (ComunicacionException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
