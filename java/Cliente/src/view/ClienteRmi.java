package view;

import java.util.List;

import javax.swing.JOptionPane;

import delegado.BusinessDelegateTruco;
import dto.CartaDTO;
import dto.JuegoDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;

public class ClienteRmi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// usuarios.add(new Usuario("Emiliano", "Emiliano", "pepe"));
			// usuarios.add(new Usuario("Debi", "Debi", "pepe"));
			// usuarios.add(new Usuario("Lucas", "Lucas", "pepe"));
			// usuarios.add(new Usuario("Amilcar", "Amilcar", "pepe"));


			//
//			new BusinessDelegateTruco().alta("Emiliano", "pepe", "pepe");
//			new BusinessDelegateTruco().alta("Debi", "pepe", "pepe");
//			new BusinessDelegateTruco().alta("Lucas", "pepe", "pepe");
//			new BusinessDelegateTruco().alta("Amilcar", "pepe", "pepe");
//
			
			UsuarioDTO us1 = new BusinessDelegateTruco().login("Emiliano", "pepe");
			UsuarioDTO us2 = new BusinessDelegateTruco().login("Debi", "pepe");
			UsuarioDTO us3 = new BusinessDelegateTruco().login("Lucas", "pepe");
			UsuarioDTO us4 = new BusinessDelegateTruco().login("Amilcar", "pepe");
			
			new BusinessDelegateTruco().agregarAListaEspera(us1);
			new BusinessDelegateTruco().agregarAListaEspera(us2);
			new BusinessDelegateTruco().agregarAListaEspera(us3);
			new BusinessDelegateTruco().agregarAListaEspera(us4);
////
//			
////
//			while (true) {
//				List<JuegoDTO> juegos = new BusinessDelegateTruco().getJuegosActivo(us1);
//				for (JuegoDTO juegoDTO : juegos) {
//					System.out.println(juegoDTO.getIdJuego());
//					if (new BusinessDelegateTruco().esMiTurno(juegoDTO, us1)) {
//						System.out.println("turno de " + us1.getApodo());
//						
//						List<CartaDTO> c = new BusinessDelegateTruco().getCartas(juegoDTO, us1);
//						for (CartaDTO cartaDTO : c) {
//							System.out.println("palo " + cartaDTO.getPalo() + " numero " + cartaDTO.getNumero());
//						}
//						
//					}
//				}
//				try {
//					Thread.sleep(6000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}

			// new BusinessDelegateTruco().armarPareja(us1, us2);

			// new BusinessDelegateTruco().alta("pepe", "pepe", "pepe");
			// new BusinessDelegateTruco().login("pepe", "pepe");
		} catch (ComunicacionException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
