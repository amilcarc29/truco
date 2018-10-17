package view;

import java.util.List;

import javax.swing.JOptionPane;

import delegado.BusinessDelegateTruco;
import dto.CartaDTO;
import dto.JuegoDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;

public class ClienteRmiUs1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UsuarioDTO us1 = new BusinessDelegateTruco().login("Emiliano", "pepe");
			new BusinessDelegateTruco().agregarAListaEspera(us1);
			
			while (us1!=null) {
				System.out.println("UsuarioLogeado");
				List<JuegoDTO> juegos = new BusinessDelegateTruco().getJuegosActivo(us1);
				for (JuegoDTO juegoDTO : juegos) {
					System.out.println(juegoDTO.getIdJuego());
					if (new BusinessDelegateTruco().esMiTurno(juegoDTO, us1)) {
						System.out.println("turno de " + us1.getApodo());
					}else {
						System.out.println("No es mi turno");
					}	
					List<CartaDTO> c = new BusinessDelegateTruco().getCartas(juegoDTO, us1);
					for (CartaDTO cartaDTO : c) {
						System.out.println("palo " + cartaDTO.getPalo() + " numero " + cartaDTO.getNumero());
					}
					
				}
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		} catch (ComunicacionException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
