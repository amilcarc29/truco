package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.BazaEntity;
import entities.ChicoEntity;
import entities.JuegoEntity;
import entities.ManoEntity;
import excepciones.ManoException;
import hbt.HibernateUtil;
import negocio.Baza;
import negocio.Chico;
import negocio.Mano;

public class ManoDAO {

	private static ManoDAO instancia;

	public static ManoDAO getInstancia() {
		if (instancia == null)
			instancia = new ManoDAO();
		return instancia;
	}

	public ManoDAO() {
	}

	public int guardarMano(Chico chico, Mano mano) {
		ChicoEntity ch = null;
		// VER EXCEPCIONES
		ch = ChicoDAO.getInstancia().buscarChicoPorID(chico.getIdChico());

		ManoEntity me = new ManoEntity(ch);

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(me);
		session.getTransaction().commit();
		session.close();
		mano.setIdMano(me.getIdMano());

		return me.getIdMano();
	}

	public ManoEntity buscarManoPorID(int idMano) throws ManoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ManoEntity mano = (ManoEntity) session.createQuery("from ManoEntity where idMano = ?").setParameter(0, idMano)
				.uniqueResult();
		session.close();
		if (mano != null) {
			return mano;
		} else {
			throw new ManoException("La mano con id: " + idMano + "no existe en la base de datos.");
		}
	}

	public List<Mano> buscarManosdeChico(int idChico) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<Mano> manos = new ArrayList<>();
		List<ManoEntity> manosEnt = (List<ManoEntity>) session.createQuery("from ManoEntity where idChico = ? ")
				.setParameter(0, idChico).list();

		for (ManoEntity manoEntity : manosEnt) {
			manos.add(toNegocio(manoEntity));
		}

		return manos;

	}

	private Mano toNegocio(ManoEntity manoEntity) {
		// TODO Auto-generated method stub
		return new Mano(manoEntity.getIdMano());
	}
}
