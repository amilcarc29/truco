package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.BazaEntity;
import entities.CartaEntity;
import entities.CategoriaEntity;
import entities.JuegoEntity;
import entities.JugadaEntity;
import entities.JugadorEntity;
import entities.UsuarioEntity;
import excepciones.BazaException;
import excepciones.CategoriaException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Baza;
import negocio.Carta;
import negocio.Jugada;
import negocio.Mano;

public class JugadaDAO {

	private static JugadaDAO instancia;

	public static JugadaDAO getInstancia() {
		if (instancia == null)
			instancia = new JugadaDAO();
		return instancia;
	}

	public JugadaDAO() {

	}

	public int guardarJugada(Jugada jugada, Baza baza) throws UsuarioException, CategoriaException, BazaException {
		JugadorEntity jug = null;
		CartaEntity ca = null;
		BazaEntity ba = null;

		try {
			jug = JugadorDAO.getInstancia().buscarJugadorById(jugada.getJugador().getId());
		} catch (UsuarioException e) {
			e.printStackTrace();
		} catch (CategoriaException e1) {
			e1.printStackTrace();
		}

		// AGREGAR EXCEPCION CARTA
		ca = CartaDAO.getInstancia().buscarCartaPorIDEntity(jugada.getCarta().getIdCarta());

		// AGREGAR EXCEPCION BAZA
		ba = BazaDAO.getInstancia().buscarBazaPorID(baza.getIdBaza());

		//

		JugadaEntity je = new JugadaEntity(jug, ca, ba);

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(je);
		session.getTransaction().commit();
		session.close();

		return je.getIdJugada();
	}

	public JugadaEntity buscarJugadaPorID(int idJugada) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JugadaEntity jugadaEntity = (JugadaEntity) session.createQuery("from JugadaEntity where idJugada = ?")
				.setParameter(0, idJugada).uniqueResult();
		session.close();
		if (jugadaEntity != null) {
			return jugadaEntity;
		} else {
			// falta la excepcion Jugada
			return null;
		}
	}

	public List<Jugada> buscarJugadaPorIDBaza(int idBaza) throws UsuarioException, CategoriaException {
		// TODO Auto-generated method stub

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<JugadaEntity> jugadas = (List<JugadaEntity>) session.createQuery("from JugadaEntity where idBaza = ? order by idJugada desc")
				.setParameter(0, idBaza).list();

		session.close();
		List<Jugada> ju = new ArrayList<>();

		for (JugadaEntity jug : jugadas) {

			Jugada j = toNegocio(jug);
			ju.add(j);
		}

		return ju;
	}

	public Jugada toNegocio(JugadaEntity bazaEnt) throws UsuarioException, CategoriaException {
		// TODO Auto-generated method stub
		Jugada j = new Jugada();
		j.setCarta(CartaDAO.getInstancia().toNegocio(bazaEnt.getCarta()));
		j.setJugador(JugadorDAO.getInstancia().buscarJugadorByIdClase(bazaEnt.getJugador().getIdJugador()));
		j.setIdJugada(bazaEnt.getIdJugada());
		return j;
	}

	public Jugada buscarJugadaMayorPorID(int idMano) throws UsuarioException, CategoriaException {
		// TODO Auto-generated method stub

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<JugadaEntity> jugadas = (List<JugadaEntity>) session.createQuery("from JugadaEntity where idBaza = ?")
				.setParameter(0, idMano).list();

		session.close();

		Jugada mayor = null;
		Carta cAnt = null;
		for (JugadaEntity jug : jugadas) {

			Jugada j = toNegocio(jug);

			if (cAnt == null) {
				cAnt = j.getCarta();
				mayor = j;
			} else {
				if (j.getCarta().esMayor(cAnt))
					mayor = j;

			}

		}

		return mayor;
	}



	
}
