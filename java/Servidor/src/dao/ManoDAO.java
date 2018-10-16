package dao;

public class ManoDAO {
	
	
	private static ManoDAO instancia;

	public static ManoDAO getInstancia() {
		if (instancia == null)
			instancia = new ManoDAO();
		return instancia;
	}

	public ManoDAO() {
	}
	
	public void guardarMano() {
		
	}

}
