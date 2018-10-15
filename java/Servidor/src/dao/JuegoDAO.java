package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.JuegoDTO;
import dto.UsuarioDTO;
import entities.GrupoEntity;
import entities.JuegoEntity;
import entities.JugadorEntity;
import entities.ParejaEntity;
import entities.UsuarioEntity;
import excepciones.CategoriaException;
import excepciones.GrupoException;
import excepciones.ParejaException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Juego;
import negocio.Jugador;
import negocio.JugadorIndividual;
import negocio.ModalidadLibreIndividual;
import negocio.Pareja;
import negocio.Usuario;

public class JuegoDAO {
	private static JuegoDAO instancia;

	public static JuegoDAO getInstancia() {
		if (instancia == null)
			instancia = new JuegoDAO();
		return instancia;
	}

	public JuegoDAO() {
	}

	public void guardarJuegoLibreIndividual(Juego juego, String tipo)
			throws UsuarioException, CategoriaException, ParejaException {

		ParejaEntity par1 = ParejaDAO.getInstancia().buscarParejaPorId(juego.getParejas().get(0).getIdPareja());
		ParejaEntity par2 = ParejaDAO.getInstancia().buscarParejaPorId(juego.getParejas().get(0).getIdPareja());

		JuegoEntity ent = new JuegoEntity(par1, par2, tipo);

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ent);
		session.getTransaction().commit();
		session.close();
	}

	public List<Juego> buscarJuegosActivos(UsuarioDTO usuario) throws CategoriaException {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Juego> juegNeg = new ArrayList<>();

		List<JuegoEntity> juegos = (List<JuegoEntity>) session.createSQLQuery(
				"select j.idJuego,  j.idPareja1,  j.idPareja2,  j.idParejaGanadora,  j.tipoDeJuego, j.fecha,  j.activo"
						+ "   from Juego j, Pareja p,Jugador ju  where ( j.idPareja1  = p.idPareja or  j.idPareja2  = p.idPareja) "
						+ "and ju.idPareja = p.idPareja and ju.idUsuario = ?")
				.addEntity(JuegoEntity.class)
				.setParameter(0, usuario.getIdUsuario()).list();

		session.close();

		for (JuegoEntity juegoEntity : juegos) {

			juegNeg.add(toNegocio(juegoEntity));

		}

		return juegNeg;
	}

	public Juego toNegocio(JuegoEntity juegoEntity) throws CategoriaException {
		Juego j  = null;

		if (juegoEntity.getTipoDeJuego().equals("LIBRE")) {
			j = new ModalidadLibreIndividual();
			j.setId(juegoEntity.getId());
		}
		
		return j;
	}
}
