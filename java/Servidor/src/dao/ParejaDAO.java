package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CategoriaEntity;
import entities.JuegoEntity;
import entities.JugadorEntity;
import entities.MiembroEntity;
import entities.ParejaEntity;
import entities.UsuarioEntity;
import excepciones.CategoriaException;
import excepciones.ErrorCode;
import excepciones.MiembroException;
import excepciones.ParejaException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.JugadorGrupal;
import negocio.JugadorIndividual;
import negocio.Pareja;

public class ParejaDAO {

	private static ParejaDAO instancia;

	public ParejaDAO() {
	}

	public static ParejaDAO getInstancia() {
		if (instancia == null)
			instancia = new ParejaDAO();
		return instancia;
	}

	public Pareja guardarParejaIndividual(Pareja pareja) throws CategoriaException, UsuarioException, ParejaException {
		UsuarioEntity ue1 = null;
		UsuarioEntity ue2 = null;
		JugadorIndividual ju1 = (JugadorIndividual) pareja.getJugador1();
		JugadorIndividual ju2 = (JugadorIndividual) pareja.getJugador2();
		CategoriaEntity cat = null;

		ue1 = UsuarioDAO.getInstancia().buscarUsuarioByIdEntity(ju1.getUsuario().getIdUsuario());
		ue2 = UsuarioDAO.getInstancia().buscarUsuarioByIdEntity(ju2.getUsuario().getIdUsuario());

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session ss = sf.openSession();
		ss.beginTransaction();

		// alta de jugadores

		JugadorEntity je1 = new JugadorEntity(ue1, null, null, "individual");
		ss.saveOrUpdate(je1);

		JugadorEntity je2 = new JugadorEntity(ue2, null, null, "individual");
		ss.saveOrUpdate(je2);
		
		
	

		// alta de pareja

		ParejaEntity pe = new ParejaEntity(je1, je2);
		pe.setCategoriaMayor(null);
		ss.saveOrUpdate(pe);

		je1.setPareja(pe);
		ss.saveOrUpdate(je1);


		je2.setPareja(pe);
		ss.saveOrUpdate(je2);


		pareja.setIdPareja(pe.getIdPareja());

		Pareja pc = this.toNegocio(pe);
		
		
		
		ss.getTransaction().commit();
		ss.close();

		
		ss = sf.openSession();
		ss.beginTransaction();
		
		pe = this.buscarParejaPorId(pc.getIdPareja());
		try {
			cat = CategoriaDAO.getInstancia().buscarCategoriaById(pc.obtenerMayorCategoria().getIdCategoria());
		} catch (CategoriaException e) {
			throw e;
		}
		pe.setCategoriaMayor(cat);

		ss.saveOrUpdate(pe);
		

		ss.getTransaction().commit();
		ss.close();


		return toNegocio(this.buscarParejaPorId(pe.getIdPareja()));
	}

	public void aumentarPuntos(Pareja pareja) throws ParejaException {
		ParejaEntity pa = null;
		try {
			pa = this.buscarParejaPorId(pareja.getIdPareja());
		} catch (ParejaException e) {
			throw e;
		}
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(pa);
		session.getTransaction().commit();
		session.close();

	}

	public Pareja guardarParejaGrupal(Pareja pareja) throws CategoriaException, MiembroException, UsuarioException, ParejaException {
		MiembroEntity mi1 = null;
		MiembroEntity mi2 = null;
		JugadorGrupal ju1 = (JugadorGrupal) pareja.getJugador1();
		JugadorGrupal ju2 = (JugadorGrupal) pareja.getJugador2();
		CategoriaEntity cat = null;
		
		try {
			mi1 = MiembroDAO.getInstancia().buscarMiembroByIdEntity(ju1.getMiembro().getIdMiembro());
			mi2 = MiembroDAO.getInstancia().buscarMiembroByIdEntity(ju2.getMiembro().getIdMiembro());
		} catch (MiembroException e) {
			throw e;
		}

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session ss = sf.openSession();
		ss.beginTransaction();

		// alta de jugadores

		JugadorEntity je1 = new JugadorEntity(null, null, mi1, "individual");
		ss.saveOrUpdate(je1);

		JugadorEntity je2 = new JugadorEntity(null, null, mi2, "individual");
		ss.saveOrUpdate(je2);
		
			

		// alta de pareja

		ParejaEntity pe = new ParejaEntity(je1, je2);
		pe.setCategoriaMayor(null);
		ss.saveOrUpdate(pe);

		je1.setPareja(pe);
		ss.saveOrUpdate(je1);


		je2.setPareja(pe);
		ss.saveOrUpdate(je2);


		pareja.setIdPareja(pe.getIdPareja());

		Pareja pc = this.toNegocio(pe);
		
		
		
		ss.getTransaction().commit();
		ss.close();

		
		ss = sf.openSession();
		ss.beginTransaction();
		
		pe = this.buscarParejaPorId(pc.getIdPareja());
		try {
			cat = CategoriaDAO.getInstancia().buscarCategoriaById(pc.obtenerMayorCategoriaCerrada().getIdCategoria());
		} catch (CategoriaException e) {
			throw e;
		}
		pe.setCategoriaMayor(cat);

		ss.saveOrUpdate(pe);
		

		ss.getTransaction().commit();
		ss.close();


		return toNegocio(this.buscarParejaPorId(pe.getIdPareja()));
	}
	
