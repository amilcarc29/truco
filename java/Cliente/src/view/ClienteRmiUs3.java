package view;

import java.util.List;

import javax.swing.JOptionPane;

import delegado.BusinessDelegateTruco;
import dto.CartaDTO;
import dto.JuegoDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;

public class ClienteRmiUs3 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UsuarioDTO us = new BusinessDelegateTruco().login("Lucas", "pepe");
			new BusinessDelegateTruco().agregarAListaEspera(us);
			
			while (us!=null) {
				System.out.println("UsuarioLogeado");

				List<JuegoDTO> juegos = new BusinessDelegateTruco().getJuegosActivo(us);
				for (JuegoDTO juegoDTO : juegos) {
					System.out.println(juegoDTO.getIdJuego());
					if (new BusinessDelegateTruco().esMiTurno(juegoDTO, us)) {
						System.out.println("turno de " + us.getApodo());

						List<CartaDTO> c = new BusinessDelegateTruco().getCartas(juegoDTO, us);
						for (CartaDTO cartaDTO : c) {
							System.out.println("palo " + cartaDTO.getPalo() + " numero " + cartaDTO.getNumero());
						}

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
