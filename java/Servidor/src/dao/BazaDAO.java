package dao;

import entities.BazaEntity;
import negocio.Baza;
import negocio.Mano;

public class BazaDAO {
	private static BazaDAO instancia;

	public static BazaDAO getInstancia() {
		if (instancia == null)
			instancia = new BazaDAO();
		return instancia;
	}

	public BazaDAO() {
	}

	public int guardarBaza(Mano mano,Baza baza) {
		
		return 0;
	}
}
