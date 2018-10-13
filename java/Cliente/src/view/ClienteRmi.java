package view;

import javax.swing.JOptionPane;

import delegado.BusinessDelegate;
import delegado.BusinessDelegateTruco;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;

public class ClienteRmi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
//			usuarios.add(new Usuario("Emiliano", "Emiliano", "pepe"));
//			usuarios.add(new Usuario("Debi", "Debi", "pepe"));
//			usuarios.add(new Usuario("Lucas", "Lucas", "pepe"));
//			usuarios.add(new Usuario("Amilcar", "Amilcar", "pepe"));
//			
//			new BusinessDelegateTruco().alta("Emiliano", "Emiliano", "pepe");
//			new BusinessDelegateTruco().alta("Debi", "Debi", "pepe");
//			new BusinessDelegateTruco().alta("Lucas", "Lucas", "pepe");
//			new BusinessDelegateTruco().alta("Amilcar", "Amilcar", "pepe");

			
//			
			UsuarioDTO us = new BusinessDelegateTruco().login("Emiliano", "pepe");
			System.out.println(us.getApodo());
			// new BusinessDelegateTruco().alta("pepe", "pepe", "pepe");
			// new BusinessDelegateTruco().login("pepe", "pepe");
		} catch (ComunicacionException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
