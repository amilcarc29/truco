package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ChicoEntity;
import entities.JugadorEntity;
import entities.ParejaEntity;
import entities.PuntuacionEntity;
import excepciones.CategoriaException;
import excepciones.ParejaException;
import hbt.HibernateUtil;
import negocio.Chico;
import negocio.Jugador;
import negocio.Pareja;
import negocio.Puntuacion;

public class PuntuacionDAO {

	private static PuntuacionDAO instancia;

	public static PuntuacionDAO getInstancia() {
		if (instancia == null)
			instancia = new PuntuacionDAO();
		return instancia;
	}

	public PuntuacionDAO() {
	}

	public int guardarPuntuacion(Chico chico, Pareja pareja) throws ParejaException {
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

	public List<Puntuacion> buscarPuntosByChico(Integer idChico) throws CategoriaException {
		// TODO Auto-generated method stub

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<PuntuacionEntity> puntuacion = (List<PuntuacionEntity>) session
				.createQuery("from PuntuacionEntity where idChico = ? ").setParameter(0, idChico).list();

		session.close();
		List<Puntuacion> puntuaciones = new ArrayList<>();
		List<PuntuacionEntity> puntEnt = new ArrayList<>();

		for (PuntuacionEntity p : puntEnt) {
			puntuaciones.add(toNegocio(p));
		}

		return puntuaciones;
	}

	public PuntuacionEntity buscarPuntosByIdEntity(Integer idPuntuacion) throws CategoriaException {
		// TODO Auto-generated method stub

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		PuntuacionEntity puntuacion = (PuntuacionEntity) session
				.createQuery("from PuntuacionEntity where idPuntuacion = ? ").setParameter(0, idPuntuacion)
				.uniqueResult();

		session.close();

		return puntuacion;
	}

	private Puntuacion toNegocio(PuntuacionEntity p) throws CategoriaException {
		// TODO Auto-generated method stub
		Puntuacion pareja = new Puntuacion(ParejaDAO.getInstancia().toNegocio(p.getPareja()));
		pareja.setIdPuntuacion(p.getIdPuntuacion());
		return pareja;
	}

	public void actualizarPuntos(Puntuacion p) throws CategoriaException {
		PuntuacionEntity pEnt = null;
		pEnt = this.buscarPuntosByIdEntity(p.getIdPuntuacion());

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(pEnt);
		session.getTransaction().commit();
		session.close();

	}

}
