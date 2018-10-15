package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.JugadorEntity;
import entities.ParejaEntity;
import entities.UsuarioEntity;
import excepciones.CategoriaException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
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

	public void guardarParejaIndividual(Pareja pareja) throws CategoriaException {
		UsuarioEntity ue1 = null;
		UsuarioEntity ue2 = null;
		JugadorIndividual ju1 = (JugadorIndividual) pareja.getJugador1();
		JugadorIndividual ju2 = (JugadorIndividual) pareja.getJugador2();
		try {
			ue1 = UsuarioDAO.getInstancia().buscarUsuarioByIdEntity(ju1.getUsuario().getIdUsuario());
			ue2 = UsuarioDAO.getInstancia().buscarUsuarioByIdEntity(ju2.getUsuario().getIdUsuario());
		} catch (UsuarioException e) {
			e.printStackTrace();
		}
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
		ss.saveOrUpdate(pe);

		// udate de id de pareja en jugador
		je1.setPareja(pe);
		ss.saveOrUpdate(pe);

		je2.setPareja(pe);

		ss.saveOrUpdate(je1);

		ss.getTransaction().commit();
		ss.close();

	}

}
