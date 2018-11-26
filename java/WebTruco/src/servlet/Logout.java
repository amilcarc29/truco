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
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Logout() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession newSession = request.getSession(true);
		UsuarioDTO us1 = (UsuarioDTO) newSession.getAttribute("userObj");
		
		try {
			new BusinessDelegateTruco().desloggearUsuario(us1);
		} catch (ComunicacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		newSession.invalidate();
		
		response.sendRedirect("./index.jsp");
		System.out.println("El usuario se deslogueo.");

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
