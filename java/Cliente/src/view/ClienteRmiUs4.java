package view;

import javax.swing.JOptionPane;

import delegado.BusinessDelegate;
import delegado.BusinessDelegateTruco;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;

public class ClienteRmiUs4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			UsuarioDTO us4 = new BusinessDelegateTruco().login("Amilcar", "pepe");

			new BusinessDelegateTruco().agregarAListaEspera(us4);

		} catch (ComunicacionException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
