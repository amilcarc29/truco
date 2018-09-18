package dao;

public class UsuarioDao {
	private static UsuarioDao instancia;

	private UsuarioDao() {
	}

	public static UsuarioDao getInstance() {
		if (instancia == null)
			instancia = new UsuarioDao();
		return instancia;
	}

}
