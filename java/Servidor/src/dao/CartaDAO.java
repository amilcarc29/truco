package dao;

import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CartaEntity;
import excepciones.CartaException;
import hbt.HibernateUtil;
import negocio.Carta;

public class CartaDAO {

	private static CartaDAO instancia;

	public CartaDAO() {
	}

	public static CartaDAO getInstancia() {
		if (instancia == null) {
			instancia = new CartaDAO();
		}
		return instancia;
	}

	public Carta buscarCartaPorID(int idCarta) throws CartaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		CartaEntity cartaEntity = (CartaEntity) session.createQuery("from CartaEntity where idCarta = ?")
				.setParameter(0, idCarta).uniqueResult();
		session.close();
		if (cartaEntity != null) {
			return toNegocio(cartaEntity);
		}
		throw new CartaException("no se encontro la carta con id: " + idCarta);
	}

	public CartaEntity buscarCartaPorIDEntity(int idCarta) throws CartaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		CartaEntity cartaEntity = (CartaEntity) session.createQuery("from CartaEntity where idCarta = ?")
				.setParameter(0, idCarta).uniqueResult();
		session.close();
		if (cartaEntity != null) {
			return cartaEntity;
		}
		throw new CartaException("No se encontro la carta con id: " + idCarta);
	}

	public Carta toNegocio(CartaEntity carta) {
		Carta c = new Carta(carta.getNumero(), carta.getPalo(), carta.getPesoTruco(), carta.getPesoEnvido());
		c.setIdCarta(carta.getIdCarta());
		return c;
	}

	public Vector<Carta> getCartas() {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Vector<Carta> cartas = new Vector<>();
		
		List<CartaEntity> cartasEnt = (List<CartaEntity>) session.createQuery("from CartaEntity").list();
		session.close();

		for (CartaEntity c : cartasEnt) {
			cartas.add(toNegocio(c));
		}

		return cartas;
	}

}
