package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JOptionPane;

import delegado.BusinessDelegateTruco;
import dto.CartaDTO;
import dto.JuegoDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;

public class ClienteRmiUs4 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		try {
			UsuarioDTO us = new BusinessDelegateTruco().login("Amilcar", "pepe");
			new BusinessDelegateTruco().agregarAListaEspera(us);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			while (us != null) {
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

						System.out.println("carta numero: ");
						int cn = Integer.parseInt(br.readLine());
						System.out.println("carta palo: ");
						String cp = br.readLine();

						for (CartaDTO cartaDTO : c) {
							if ((cartaDTO.getNumero() == cn) && (cartaDTO.getPalo().equals(cp))) {
								new BusinessDelegateTruco().jugarCarta(juegoDTO, cartaDTO, us);
							}
						}

					} else {
						System.out.println("No es mi turno");
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
