package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.GrupoEntity;
import hbt.HibernateUtil;
import negocio.GrupoJuego;

public class GrupoJuegoDAO {
	private static GrupoJuegoDAO instancia;

	public GrupoJuegoDAO() {
	}

	public static GrupoJuegoDAO getInstancia() {
		if (instancia == null)
			instancia = new GrupoJuegoDAO();
		return instancia;
	}

	public GrupoJuego guardarGrupoJuego(GrupoJuego grupo) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		GrupoEntity grupoEntity = toEntity(grupo);
		session.beginTransaction();
		session.saveOrUpdate(grupoEntity);
		session.getTransaction().commit();
		session.close();
		return null;
	}

	private GrupoEntity toEntity(GrupoJuego grupo) {
		return null;
	}

}
