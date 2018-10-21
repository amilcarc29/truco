package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.BazaEntity;
import entities.CartaEntity;
import entities.ChicoEntity;
import entities.JuegoEntity;
import entities.JugadaEntity;
import entities.ManoEntity;
import excepciones.CategoriaException;
import excepciones.UsuarioException;
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
		// ver excepciones
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

	public void actualizarJugadaMayor(Baza baza) throws UsuarioException, CategoriaException {
		
		Jugada jMayor = null;
		jMayor = JugadaDAO.getInstancia().buscarJugadaMayorPorID(baza.getIdBaza());
		JugadaEntity je =  JugadaDAO.getInstancia().buscarJugadaPorID(jMayor.getIdJugada());

		//actualiza la jugada mayor en todas las bazas de la mano

		BazaEntity be = this.buscarBazaPorID(baza.getIdBaza());
		
		

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		
		be.setJugadaMayor(je);
		session.update(be);
		session.getTransaction().commit();

		session.close();
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

	public List<Baza> buscarBazaPorIDMano(Mano mano) throws UsuarioException, CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<BazaEntity> bazasEntity = (List<BazaEntity>) session.createQuery("from BazaEntity where idMano = ?")
				.setParameter(0, mano.getIdMano()).list();

		session.close();
		List<Baza> bz = new ArrayList<>();

		for (BazaEntity bazaEntity : bazasEntity) {
			// TODO VER EL ORDEN
			Baza b = new Baza(mano.getJugadores());
			b.setIdBaza(bazaEntity.getIdBaza());

			if (bazaEntity.getJugadaMayor() != null)
				b.setJugadaMayor(JugadaDAO.getInstancia().toNegocio(bazaEntity.getJugadaMayor()));

			List<Jugada> jugadas = JugadaDAO.getInstancia().buscarJugadaPorIDBaza(bazaEntity.getIdBaza());
			b.setJugadas(jugadas);
			bz.add(b);
		}

		return bz;

	}

	public void actualizarJugadaMayor(Baza baza, Jugada jugada) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		// VER EXCEPCIONES
		BazaEntity bz = BazaDAO.getInstancia().buscarBazaPorID(baza.getIdBaza());
		JugadaEntity jug = JugadaDAO.getInstancia().buscarJugadaPorID(jugada.getIdJugada());

		bz.setJugadaMayor(jug);

		session.beginTransaction();
		session.saveOrUpdate(bz);
		session.getTransaction().commit();
		session.close();
	}

}
