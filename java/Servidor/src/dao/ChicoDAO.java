package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.ChicoEntity;
import entities.JuegoEntity;
import entities.JugadorEntity;
import entities.ParejaEntity;
import excepciones.CategoriaException;
import excepciones.ParejaException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Chico;
import negocio.Juego;
import negocio.Pareja;

public class ChicoDAO {

	private static ChicoDAO instancia;

	public static ChicoDAO getInstancia() {
		if (instancia == null)
			instancia = new ChicoDAO();
		return instancia;
	}

	public ChicoDAO() {
	}

	public void guardarChico(Juego juego, Chico chico) throws ParejaException {

		JuegoEntity jue = null;

		try {
			jue = JuegoDAO.getInstancia().buscarJuegoPorID(juego.getId());
		} catch (ParejaException e) {
			e.printStackTrace();
		}

		ChicoEntity ch = new ChicoEntity(jue, null, chico.getPuntosPorGanar());

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ch);
		session.getTransaction().commit();
		session.close();
		
		
		
		
		
	}

	public List<Chico> getChicos(int idJuego) throws CategoriaException {
		List<Chico> ch = new ArrayList<>();

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<ChicoEntity> chicos = (List<ChicoEntity>) session.createQuery("from ChicoEntity where idJuego = ?")
				.setParameter(0, idJuego).list();

		for (ChicoEntity chicoent : chicos) {
			
			ch.add(toNegocio(chicoent));
		}

		session.close();

		return ch;
	}

	private Chico toNegocio(ChicoEntity chicoent) throws CategoriaException {
		List<Pareja> parejas = new ArrayList<>();
		
		parejas.add(ParejaDAO.getInstancia().toNegocio(chicoent.getJuego().getPareja1()));
		parejas.add(ParejaDAO.getInstancia().toNegocio(chicoent.getJuego().getPareja2()));
		Chico c = new Chico(parejas);
		c.setIdChico(chicoent.getIdChico());
		return c;
	}

}
