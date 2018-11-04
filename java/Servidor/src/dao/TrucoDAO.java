package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ManoEntity;
import entities.TrucooEntity;
import hbt.HibernateUtil;
import negocio.Mano;
import negocio.Trucoo;

public class TrucoDAO {

	private static TrucoDAO instancia;

	public TrucoDAO() {
	}

	public static TrucoDAO getInstancia() {
		if (instancia == null)
			instancia = new TrucoDAO();
		return instancia;
	}
	
	public int guardarTruco (Trucoo truco, Mano mano) {
		ManoEntity m = null;
		m = ManoDAO.getInstancia().buscarManoPorID(mano.getIdMano());
		TrucooEntity truc = this.buscarTrucoByID(truco.getId());
		
		if (truc == null) {
			truc = new TrucooEntity(m, null, truco.getPuntosQuiero(), truco.getPuntosNoQuiero());
		} else {
			truc.setPuntosQuiero(truco.getPuntosQuiero());
			truc.setPuntosNoQuiero(truco.getPuntosNoQuiero());
		}
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.saveOrUpdate(truc);
		session.getTransaction().commit();
		session.close();
		
		return truc.getIdTruco();
		
	}
	
	public TrucooEntity buscarTrucoByID (int idTruco) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		TrucooEntity trucooEntity = (TrucooEntity) session.createQuery("from TrucooEntity where idTruco = ?")
				.setParameter(0, idTruco).uniqueResult();
		session.close();
		
		return trucooEntity;
	}
}
