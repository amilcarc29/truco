package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.JuegoEntity;
import entities.JugadorEntity;
import entities.ParejaEntity;
import excepciones.CategoriaException;
import excepciones.ParejaException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Juego;
import negocio.Jugador;
import negocio.JugadorIndividual;
import negocio.ModalidadLibreIndividual;

public class JuegoDAO {
	private static JuegoDAO instancia;

	public static JuegoDAO getInstancia() {
		if (instancia == null)
			instancia = new JuegoDAO();
		return instancia;
	}

	public JuegoDAO() {
	}

	
	
	
	public void guardarJuegoLibreIndividual(Juego juego, String tipo) throws UsuarioException, CategoriaException, ParejaException {

		
		ParejaEntity par1 =  ParejaDAO.getInstancia().buscarParejaPorId(juego.getParejas().get(0).getIdPareja());
		ParejaEntity par2 =  ParejaDAO.getInstancia().buscarParejaPorId(juego.getParejas().get(0).getIdPareja());
		
		JuegoEntity ent = new JuegoEntity(par1, par2, tipo);
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ent);
		session.getTransaction().commit();
		session.close();
	}



}
