package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.BazaEntity;
import entities.CartaEntity;
import entities.ChicoEntity;
import entities.JuegoEntity;
import entities.JugadaEntity;
import entities.ManoEntity;
import hbt.HibernateUtil;
import negocio.Baza;
import negocio.Jugada;
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
	
	public void actualizarJugadaMayor(int idBaza, int idJugada) {
		BazaEntity b = null;
		b = this.buscarBazaPorID(idBaza);
		if (b != null) {
			JugadaEntity j = null;
			j = JugadaDAO.getInstancia().buscarJugadaPorID(idJugada);
		}
		
	}
	
	public BazaEntity buscarBazaPorID(int idBaza) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		BazaEntity bazaEntity = (BazaEntity) session.createQuery("from BazaEntity where idBaza = ?")
				.setParameter(0, idBaza).uniqueResult();
		session.close();
		if (bazaEntity != null) {
			return bazaEntity;
		} else {
			// falta la excepcion Baza
			return null;
		}	
	}
	
	public void actualizarJugadaMayor(Baza baza, Jugada jugada) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		// VER EXCEPCIONES
		BazaEntity bz = BazaDAO.getInstancia().buscarBazaPorID(baza.getIdBaza());
		JugadaEntity jug = JugadaDAO.getInstancia().buscarJugadaPorID(jugada.getIdJugada());
		
		bz.setJugadaMayor(jug);
		
		session.beginTransaction();
		session.saveOrUpdate(jug);
		session.getTransaction().commit();
		session.close();
	}

}
