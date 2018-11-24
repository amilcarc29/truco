package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import delegado.BusinessDelegateTruco;
import dto.CartaDTO;
import dto.JuegoDTO;
import dto.JugadorDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;
import excepciones.ErrorCode;
import excepciones.MiembroException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Juegos")
public class Juegos extends HttpServlet {
	/**
	 * Default constructor.
	 */
	public Juegos() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		response.setContentType("application/json");

		String action = request.getParameter("action");

		HttpSession newSession = request.getSession(true);
		UsuarioDTO us1 = (UsuarioDTO) newSession.getAttribute("userObj");

		if ((us1 != null) && (action != null)) {

			try {
				if (action.equals("getJuegos")) {

					List<JuegoDTO> juegos = new BusinessDelegateTruco().getJuegosActivo(us1);
					JSONArray arr = new JSONArray();
					for (JuegoDTO juegoDTO : juegos) {
						JSONObject obj = new JSONObject(juegoDTO.toJson());
						arr.put(obj);
					}
					out.write(arr.toString());

				} else if (action.equals("unirsePartida")) {

					new BusinessDelegateTruco().agregarAListaEspera(us1);
					out.write("{\"ERROR\":\"FALSE\"}");

				} else if (action.equals("getCartas")) {

					Integer id = Integer.valueOf(request.getParameter("idJuego"));
					JuegoDTO j = new BusinessDelegateTruco().getJuegosById(id);

					List<CartaDTO> c = new BusinessDelegateTruco().getCartas(j, us1);

					JSONArray arr = new JSONArray();

					for (CartaDTO cartaDTO : c) {
						JSONObject obj = new JSONObject(cartaDTO.toJson());

						arr.put(obj);
					}

					out.write(arr.toString());
				} else if (action.equals("getJuego")) {

					Integer id = Integer.valueOf(request.getParameter("idJuego"));
					JuegoDTO j = new BusinessDelegateTruco().getJuegosById(id);

					out.write(j.toJson().toString());
				} else if (action.equals("esMiturno")) {

					Integer id = Integer.valueOf(request.getParameter("idJuego"));
					JuegoDTO j = new BusinessDelegateTruco().getJuegosById(id);

					boolean turno = new BusinessDelegateTruco().esMiTurno(j, us1);
					boolean cantaron = new BusinessDelegateTruco().alguienTieneQueContestar(j);
					String tanto = new BusinessDelegateTruco().tengoQueContestar(j, us1);

					JSONObject jsonOb = new JSONObject();

					jsonOb.put("TURNO", turno);
					jsonOb.put("CANTARON", cantaron);
					jsonOb.put("TANTO", tanto);

					out.write(jsonOb.toString());

				} else if (action.equals("JugarCarta")) {

					Integer idCar = Integer.valueOf(request.getParameter("idCarta"));

					Integer idJu = Integer.valueOf(request.getParameter("idJuego"));
					JuegoDTO juegoDTO = new BusinessDelegateTruco().getJuegosById(idJu);

					List<CartaDTO> c = new BusinessDelegateTruco().getCartas(juegoDTO, us1);

					CartaDTO jugarCarta = null;
					for (CartaDTO cartas : c) {
						if (cartas.getIdCarta() == idCar) {

							jugarCarta = cartas;
							break;
						}
					}
					new BusinessDelegateTruco().jugarCarta(juegoDTO, jugarCarta, us1);
					out.write("{\"JUGADA\":\"TRUE\"}");
				} else if (action.equals("responderTanto")) {

					Integer idJu = Integer.valueOf(request.getParameter("idJuego"));
					JuegoDTO juegoDTO = new BusinessDelegateTruco().getJuegosById(idJu);
					String jug = request.getParameter("jug");
					String tanto = new BusinessDelegateTruco().tengoQueContestar(juegoDTO, us1);
					JSONObject jsonOb = new JSONObject();
					if (tanto==null) {
						
						

						jsonOb.put("TURNO", false);
						
					} else {
						if (jug.equals("ENVIDO"))
							new BusinessDelegateTruco().cantarEnvido(juegoDTO, us1);
						if (jug.equals("REAL ENVIDO"))
							new BusinessDelegateTruco().cantarRealEnvido(juegoDTO, us1);
						if (jug.equals("FALTA ENVIDO"))
							new BusinessDelegateTruco().cantarFaltaEnvido(juegoDTO, us1);
						if (jug.equals("QUIERO ENVIDO"))
							new BusinessDelegateTruco().quieroEnvido(juegoDTO);
						if (jug.equals("NO QUIERO ENVIDO"))
							new BusinessDelegateTruco().noQuieroEnvido(juegoDTO, us1);
						if (jug.equals("TRUCO"))
							new BusinessDelegateTruco().cantarTruco(juegoDTO, us1);
						if (jug.equals("RE TRUCO"))
							new BusinessDelegateTruco().cantarReTruco(juegoDTO, us1);
						if (jug.equals("VALE CUATRO"))
							new BusinessDelegateTruco().cantarValeCuatro(juegoDTO, us1);
						if (jug.equals("NO QUIERO TRUCO"))
							new BusinessDelegateTruco().noQuieroTruco(juegoDTO, us1);
						if (jug.equals("QUIERO TRUCO"))
							new BusinessDelegateTruco().quieroTruco(juegoDTO);
						
						
						jsonOb.put("TURNO", true);
					}
					out.write(jsonOb.toString());
				}else if (action.equals("cantarTanto")) {

					Integer idJu = Integer.valueOf(request.getParameter("idJuego"));
					JuegoDTO juegoDTO = new BusinessDelegateTruco().getJuegosById(idJu);
					String jug = request.getParameter("jug");
					boolean turno = new BusinessDelegateTruco().esMiTurno(juegoDTO, us1);
					JSONObject jsonOb = new JSONObject();
					if (!turno) {
						
						jsonOb.put("TURNO", false);
						
					} else {
						if (jug.equals("ENVIDO"))
							new BusinessDelegateTruco().cantarEnvido(juegoDTO, us1);
						if (jug.equals("REAL ENVIDO"))
							new BusinessDelegateTruco().cantarRealEnvido(juegoDTO, us1);
						if (jug.equals("FALTA ENVIDO"))
							new BusinessDelegateTruco().cantarFaltaEnvido(juegoDTO, us1);
	
						
						if (jug.equals("TRUCO"))
							new BusinessDelegateTruco().cantarTruco(juegoDTO, us1);
						if (jug.equals("RE TRUCO"))
							new BusinessDelegateTruco().cantarReTruco(juegoDTO, us1);
						if (jug.equals("VALE CUATRO"))
							new BusinessDelegateTruco().cantarValeCuatro(juegoDTO, us1);
						
						if (jug.equals("QUIERO TRUCO"))
							new BusinessDelegateTruco().quieroTruco(juegoDTO);
						
						
						jsonOb.put("TURNO", true);
					}
					out.write(jsonOb.toString());
					
					
				}else if (action.equals("buscarUsuarios")) {
					
//					new BusinessDelegateTruco()
					
				}
				
			} catch (ComunicacionException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MiembroException e) {
				// Probablemente haya que enviar un Status Code
				ErrorCode error = ErrorCode.MIEMBRO_NO_ENCONTRADO;
				error.setDescripcion(e.getMessage());
				System.out.println(error.toString());
			}
		}
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
