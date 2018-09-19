package dao;

public class UsuarioDAO {
	private static UsuarioDAO instancia;

	public UsuarioDAO() {
	}

	public static UsuarioDAO getInstance() {
		if (instancia == null)
			instancia = new UsuarioDAO();
		return instancia;
	}

}
