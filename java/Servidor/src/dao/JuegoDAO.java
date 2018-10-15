package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.JuegoEntity;
import entities.JugadorEntity;
import entities.ParejaEntity;
import excepciones.CategoriaException;
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

	
	
	
	public void guardarJuegoLibreIndividual(Juego juego, String tipo) throws UsuarioException, CategoriaException {
		
		JugadorEntity je1 = JugadorDAO.getInstancia().buscarJugadorById(juego.getParejas().get(0).getJugador1().getId());
		JugadorEntity je2 = JugadorDAO.getInstancia().buscarJugadorById(juego.getParejas().get(1).getJugador1().getId());
		JugadorEntity je3 = JugadorDAO.getInstancia().buscarJugadorById(juego.getParejas().get(2).getJugador1().getId());
		JugadorEntity je4 = JugadorDAO.getInstancia().buscarJugadorById(juego.getParejas().get(3).getJugador1().getId());

		
		
		ParejaEntity par1 = new ParejaEntity(je1, je2);
		ParejaEntity par2 = new ParejaEntity(je3, je4);
		JuegoEntity ent = new JuegoEntity(par1, par2, tipo);
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ent);
		session.getTransaction().commit();
		session.close();
	}

	public void guardarJuego(JugadorIndividual jugadorIndividual, String string) {
		// TODO Auto-generated method stub

	}

}
