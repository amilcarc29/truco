package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ChicoEntity;
import entities.ParejaEntity;
import entities.PuntuacionEntity;
import excepciones.CategoriaException;
import excepciones.ChicoException;
import excepciones.ParejaException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Chico;
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

	public int guardarPuntuacion(Chico chico, Pareja pareja) throws ParejaException, ChicoException {
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

	public List<Puntuacion> buscarPuntosByChico(Integer idChico) throws CategoriaException, UsuarioException {
		// TODO Auto-generated method stub

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<PuntuacionEntity> puntEnt = (List<PuntuacionEntity>) session
				.createQuery("from PuntuacionEntity where idChico = ? ").setParameter(0, idChico).list();

		session.close();
		List<Puntuacion> puntuaciones = new ArrayList<>();
//		List<PuntuacionEntity> puntEnt = new ArrayList<>();

		for (PuntuacionEntity p : puntEnt) {
			puntuaciones.add(toNegocio(p));
		}

		return puntuaciones;
	}

	public PuntuacionEntity buscarPuntosByIdEntity(int idPuntuacion) throws CategoriaException {
		// TODO Auto-generated method stub

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		PuntuacionEntity puntuacion = (PuntuacionEntity) session
				.createQuery("from PuntuacionEntity where idPuntuacion = ? ").setParameter(0, idPuntuacion)
				.uniqueResult();

		session.close();

		return puntuacion;
	}

	private Puntuacion toNegocio(PuntuacionEntity p) throws CategoriaException, UsuarioException {
		// TODO Auto-generated method stub
		Puntuacion puntucaion = new Puntuacion(ParejaDAO.getInstancia().toNegocio(p.getPareja()));
		puntucaion.setIdPuntuacion(p.getIdPuntuacion());
		puntucaion.setPuntos(p.getPuntuacion());
		
//		puntucaion.setChico(ChicoDAO.getInstancia().toNegocio(p.getChico()));
		
		return puntucaion;
	}

	public void actualizarPuntos(Puntuacion p) throws CategoriaException, ParejaException {
		PuntuacionEntity pEnt = null;
		
		pEnt = this.buscarPuntosByIdEntity(p.getIdPuntuacion());
		pEnt.setPuntuacion(p.getPuntos());
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(pEnt);
		session.getTransaction().commit();
		session.close();

//		if (p.getIdPuntuacion() == 0) {
//			pEnt = new PuntuacionEntity();
//			pEnt.setPuntuacion(p.getIdPuntuacion());
//			pEnt.setChico(ChicoDAO.getInstancia().buscarChicoPorID(p.getChico().getIdChico()));
//			pEnt.setPareja(ParejaDAO.getInstancia().buscarParejaPorId(p.getPareja().getIdPareja()));
//
//		} else {
//
//			pEnt = this.buscarPuntosByIdEntity(p.getIdPuntuacion());
//		}
//
//		SessionFactory sf = HibernateUtil.getSessionFactory();
//		Session session = sf.openSession();
//		session.beginTransaction();
//		session.saveOrUpdate(pEnt);
//		session.getTransaction().commit();
//		session.close();

	}

}
