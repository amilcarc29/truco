package view;

import javax.swing.JOptionPane;

import delegado.BusinessDelegate;
import delegado.BusinessDelegateTruco;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;

public class ClienteRmiUs3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			UsuarioDTO us3 = new BusinessDelegateTruco().login("Lucas", "pepe");

			new BusinessDelegateTruco().agregarAListaEspera(us3);
			
			
			
			

		} catch (ComunicacionException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
