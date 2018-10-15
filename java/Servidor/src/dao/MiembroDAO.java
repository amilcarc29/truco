package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.MiembroEntity;
import excepciones.CategoriaException;
import excepciones.MiembroException;
import hbt.HibernateUtil;
import negocio.Miembro;

public class MiembroDAO {

	private static MiembroDAO instancia;

	public MiembroDAO() {
	}

	public static MiembroDAO getInstancia() {
		if (instancia == null) {
			instancia = new MiembroDAO();
		}
		return instancia;
	}

	public Miembro buscarMiembroById(int idMiembro) throws MiembroException, CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		MiembroEntity miembroEntity = (MiembroEntity) session.createQuery("from MiembroEntity where idMiembro = ?")
				.setParameter(0, idMiembro)
				.uniqueResult();
		session.close();
		if (miembroEntity != null) {
			return toNegocio(miembroEntity);
		} else {
			throw new MiembroException("El miembro con id: " + idMiembro + "no existe en la base de datos.");
		}
	}
	
	public MiembroEntity buscarMiembroByIdEntity(int idMiembro) throws MiembroException, CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		MiembroEntity miembroEntity = (MiembroEntity) session.createQuery("from MiembrosEntity where idMiembro = ?")
				.setParameter(0, idMiembro)
				.uniqueResult();
		session.close();
		if (miembroEntity != null) {
			return miembroEntity;
		} else {
			throw new MiembroException("El miembro con id: " + idMiembro + "no existe en la base de datos.");
		}
	}
	
	public Miembro buscarMiembro(int idUsuario, int idGrupo) throws CategoriaException, MiembroException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		MiembroEntity miembroEntity = (MiembroEntity) session.createQuery("from MiembrosEntity where idUsuario = ? and idGrupo = ?")
				.setParameter(0, idUsuario).setParameter(1, idGrupo);
		session.close();
		if (miembroEntity != null) {
			return toNegocio(miembroEntity);
		} else {
			throw new MiembroException("El miembro no existe en la base de datos.");
		}
	}

	public Miembro toNegocio(MiembroEntity miembroEntity) throws CategoriaException {
		Miembro miembro = new Miembro(miembroEntity.getIdMiembro(), miembroEntity.getPuntaje(), miembroEntity.isEnGrupo());
		miembro.setUsuario(UsuarioDAO.getInstancia().toNegocio(miembroEntity.getUsuario()));
		miembro.setGrupo(GrupoDAO.getInstancia().toNegocio(miembroEntity.getGrupo()));
		return miembro;
	}

	public void guardarMiembro(Miembro miembro) throws CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		MiembroEntity miembroEntity = toEntity(miembro);
		session.beginTransaction();
		session.saveOrUpdate(miembroEntity);
		session.getTransaction().commit();
		session.close();
	}

	public MiembroEntity toEntity(Miembro miembro) throws CategoriaException {
		MiembroEntity miembroEntity = new MiembroEntity(miembro.getIdMiembro(), miembro.getPuntaje(), miembro.isEnGrupo());
		miembroEntity.setUsuario(UsuarioDAO.getInstancia().toEntity(miembro.getUsuario()));
		miembroEntity.setGrupo(GrupoDAO.getInstancia().toEntity(miembro.getGrupo()));
		return miembroEntity;
	}
}
