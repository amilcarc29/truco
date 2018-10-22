package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CartaEntity;
import entities.CategoriaEntity;
import entities.JuegoEntity;
import entities.JugadorCartaEntity;
import entities.JugadorEntity;
import entities.UsuarioEntity;
import excepciones.CategoriaException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Carta;
import negocio.Jugador;

public class JugadorCartaDAO {
	private static JugadorCartaDAO instancia;

	public static JugadorCartaDAO getInstancia() {
		if (instancia == null)
			instancia = new JugadorCartaDAO();
		return instancia;
	}

	public JugadorCartaDAO() {
	}

	public void guardarCartas(List<Carta> cartas, Jugador jugador) throws UsuarioException, CategoriaException {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		JugadorEntity je = JugadorDAO.getInstancia().buscarJugadorById(jugador.getId());
		for (Carta carta : cartas) {
			CartaEntity ce = CartaDAO.getInstancia().buscarCartaPorIDEntity(carta.getIdCarta());

			JugadorCartaEntity jc = new JugadorCartaEntity(je, ce);

			session.saveOrUpdate(jc);

		}

		session.getTransaction().commit();
		session.close();

	}

	public List<Carta> getCartasbyJugador(Jugador jug) {
		// TODO Auto-generated method stub
		
		Vector<Carta> cartas = new Vector<>();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<JugadorCartaEntity> jugadorcartasEnt = (List<JugadorCartaEntity>) session.createQuery("from JugadorCartaEntity where idJugador = ? and cartaJugada=0").setParameter(0, jug.getId()).list();
		session.close();

		for (JugadorCartaEntity jc : jugadorcartasEnt) {
			
			cartas.add(CartaDAO.getInstancia().toNegocio(jc.getCarta()));
		}
		
		return cartas;
	}

	public void guardarCartaJugada(int idJugador, int idCarta) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		JugadorCartaEntity jugadorcartasEnt = (JugadorCartaEntity) session.
				createQuery("from JugadorCartaEntity where idJugador = ? and idCarta = ?  ").setParameter(0, idJugador).setParameter(1, idCarta).uniqueResult();
		//session.close();
		
		jugadorcartasEnt.setCartaJugada(true);
		session.beginTransaction();
		session.saveOrUpdate(jugadorcartasEnt);
		session.getTransaction().commit();
		session.close();
		
	}

	public void limpiarCartas(int idJugador) {
		// TODO Auto-generated method stub

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<JugadorCartaEntity> jugadorcartasEnt = (List<JugadorCartaEntity>) session.createQuery("from JugadorCartaEntity where idJugador = ?").setParameter(0, idJugador).list();

		for (JugadorCartaEntity jc : jugadorcartasEnt) {
			jc.setCartaJugada(true);
			
			session.beginTransaction();
			session.saveOrUpdate(jc);
			session.getTransaction().commit();
			
		}
		session.close();
	}


}
