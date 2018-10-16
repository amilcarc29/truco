package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.JugadorEntity;
import excepciones.CategoriaException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Jugador;
import negocio.JugadorIndividual;

public class JugadorDAO {
	private static JugadorDAO instancia;

	public static JugadorDAO getInstancia() {
		if (instancia == null)
			instancia = new JugadorDAO();
		return instancia;
	}

	public JugadorDAO() {
	}

	public JugadorEntity buscarJugadorById(int idJugador) throws UsuarioException, CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JugadorEntity jugadorEntity = (JugadorEntity) session.createQuery("from JugadorEntity where idJugador = ?")
				.setParameter(0, idJugador).uniqueResult();
		session.close();
		if (jugadorEntity != null) {
			return jugadorEntity;
		} else {
			throw new UsuarioException("El jugador con id: " + idJugador + "no existe en la base de datos.");
		}
	}

	public Jugador toNegocio(JugadorEntity pe) throws CategoriaException {
		Jugador j = null;
		if (pe.getTipo().equals("individual")) {
			j = new JugadorIndividual(UsuarioDAO.getInstancia().toNegocio(pe.getUsuario()));
			j.setId(pe.getIdJugador());
		}
		return j;
	}

	public JugadorEntity buscarJugadorByUsario(int id, int idUsuario) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JugadorEntity jugadorEntity = (JugadorEntity) session.createQuery("from JugadorEntity where idJugador = ?")
				.setParameter(0, idUsuario).uniqueResult();
		session.close();
		if (jugadorEntity != null) {
			return jugadorEntity;
		} else {
//			throw new UsuarioException("El jugador con id: " + idJugador + "no existe en la base de datos.");
		}
		return null;
	}
}
