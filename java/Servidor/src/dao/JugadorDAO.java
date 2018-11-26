package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CategoriaEntity;
import entities.ChicoEntity;
import entities.JugadorEntity;
import entities.Tanto;
import entities.TantoEntity;
import entities.UsuarioEntity;
import excepciones.CategoriaException;
import excepciones.JuegoException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Juego;
import negocio.Jugador;
import negocio.JugadorIndividual;
import negocio.Pareja;

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
	
	public void actualizarPuntosEnvido(Jugador jugador) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JugadorEntity jugadorEntity = (JugadorEntity) session
				.createQuery("from JugadorEntity where idJugador = ?")
				.setParameter(0, jugador.getId()).uniqueResult();
		
		jugadorEntity.setPuntosEnvido(jugador.getPuntosEnvido());
		session.beginTransaction();
		session.saveOrUpdate(jugadorEntity);
		session.getTransaction().commit();
		session.close();
	}

	public void setTieneQueContestar(Pareja pareja, String tanto) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JugadorEntity jugadorEntity = (JugadorEntity) session
				.createQuery("from JugadorEntity where idPareja = ? and (orden = 2 or orden = 3)")
				.setParameter(0, pareja.getIdPareja()).uniqueResult();

		TantoEntity tantoEnt = (TantoEntity) session.createQuery("from TantoEntity where tanto = ?")
				.setParameter(0, tanto).uniqueResult();

		jugadorEntity.setTanto(tantoEnt);
		session.beginTransaction();
		session.saveOrUpdate(jugadorEntity);
		session.getTransaction().commit();
		session.close();
	}

	public void inicializarContestar(Jugador jugador) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JugadorEntity jugadorEntity = (JugadorEntity) session.createQuery("from JugadorEntity where idJugador = ?")
				.setParameter(0, jugador.getId()).uniqueResult();
		jugadorEntity.setTanto(null);
		session.beginTransaction();
		session.saveOrUpdate(jugadorEntity);
		session.getTransaction().commit();
		session.close();
	}

	public void actualizarTurno(Jugador jugador) throws UsuarioException, CategoriaException {
		JugadorEntity jugadorEntity = null;
		jugadorEntity = this.buscarJugadorById(jugador.getId());
		jugadorEntity.setTieneTurno(jugador.isTieneTurno());

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(jugadorEntity);
		session.getTransaction().commit();
		session.close();
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

	public Jugador toNegocio(JugadorEntity pe) throws CategoriaException, UsuarioException {
		Jugador j = null;
		if (pe.getTipo().equals("individual")) {
			j = new JugadorIndividual(UsuarioDAO.getInstancia().toNegocio(pe.getUsuario()));
			j.setTieneTurno(pe.geTieneTurno());
			j.setOrden(pe.getOrden());
			j.setTanto((pe.getTanto()==null)?null:pe.getTanto().getTanto());
			j.setId(pe.getIdJugador());
			j.setPuntosEnvido(pe.getPuntosEnvido());
			j.setCartas(JugadorCartaDAO.getInstancia().getTodasCartasbyJugador(j));
			j.setPuntosEnvido(pe.getPuntosEnvido());

		}
		return j;
	}

	public Jugador buscarJugadorByUsario(int idJuego, int idUsuario) throws CategoriaException, UsuarioException {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JugadorEntity jugadorEntity = (JugadorEntity) session
				.createQuery("from JugadorEntity where idJuego = ? and idUsuario = ?").setParameter(0, idJuego)
				.setParameter(1, idUsuario).uniqueResult();
		session.close();
		if (jugadorEntity != null) {
			return toNegocio(jugadorEntity);
		}
		throw new UsuarioException("El jugador con id: " + idUsuario + "no existe en el juego: " + idJuego);
	}

	public List<Jugador> buscarJugadoresByJuego(int idJuego) throws CategoriaException, UsuarioException {
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

	public Jugador getJugadorConTurno(Juego juego) throws CategoriaException, UsuarioException, JuegoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JugadorEntity jugadorEntity = (JugadorEntity) session
				.createQuery("from JugadorEntity where idJuego = ? and tieneTurno=1").setParameter(0, juego.getId())
				.uniqueResult();
		session.close();
		if (jugadorEntity != null) {
			return toNegocio(jugadorEntity);
		}
		throw new JuegoException("El juego con id: " + juego.getId() + "no existe en la base de datos.");
	}

	public List<Jugador> getJugadores(int idJuego) throws CategoriaException, UsuarioException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<JugadorEntity> jugadoresEntity = (List<JugadorEntity>) session
				.createQuery("from JugadorEntity where idJuego = ? order by ORDEN asc").setParameter(0, idJuego).list();

		session.close();
		List<Jugador> jugadores = new ArrayList<>();

		for (JugadorEntity je : jugadoresEntity) {
			jugadores.add(toNegocio(je));
		}

		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public void getPasarTurno(Juego juego) throws CategoriaException, UsuarioException, JuegoException {
		// TODO Auto-generated method stub

		Jugador jturno = this.getJugadorConTurno(juego);
		// fue la ultima
		if ((jturno.getOrden() + 1) == 4)
			return;

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		JugadorEntity jugadorAnterior = this.buscarJugadorById(jturno.getId());

		session.beginTransaction();
		jugadorAnterior.setTieneTurno(false);
		session.saveOrUpdate(jugadorAnterior);

		session.getTransaction().commit();

		JugadorEntity jugadorSiguiente = (JugadorEntity) session
				.createQuery("from JugadorEntity where idJuego = ? and orden = ?").setParameter(0, juego.getId())
				.setParameter(1, (jturno.getOrden() + 1)).uniqueResult();

		session.beginTransaction();
		jugadorSiguiente.setTieneTurno(true);

		session.saveOrUpdate(jugadorSiguiente);
		session.getTransaction().commit();

		session.close();
	}

	public void actualizarTurnos(List<Jugador> jugadores) throws UsuarioException, CategoriaException {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		int i = 0;

		JugadorEntity jePrimero = JugadorDAO.getInstancia().buscarJugadorById(jugadores.get(0).getId());
		session.beginTransaction();
		jePrimero.setTieneTurno(true);
		session.saveOrUpdate(jePrimero);
		jePrimero.setOrden(i);
		session.getTransaction().commit();

		i++;

		for (int x = 1; x < jugadores.size(); x++) {
			JugadorEntity je = JugadorDAO.getInstancia().buscarJugadorById(jugadores.get(x).getId());
			session.beginTransaction();
			je.setOrden(i);
			je.setTieneTurno(false);
			session.saveOrUpdate(je);
			session.getTransaction().commit();
			i++;
		}

		session.close();

	}

	public boolean tieneQueContestar(Jugador j) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JugadorEntity jugadorEntity = (JugadorEntity) session.createQuery("from JugadorEntity where idJugador = ?")
				.setParameter(0, j.getId()).uniqueResult();
		session.close();
		return jugadorEntity.getTanto() != null;
	}

	public void finalizar(Jugador jug) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		JugadorEntity jugador = (JugadorEntity) session.createQuery("from JugadorEntity where idJugador = ?")
				.setParameter(0, jug.getId()).uniqueResult();

		session.beginTransaction();
		jugador.setTieneTurno(false);
		session.beginTransaction();

		session.saveOrUpdate(jugador);
		session.getTransaction().commit();
	}

}