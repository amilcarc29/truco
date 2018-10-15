package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.JugadorEntity;
import entities.MiembroEntity;
import entities.ParejaEntity;
import entities.UsuarioEntity;
import excepciones.CategoriaException;
import excepciones.MiembroException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.JugadorGrupal;
import negocio.JugadorIndividual;
import negocio.Pareja;

public class ParejaDAO {

	private static ParejaDAO instancia;

	public ParejaDAO() {
	}

	public static ParejaDAO getInstancia() {
		if (instancia == null)
			instancia = new ParejaDAO();
		return instancia;
	}

	public void guardarParejaIndividual(Pareja pareja) throws CategoriaException {
		UsuarioEntity ue1 = null;
		UsuarioEntity ue2 = null;
		JugadorIndividual ju1 = (JugadorIndividual) pareja.getJugador1();
		JugadorIndividual ju2 = (JugadorIndividual) pareja.getJugador2();
		try {
			ue1 = UsuarioDAO.getInstancia().buscarUsuarioByIdEntity(ju1.getUsuario().getIdUsuario());
			ue2 = UsuarioDAO.getInstancia().buscarUsuarioByIdEntity(ju2.getUsuario().getIdUsuario());
		} catch (UsuarioException e) {
			e.printStackTrace();
		}
		ParejaEntity pe = new ParejaEntity(null, null);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(pe);
		session.getTransaction().commit();
		session.close();

		JugadorEntity je1 = new JugadorEntity(ue1, pe, null, "individual");
		SessionFactory sf2 = HibernateUtil.getSessionFactory();
		Session session2 = sf2.openSession();
		session2.beginTransaction();
		session2.saveOrUpdate(je1);
		session2.getTransaction().commit();
		session2.close();

		JugadorEntity je2 = new JugadorEntity(ue2, pe, null, "individual");
		SessionFactory sf3 = HibernateUtil.getSessionFactory();
		Session session3 = sf3.openSession();
		session3.beginTransaction();
		session3.saveOrUpdate(je2);
		session3.getTransaction().commit();
		session3.close();

		pe.setJugador1(je1);
		pe.setJugador2(je2);
		SessionFactory sf4 = HibernateUtil.getSessionFactory();
		Session session4 = sf4.openSession();
		session4.beginTransaction();
		session4.saveOrUpdate(pe);
		session4.getTransaction().commit();
		session4.close();

	}
	
	public void guardarParejaGrupal(Pareja pareja) throws CategoriaException, MiembroException {
		MiembroEntity mi1 = null;
		MiembroEntity mi2 = null;
		JugadorGrupal ju1 = (JugadorGrupal) pareja.getJugador1();
		JugadorGrupal ju2 = (JugadorGrupal) pareja.getJugador2();
		try {
			mi1 = MiembroDAO.getInstancia().buscarMiembroByIdEntity(ju1.getMiembro().getIdMiembro());
			mi2 = MiembroDAO.getInstancia().buscarMiembroByIdEntity(ju2.getMiembro().getIdMiembro());
		} catch (MiembroException e) {
			e.printStackTrace();
		}
		ParejaEntity pe = new ParejaEntity(null, null);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(pe);
		session.getTransaction().commit();
		session.close();

		JugadorEntity je1 = new JugadorEntity(null, pe, mi1, "grupal");
		SessionFactory sf2 = HibernateUtil.getSessionFactory();
		Session session2 = sf2.openSession();
		session2.beginTransaction();
		session2.saveOrUpdate(je1);
		session2.getTransaction().commit();
		session2.close();

		JugadorEntity je2 = new JugadorEntity(null, pe, mi2, "grupal");
		SessionFactory sf3 = HibernateUtil.getSessionFactory();
		Session session3 = sf3.openSession();
		session3.beginTransaction();
		session3.saveOrUpdate(je2);
		session3.getTransaction().commit();
		session3.close();

		pe.setJugador1(je1);
		pe.setJugador2(je2);
		SessionFactory sf4 = HibernateUtil.getSessionFactory();
		Session session4 = sf4.openSession();
		session4.beginTransaction();
		session4.saveOrUpdate(pe);
		session4.getTransaction().commit();
		session4.close();

	}

}
