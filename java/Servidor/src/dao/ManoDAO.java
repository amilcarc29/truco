package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ChicoEntity;
import entities.ManoEntity;
import hbt.HibernateUtil;
import negocio.Chico;

public class ManoDAO {
	
	
	private static ManoDAO instancia;

	public static ManoDAO getInstancia() {
		if (instancia == null)
			instancia = new ManoDAO();
		return instancia;
	}

	public ManoDAO() {
	}
	
	public int guardarMano(Chico chico) {
		ChicoEntity ch = null;
		// VER EXCEPCIONES
		ch = ChicoDAO.getInstancia().buscarChicoPorID(chico.getIdChico());
		
		ManoEntity me = new ManoEntity (ch);

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(me);
		session.getTransaction().commit();
		session.close();
		
		return me.getIdMano();
		
	}

}
