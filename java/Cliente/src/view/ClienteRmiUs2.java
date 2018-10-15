package view;

import javax.swing.JOptionPane;

import delegado.BusinessDelegateTruco;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;

public class ClienteRmiUs2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UsuarioDTO us2 = new BusinessDelegateTruco().login("Debi", "pepe");
			new BusinessDelegateTruco().agregarAListaEspera(us2);
		} catch (ComunicacionException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
