package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ChicoEntity;
import entities.JugadorEntity;
import excepciones.CategoriaException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Juego;
import negocio.Jugador;
import negocio.JugadorIndividual;

public class JugadorDAO {
	private static JugadorDAO instancia;
	
	private List<Jugador> jugadores;
	
	
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

	public Jugador buscarJugadorByIdClase(int idJugador) throws UsuarioException, CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JugadorEntity jugadorEntity = (JugadorEntity) session.createQuery("from JugadorEntity where idJugador = ?")
				.setParameter(0, idJugador).uniqueResult();
		session.close();
		if (jugadorEntity != null) {
			return toNegocio(jugadorEntity);
		} else {
			throw new UsuarioException("El jugador con id: " + idJugador + "no existe en la base de datos.");
		}
	}

	public Jugador toNegocio(JugadorEntity pe) throws CategoriaException {
		Jugador j = null;
		if (pe.getTipo().equals("individual")) {
			j = new JugadorIndividual(UsuarioDAO.getInstancia().toNegocio(pe.getUsuario()));
			j.setTieneTurno(pe.tieneTurno());
			j.setId(pe.getIdJugador());

		}
		return j;
	}

	public Jugador buscarJugadorByUsario(int idJuego, int idUsuario) throws CategoriaException {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JugadorEntity jugadorEntity = (JugadorEntity) session
				.createQuery("from JugadorEntity where idJuego = ? and idUsuario = ?").setParameter(0, idJuego)
				.setParameter(1, idUsuario).uniqueResult();
		session.close();
		if (jugadorEntity != null) {
			return toNegocio(jugadorEntity);
		} else {
			// throw new UsuarioException("El jugador con id: " + idJugador + "no existe en
			// la base de datos.");
		}
		return null;
	}

	public List<Jugador> buscarJugadoresByJuego(int idJuego) throws CategoriaException {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<JugadorEntity> jugadoresEntity = (List<JugadorEntity>) session
				.createQuery("from JugadorEntity where idJuego = ? ").setParameter(0, idJuego).list();

		session.close();
		List<Jugador> jugadores = new ArrayList<>();

		for (JugadorEntity je : jugadoresEntity) {
			jugadores.add(toNegocio(je));
		}

		return jugadores;
	}

	public void setTurno(Jugador jugador) {
		// TODO Auto-generated method stub

	}

	public Jugador getJugadorConTurno(Juego juego) throws CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JugadorEntity jugadorEntity = (JugadorEntity) session
				.createQuery("from JugadorEntity where idJuego = ? and tieneTurno=1").setParameter(0, juego.getId())
				.uniqueResult();
		session.close();
		if (jugadorEntity != null) {
			return toNegocio(jugadorEntity);
		} else {
			// throw new UsuarioException("El jugador con id: " + idJugador + "no existe en
			// la base de datos.");
		}

		return null;
	}

	public void setTurnoSigJugador(Jugador jugador) throws CategoriaException, UsuarioException {
		// TODO Auto-generated method stub

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session ss = sf.openSession();
		ss.beginTransaction();



		for (int i = 0; i < jugadores.size() - 1; i++) {
			if (jugadores.get(i).esJugador(jugador.getId())) {

				JugadorEntity jant = JugadorDAO.getInstancia().buscarJugadorById(jugadores.get(i).getId());
				jant.setTieneTurno(false);

				JugadorEntity jsig = JugadorDAO.getInstancia().buscarJugadorById(jugadores.get(i + 1).getId());
				jsig.setTieneTurno(true);

				ss.update(jant);
				ss.update(jsig);

				break;

			}
		}

		ss.getTransaction().commit();
		ss.close();

	}
	// TODO MEJORAR LA CONSULTA

	public void setTurnoPrimero(Juego j) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<JugadorEntity> jugadoresEntity = (List<JugadorEntity>) session
				.createQuery("from JugadorEntity where idJuego = ? ").setParameter(0, j.getId()).list();
		
		
		session.beginTransaction();

		jugadoresEntity.get(0).setTieneTurno(true);

		session.saveOrUpdate(jugadoresEntity.get(0));

		session.getTransaction().commit();
		session.close();

	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

}