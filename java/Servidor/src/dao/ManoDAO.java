package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.BazaEntity;
import entities.ChicoEntity;
import entities.ManoEntity;
import hbt.HibernateUtil;
import negocio.Baza;
import negocio.Chico;
import negocio.Mano;

public class ManoDAO {

	private static ManoDAO instancia;

	public static ManoDAO getInstancia() {
		if (instancia == null)
			instancia = new ManoDAO();
		return instancia;
	}

	public ManoDAO() {
	}

	public int guardarMano(Chico chico, Mano mano) {
		ChicoEntity ch = null;
		// VER EXCEPCIONES
		ch = ChicoDAO.getInstancia().buscarChicoPorID(chico.getIdChico());

		ManoEntity me = new ManoEntity(ch);

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(me);
		session.getTransaction().commit();
		session.close();
		mano.setIdMano(me.getIdMano());
		Baza baza = new Baza();
		BazaDAO.getInstancia().guardarBaza(mano, baza);

		return me.getIdMano();

	}

}
