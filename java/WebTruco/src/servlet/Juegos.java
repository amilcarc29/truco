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

import com.sun.jdi.IntegerType;

import delegado.BusinessDelegateTruco;
import dto.CartaDTO;
import dto.JuegoDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;

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
				}else if (action.equals("esMiturno")) {
					
					Integer id = Integer.valueOf(request.getParameter("idJuego"));
					JuegoDTO j = new BusinessDelegateTruco().getJuegosById(id);
					
					if (new BusinessDelegateTruco().esMiTurno(j, us1)){
						out.write("{\"TURNO\":\"TRUE\"}");
					}else {
						out.write("{\"TURNO\":\"FALSE\"}");
					}
					
				
					
					
				}else if (action.equals("jugar")) {
					
					Integer id = Integer.valueOf(request.getParameter("idJuego"));
					JuegoDTO j = new BusinessDelegateTruco().getJuegosById(id);
//					CartaDto carta = new CartaDTO(idCarta, numero, palo);
//					
//					if (new BusinessDelegateTruco().esMiTurno(j, us1)){
//						new BusinessDelegateTruco().jugarCarta(j, cartaDTO, us1);
//					}else {
//						out.write("{\"TURNO\":\"FALSE\"}");
//					}
					
				
					
					
				}

			} catch (ComunicacionException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
