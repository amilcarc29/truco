package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.BazaEntity;
import entities.ChicoEntity;
import entities.JuegoEntity;
import entities.JugadorEntity;
import entities.ManoEntity;
import entities.ParejaEntity;
import entities.PuntuacionEntity;
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

	public int guardarChico(Juego juego, Chico chico) throws ParejaException {

		JuegoEntity jue = null;
		ParejaEntity pa1 = null;
		ParejaEntity pa2 = null;

		try {
			jue = JuegoDAO.getInstancia().buscarJuegoPorID(juego.getId());
		} catch (ParejaException e) {
			e.printStackTrace();
		}
		
		try {
			pa1 = ParejaDAO.getInstancia().buscarParejaPorId(chico.getParejas().get(0).getIdPareja());
			pa2 = ParejaDAO.getInstancia().buscarParejaPorId(chico.getParejas().get(1).getIdPareja());
		} catch (ParejaException e1){
			e1.printStackTrace();
		}

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		// alta de Chico
		
		ChicoEntity ch = new ChicoEntity(jue, null, chico.getPuntosPorGanar());
		session.saveOrUpdate(ch);

		// alta de puntuaciones

		PuntuacionEntity pe1 = new PuntuacionEntity(ch, pa1 , chico.getPuntosChico().get(0).getPuntos());
		session.saveOrUpdate(pe1);

		PuntuacionEntity pe2 = new PuntuacionEntity(ch, pa2, chico.getPuntosChico().get(1).getPuntos());
		session.saveOrUpdate(pe2);

		session.getTransaction().commit();
		session.close();
		
		

		
		return ch.getIdChico();
	}
	
	public ChicoEntity buscarChicoPorID(int idChico) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ChicoEntity chico = (ChicoEntity) session.createQuery("from ChicoEntity where idChico = ?")
				.setParameter(0, idChico).uniqueResult();
		session.close();
		if (chico != null) {
			return chico;
		} else {
			// pasarla ! a un metodo de busqueda nuevo throw new UsuarioException("El
			// usuario con apodo: " + apodo + "no existe en la base de datos.");
			return null;
		}
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
