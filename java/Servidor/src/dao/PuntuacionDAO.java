package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ChicoEntity;
import entities.ParejaEntity;
import entities.PuntuacionEntity;
import excepciones.ParejaException;
import hbt.HibernateUtil;
import negocio.Chico;
import negocio.Pareja;

public class PuntuacionDAO {
	
	private static PuntuacionDAO instancia;

	public static PuntuacionDAO getInstancia() {
		if (instancia == null)
			instancia = new PuntuacionDAO();
		return instancia;
	}

	public PuntuacionDAO() {
	}
	
	public int guardarPuntuacion (Chico chico, Pareja pareja) throws ParejaException {
		ChicoEntity ch = null;
		ParejaEntity pa = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		try {
			ch = ChicoDAO.getInstancia().buscarChicoPorID(chico.getIdChico());
			pa = ParejaDAO.getInstancia().buscarParejaPorId(pareja.getIdPareja());
		} catch (ParejaException e) {
			e.printStackTrace();
		}
		
		PuntuacionEntity pu = new PuntuacionEntity(ch, pa, 0);		
		session.beginTransaction();
		session.saveOrUpdate(pu);
		session.getTransaction().commit();
		session.close();
		
		return pu.getIdPuntuacion();
	}

}
