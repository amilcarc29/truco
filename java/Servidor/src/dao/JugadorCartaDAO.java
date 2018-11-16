package dao;

import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.BazaEntity;
import entities.CartaEntity;
import entities.JugadorCartaEntity;
import entities.JugadorEntity;
import entities.ManoEntity;
import excepciones.BazaException;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.ManoException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Carta;
import negocio.Jugador;
import negocio.Mano;

public class JugadorCartaDAO {
	private static JugadorCartaDAO instancia;

	public static JugadorCartaDAO getInstancia() {
		if (instancia == null)
			instancia = new JugadorCartaDAO();
		return instancia;
	}

	public JugadorCartaDAO() {
	}

	public void guardarCartas(List<Carta> cartas, Jugador jugador, int idMano) throws UsuarioException, CategoriaException, CartaException, ManoException {

		
		ManoEntity mano = ManoDAO.getInstancia().buscarManoPorID(idMano);
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		JugadorEntity je = JugadorDAO.getInstancia().buscarJugadorById(jugador.getId());
		for (Carta carta : cartas) {
			CartaEntity ce = CartaDAO.getInstancia().buscarCartaPorIDEntity(carta.getIdCarta());

			JugadorCartaEntity jc = new JugadorCartaEntity(je, ce);
			jc.setMano(mano);

			session.saveOrUpdate(jc);

		}

		session.getTransaction().commit();
		session.close();

	}
	public List<Carta> getCartasbyJugador(Jugador jug, boolean jugada) {
		// TODO Auto-generated method stub
		
		Vector<Carta> cartas = new Vector<>();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<JugadorCartaEntity> jugadorcartasEnt = (List<JugadorCartaEntity>) session.createQuery("from JugadorCartaEntity where idJugador = ? and cartaJugada=?" )
				.setParameter(0, jug.getId()).setParameter(1, jugada).list();
		session.close();

		for (JugadorCartaEntity jc : jugadorcartasEnt) {
			
			cartas.add(CartaDAO.getInstancia().toNegocio(jc.getCarta()));
		}
		
		return cartas;
	}
	
	public List<Carta> getCartasbyJugador(Jugador jug, Mano mano, boolean jugada) {
		// TODO Auto-generated method stub
		
		Vector<Carta> cartas = new Vector<>();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<JugadorCartaEntity> jugadorcartasEnt = (List<JugadorCartaEntity>) session.createQuery("from JugadorCartaEntity where idJugador = ? and cartaJugada=?  and idMano=? " )
				.setParameter(0, jug.getId()).setParameter(1, jugada).setParameter(2, mano.getIdMano()).list();
		session.close();

		for (JugadorCartaEntity jc : jugadorcartasEnt) {
			
			cartas.add(CartaDAO.getInstancia().toNegocio(jc.getCarta()));
		}
		
		return cartas;
	}

	

	public List<Carta> getTodasCartasbyJugador(Jugador jug) {
		// TODO Auto-generated method stub
		
		Vector<Carta> cartas = new Vector<>();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<JugadorCartaEntity> jugadorcartasEnt = (List<JugadorCartaEntity>) session.createQuery("from JugadorCartaEntity where idJugador = ? ").setParameter(0, jug.getId()).list();
		session.close();

		for (JugadorCartaEntity jc : jugadorcartasEnt) {
			
			cartas.add(CartaDAO.getInstancia().toNegocio(jc.getCarta()));
		}
		
		return cartas;
	}

	
	public void guardarCartaJugada(int idJugador, int idCarta) throws BazaException {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		JugadorCartaEntity jugadorcartasEnt = (JugadorCartaEntity) session.
				createQuery("from JugadorCartaEntity where idJugador = ? and idCarta = ? and cartaJugada = 0 ").setParameter(0, idJugador).setParameter(1, idCarta).uniqueResult();
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