	public Pareja toNegocioDespues(ParejaEntity pe) throws CategoriaException, UsuarioException {
		// TODO Auto-generated method stub
		Pareja p = new Pareja(JugadorDAO.getInstancia().toNegocio(pe.getJugador1()),
				JugadorDAO.getInstancia().toNegocio(pe.getJugador2()));
		p.setIdPareja(pe.getIdPareja());
		p.setCategoriaMayor(CategoriaDAO.getInstancia().buscarCategoriaByIdNegocio(pe.getCategoriaMayor().getIdCategoria()));
		return p;
	}

	public Pareja toNegocio(ParejaEntity pe) throws CategoriaException, UsuarioException {
		// TODO Auto-generated method stub
		Pareja p = new Pareja(JugadorDAO.getInstancia().toNegocio(pe.getJugador1()),
				JugadorDAO.getInstancia().toNegocio(pe.getJugador2()));
		p.setIdPareja(pe.getIdPareja());
		return p;
	}
	
	public Pareja buscarParejaPorIdNegocio(int idPareja) throws CategoriaException, UsuarioException, ParejaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ParejaEntity parejaEnt = (ParejaEntity) session.createQuery("from ParejaEntity where idPareja = ? ")
				.setParameter(0, idPareja).uniqueResult();
		session.close();
		if (parejaEnt != null) {
			return toNegocioDespues(parejaEnt);
		} else {
			throw new ParejaException("La pareja con id: " + idPareja + " no existe en la base de datos.");
		} // TODO Auto-generated method stub
	}

	public ParejaEntity buscarParejaPorId(int idPareja) throws ParejaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ParejaEntity parejaEnt = (ParejaEntity) session.createQuery("from ParejaEntity where idPareja = ? ")
				.setParameter(0, idPareja).uniqueResult();
		session.close();
		if (parejaEnt != null) {
			return parejaEnt;
		} else {
			throw new ParejaException("La pareja con id: " + idPareja + " no existe en la base de datos.");
		} // TODO Auto-generated method stub
	}

	public Pareja buscarParejaDeUnJugador(int idJugador) throws CategoriaException, UsuarioException, ParejaException {
		ParejaEntity parejaEnt = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		parejaEnt = (ParejaEntity) session.createQuery("from ParejaEntity where idJugador1 = ? or idJugador2 = ?")
				.setParameter(0, idJugador).setParameter(1, idJugador).uniqueResult();
		session.close();
		if (parejaEnt != null) {
			return toNegocioDespues(parejaEnt);
		}
		throw new ParejaException("La pareja con el jugador de id: " + idJugador + " no existe en la base de datos.");
	}

	public void actualizarJuego(int idPareja, int idJuego) throws ParejaException {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<JugadorEntity> jugadorEntity = (List<JugadorEntity>) session
				.createQuery("from JugadorEntity where idPareja = ?").setParameter(0, idPareja).list();

		JuegoEntity je = JuegoDAO.getInstancia().buscarJuegoPorID(idJuego);

		session.beginTransaction();

		for (JugadorEntity jugadorEnt : jugadorEntity) {
			jugadorEnt.setJuego(je);
			session.saveOrUpdate(jugadorEnt);
		}

		session.getTransaction().commit();
		session.close();
	}
}
