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

public class ClienteRmiUs1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		try {
			UsuarioDTO us1 = new BusinessDelegateTruco().login("Emiliano", "pepe");
			new BusinessDelegateTruco().agregarAListaEspera(us1);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			while (us1 != null) {
				System.out.println("UsuarioLogeado");
				List<JuegoDTO> juegos = new BusinessDelegateTruco().getJuegosActivo(us1);
				for (JuegoDTO juegoDTO : juegos) {
					System.out.println(juegoDTO.getIdJuego());
					if (new BusinessDelegateTruco().alguienTieneQueContestar(juegoDTO)) {
						if (new BusinessDelegateTruco().tengoQueContestar(juegoDTO, us1)) {
							String con = null;
							System.out.println("que contestas  ?: ");
							con = br.readLine();
														
							if (con.equals("env"))
								new BusinessDelegateTruco().cantarEnvido(juegoDTO, us1);
							if (con.equals("real"))
								new BusinessDelegateTruco().cantarRealEnvido(juegoDTO, us1);
							if (con.equals("falta"))
								new BusinessDelegateTruco().cantarFaltaEnvido(juegoDTO, us1);
							if (con.equals("qe"))
								new BusinessDelegateTruco().quieroEnvido(juegoDTO);
							if (con.equals("nqe"))
								new BusinessDelegateTruco().noQuieroEnvido(juegoDTO, us1);
							if (con.equals("truco"))
								new BusinessDelegateTruco().cantarTruco(juegoDTO, us1);
							if (con.equals("retruco"))
								new BusinessDelegateTruco().cantarReTruco(juegoDTO, us1);
							if (con.equals("valec"))
								new BusinessDelegateTruco().cantarValeCuatro(juegoDTO, us1);
							if (con.equals("nqt"))
								new BusinessDelegateTruco().noQuieroTruco(juegoDTO, us1);
							if (con.equals("qt"))
								new BusinessDelegateTruco().quieroTruco(juegoDTO);
						}
					} else {
						if (new BusinessDelegateTruco().esMiTurno(juegoDTO, us1)) {
							System.out.println("turno de " + us1.getApodo());

							List<CartaDTO> c = new BusinessDelegateTruco().getCartas(juegoDTO, us1);
							for (CartaDTO cartaDTO : c) {
								System.out.println("palo " + cartaDTO.getPalo() + " numero " + cartaDTO.getNumero());
							}
							String jug = null;
							System.out.println("que desea hacer  ?: ");
							jug = br.readLine();

							if (jug.equals("env"))
								new BusinessDelegateTruco().cantarEnvido(juegoDTO, us1);
							if (jug.equals("real"))
								new BusinessDelegateTruco().cantarRealEnvido(juegoDTO, us1);
							if (jug.equals("falta"))
								new BusinessDelegateTruco().cantarFaltaEnvido(juegoDTO, us1);
							if (jug.equals("qe"))
								new BusinessDelegateTruco().quieroEnvido(juegoDTO);
							if (jug.equals("nqe"))
								new BusinessDelegateTruco().noQuieroEnvido(juegoDTO, us1);
							if (jug.equals("truco"))
								new BusinessDelegateTruco().cantarTruco(juegoDTO, us1);
							if (jug.equals("retruco"))
								new BusinessDelegateTruco().cantarReTruco(juegoDTO, us1);
							if (jug.equals("valec"))
								new BusinessDelegateTruco().cantarValeCuatro(juegoDTO, us1);
							if (jug.equals("nqt"))
								new BusinessDelegateTruco().noQuieroTruco(juegoDTO, us1);
							if (jug.equals("qt"))
								new BusinessDelegateTruco().quieroTruco(juegoDTO);

							if (jug.equals("jug")) {
								System.out.println("carta numero: ");
								int cn = Integer.parseInt(br.readLine());
								System.out.println("carta palo: ");
								String cp = br.readLine();

								for (CartaDTO cartaDTO : c) {
									if ((cartaDTO.getNumero() == cn) && (cartaDTO.getPalo().equals(cp))) {
										new BusinessDelegateTruco().jugarCarta(juegoDTO, cartaDTO, us1);
									}
								}
							}

						} else {
							System.out.println("No es mi turno");
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
