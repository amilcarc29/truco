package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.UsuarioDTO;
import entities.JuegoEntity;
import entities.ParejaEntity;
import excepciones.CategoriaException;
import excepciones.JuegoException;
import excepciones.ParejaException;
import hbt.HibernateUtil;
import negocio.Chico;
import negocio.Juego;
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

	public int guardarJuegoLibreIndividual(Juego juego) throws ParejaException {
		ParejaEntity par1 = ParejaDAO.getInstancia().buscarParejaPorId(juego.getPareja1().getIdPareja());
		ParejaEntity par2 = ParejaDAO.getInstancia().buscarParejaPorId(juego.getPareja2().getIdPareja());

		JuegoEntity ent = new JuegoEntity(par1, par2, "LIBRE");

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ent);
		session.getTransaction().commit();
		session.close();

		// guarda el id de juego para los jugadores de la pareja
		ParejaDAO.getInstancia().actualizarJuego(juego.getPareja1().getIdPareja(), ent.getId());
		ParejaDAO.getInstancia().actualizarJuego(juego.getPareja2().getIdPareja(), ent.getId());
		
		
		
		
		return ent.getId();
	}

	public void guardarJuegoLibreEnPareja(Juego juego) throws ParejaException {
		ParejaEntity par1 = ParejaDAO.getInstancia().buscarParejaPorId(juego.getPareja1().getIdPareja());
		ParejaEntity par2 = ParejaDAO.getInstancia().buscarParejaPorId(juego.getPareja2().getIdPareja());
		JuegoEntity ent = new JuegoEntity(par1, par2, "ENPAREJA");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ent);
		session.getTransaction().commit();
		session.close();
	}

	public void guardarJuegoCerrado(Juego juego) throws ParejaException {
		ParejaEntity par1 = ParejaDAO.getInstancia().buscarParejaPorId(juego.getPareja1().getIdPareja());
		ParejaEntity par2 = ParejaDAO.getInstancia().buscarParejaPorId(juego.getPareja2().getIdPareja());
		JuegoEntity ent = new JuegoEntity(par1, par2, "CERRADA");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ent);
		session.getTransaction().commit();
		session.close();
	}

	public JuegoEntity buscarJuegoPorID(int idJuego) throws ParejaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JuegoEntity juego = (JuegoEntity) session.createQuery("from JuegoEntity where idJuego = ? ").setParameter(0, idJuego).uniqueResult();
		session.close();
		if (juego != null) {
			return juego;
		} else {
			throw new ParejaException("El juego con id: " + idJuego + "no existe en la base de datos.");
		}
	}

	public List<Juego> buscarJuegosActivos(UsuarioDTO usuario) throws CategoriaException {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Juego> juegNeg = new ArrayList<>();
		List<JuegoEntity> juegos = (List<JuegoEntity>) session.createSQLQuery(
				"select j.idJuego, j.idPareja1, j.idPareja2, j.idParejaGanadora, j.tipoDeJuego, j.fecha,  j.activo"
						+ " from Juego j, Pareja p, Jugador ju where (j.idPareja1 = p.idPareja or j.idPareja2 = p.idPareja)"
						+ "and ju.idPareja = p.idPareja and ju.idUsuario = ?")
				.addEntity(JuegoEntity.class).setParameter(0, usuario.getIdUsuario()).list();
		session.close();
		for (JuegoEntity juegoEntity : juegos) {
			juegNeg.add(toNegocio(juegoEntity));
		}
		return juegNeg;
	}

	public Juego toNegocio(JuegoEntity juegoEntity) throws CategoriaException {
		Juego j = null;
		if (juegoEntity.getTipoDeJuego().equals("LIBRE")) {
			j = new ModalidadLibreIndividual();
			List<Chico> chicos = ChicoDAO.getInstancia().getChicos(juegoEntity.getId());
			j.setChico(chicos);
			j.setId(juegoEntity.getId());
		}
		return j;
	}

	public Juego buscarJuego(int idJuego) throws CategoriaException, JuegoException {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JuegoEntity juegoEntity = (JuegoEntity) session.createQuery("from JuegoEntity where idJuego = ?").setParameter(0, idJuego).uniqueResult();
		session.close();
		if (juegoEntity != null) {
			return toNegocio(juegoEntity);
		} else {
			throw new JuegoException("El juego con id: " + idJuego + "no existe en la base de datos.");
		}
	}

	public List<Juego> getJuegosActivos() throws CategoriaException {

		List<Juego> juegNeg = new ArrayList<>();

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<JuegoEntity> juegos = (List<JuegoEntity>) session.createQuery("from JuegoEntity where activo = 1 ").list();
		session.close();

		for (JuegoEntity juegoEntity : juegos) {
			juegNeg.add(toNegocio(juegoEntity));
		}

		return juegNeg;
	}
}
