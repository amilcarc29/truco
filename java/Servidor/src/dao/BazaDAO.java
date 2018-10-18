package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.BazaEntity;
import entities.ManoEntity;
import hbt.HibernateUtil;
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

	public int guardarBaza(Mano mano) {
		ManoEntity m = null;
		//ver excepciones
		m = ManoDAO.getInstancia().buscarManoPorID(mano.getIdMano());
		BazaEntity bz = new BazaEntity(m, null);
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(bz);
		session.getTransaction().commit();
		session.close();
		
		return bz.getIdBaza();
	}
}
