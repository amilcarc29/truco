package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.EnvidooEntity;
import entities.JugadorEntity;
import entities.ManoEntity;
import entities.TrucooEntity;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Envidoo;
import negocio.Mano;
import negocio.Trucoo;

public class EnvidooDAO {
	
	private static EnvidooDAO instancia;

	public EnvidooDAO() {
	}

	public static EnvidooDAO getInstancia() {
		if (instancia == null)
			instancia = new EnvidooDAO();
		return instancia;
	}
	
	public int guardarEnvido (Envidoo envido, Mano mano) {
		ManoEntity m = null;
		m = ManoDAO.getInstancia().buscarManoPorID(mano.getIdMano());
		EnvidooEntity env = this.buscarEnvioByID(envido.getId());
		
		if (env == null) {
			env = new EnvidooEntity(m, null, envido.getPuntosQuiero(), envido.getPuntosNoQuiero());
		} else {
			env.setPuntosQuiero(envido.getPuntosQuiero());
			env.setPuntosNoQuiero(env.getPuntosNoQuiero());
		}
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.saveOrUpdate(env);
		session.getTransaction().commit();
		session.close();
		
		return env.getIdEnvite();
		
	}
	
	public EnvidooEntity buscarEnvioByID (int idEnvido) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		EnvidooEntity envidooEntity = (EnvidooEntity) session.createQuery("from EnvidooEntity where idEnvido = ?")
				.setParameter(0, idEnvido).uniqueResult();
		session.close();
		
		return envidooEntity;
	}
	
	public Envidoo buscarEnvidoByMano (int idMano) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		EnvidooEntity envidooEntity = (EnvidooEntity) session.createQuery("from EnvidooEntity where idMano = ?")
				.setParameter(0, idMano).uniqueResult();
		session.close();
		
		return this.toNegocio(envidooEntity);
	}
	
	public Envidoo toNegocio (EnvidooEntity envido) {
		Envidoo env = new Envidoo();
		env.setId(envido.getIdEnvite());
		env.setPuntosQuiero(envido.getPuntosQuiero());
		env.setPuntosNoQuiero(envido.getPuntosNoQuiero());
		return env;
	}
	
	

}
