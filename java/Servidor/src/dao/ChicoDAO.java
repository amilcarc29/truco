package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ChicoEntity;
import entities.JuegoEntity;
import entities.ParejaEntity;
import excepciones.CategoriaException;
import excepciones.ParejaException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Chico;
import negocio.Juego;

public class ChicoDAO {
	
	private static ChicoDAO instancia;

	public static ChicoDAO getInstancia() {
		if (instancia == null)
			instancia = new ChicoDAO();
		return instancia;
	}

	public ChicoDAO() {
	}

	public void guardarChico(Juego juego, Chico chico) throws ParejaException {
		
		JuegoEntity jue = null;
		
		try {
			jue = JuegoDAO.getInstancia().buscarJuegoPorID(juego.getId());
		} catch (ParejaException e) {
			e.printStackTrace();
		}
		
		ChicoEntity ch = new ChicoEntity(jue, null, chico.getPuntosPorGanar());
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ch);
		session.getTransaction().commit();
		session.close();			
	}
	
	
}
