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

import delegado.BusinessDelegateTruco;
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
		if (us1 != null) {

			try {

				if (action.equals("buscarJuegos")) {

					List<JuegoDTO> juegos = new BusinessDelegateTruco().getJuegosActivo(us1);
					JSONArray arr = new JSONArray();
					for (JuegoDTO juegoDTO : juegos) {
						arr.put(juegoDTO.toJson());
					}
					out.write(arr.toString());

				} else if (action.equals("unirsePartida")) {

					new BusinessDelegateTruco().agregarAListaEspera(us1);
					out.write("{\"ERROR\":\"FALSE\"}");
				}

			} catch (ComunicacionException e) {
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
