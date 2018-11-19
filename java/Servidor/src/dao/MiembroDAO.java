package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.GrupoEntity;
import entities.MiembroEntity;
import entities.UsuarioEntity;
import excepciones.CategoriaException;
import excepciones.GrupoException;
import excepciones.MiembroException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Miembro;
import negocio.Usuario;

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

	public Miembro buscarMiembroById(int idMiembro) throws MiembroException, CategoriaException, UsuarioException {
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
	
	public Miembro buscarMiembro(int idUsuario, int idGrupo) throws CategoriaException, MiembroException, UsuarioException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		MiembroEntity miembroEntity = (MiembroEntity) session.createQuery("from MiembrosEntity where idUsuario = ? and idGrupo = ?")
				.setParameter(0, idUsuario).setParameter(1, idGrupo);
		session.close();
		if (miembroEntity != null) {
			return toNegocio(miembroEntity);
		} else {
			return null;
		}
	}

	public Miembro toNegocio(MiembroEntity miembroEntity) throws CategoriaException, UsuarioException {
		Usuario usuario = null;
		usuario = UsuarioDAO.getInstancia().buscarUsuarioById(miembroEntity.getUsuario().getIdUsuario());
				
		Miembro miembro = new Miembro(miembroEntity.getPuntaje(), miembroEntity.isEnGrupo(), usuario);
		miembro.setIdMiembro(miembroEntity.getIdMiembro());
		return miembro;
	}

	public int guardarMiembro(Miembro miembro, int idGrupo) throws CategoriaException, GrupoException, UsuarioException {
		GrupoEntity grupo = null;
		grupo = GrupoDAO.getInstancia().buscarGrupoByIdEntity(idGrupo);
		
		UsuarioEntity usuario = null;
		usuario = UsuarioDAO.getInstancia().buscarUsuarioByIdEntity(miembro.getUsuario().getIdUsuario());
				
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		
		MiembroEntity miembroEntity = new MiembroEntity(grupo, usuario, miembro.getPuntaje(), miembro.isEnGrupo());
				
		session.beginTransaction();
		session.saveOrUpdate(miembroEntity);
		session.getTransaction().commit();
		session.close();
		
		return miembro.getIdMiembro();
	}

}
