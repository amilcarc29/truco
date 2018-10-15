package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.JugadorEntity;
import entities.MiembroEntity;
import entities.ParejaEntity;
import entities.UsuarioEntity;
import excepciones.CategoriaException;
import excepciones.MiembroException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.JugadorGrupal;
import negocio.JugadorIndividual;
import negocio.Miembro;
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

	public Pareja guardarParejaIndividual(Pareja pareja) throws CategoriaException {
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
		
		return toNegocio(pe);
		

	}

	public Pareja guardarParejaGrupal(Pareja pareja) throws CategoriaException, MiembroException {
		MiembroEntity mi1 = null;
		MiembroEntity mi2 = null;
		JugadorGrupal ju1 = (JugadorGrupal) pareja.getJugador1();
		JugadorGrupal ju2 = (JugadorGrupal) pareja.getJugador2();
		try {
			mi1 = MiembroDAO.getInstancia().buscarMiembroByIdEntity(ju1.getMiembro().getIdMiembro());
			mi2 = MiembroDAO.getInstancia().buscarMiembroByIdEntity(ju2.getMiembro().getIdMiembro());
		} catch (MiembroException e) {
			e.printStackTrace();
		}

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session ss = sf.openSession();
		ss.beginTransaction();

		// alta de jugadores

		JugadorEntity je1 = new JugadorEntity(null, null, mi1, "grupal");
		ss.saveOrUpdate(je1);

		JugadorEntity je2 = new JugadorEntity(null, null, mi2, "grupal");

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

		if (pe != null) {
			return toNegocio(pe);
		} else {
			throw new MiembroException("Error al guardar la pareja");
		}

	}

	private Pareja toNegocio(ParejaEntity pe) throws CategoriaException {
		// TODO Auto-generated method stub

		Pareja p = new Pareja(JugadorDAO.getInstancia().toNegocio(pe.getJugador1()),
				JugadorDAO.getInstancia().toNegocio(pe.getJugador2()));
		p.setIdPareja(pe.getIdPareja());
		return p;
	}

}
