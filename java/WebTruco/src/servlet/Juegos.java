package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import delegado.BusinessDelegateTruco;
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
	
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will
		// return your json object
		HttpSession newSession = request.getSession(true);
//		newSession.getAttribute(arg0)
//		try {
//			UsuarioDTO us1 = new BusinessDelegateTruco().login(user, password);
//			if (us1 == null) {
//				out.write("{\"ERROR\":\"TRUE\"}");
//				newSession.setAttribute("user", "null");
//
//			} else {
//				out.write(us1.toJson());
//				newSession.setAttribute("user", us1.toJson());
//
//			}
//
//		} catch (ComunicacionException e1) {
//
//		}

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
