package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.GrupoEntity;
import entities.UsuarioEntity;
import excepciones.CategoriaException;
import excepciones.GrupoException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Grupo;
import negocio.Usuario;

public class GrupoDAO {

	private static GrupoDAO instancia;

	public GrupoDAO() {
	}

	public static GrupoDAO getInstancia() {
		if (instancia == null)
			instancia = new GrupoDAO();
		return instancia;
	}
	
	public GrupoEntity buscarGrupoByIdEntity(int idGrupo) throws GrupoException, CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		GrupoEntity grupoEntity = (GrupoEntity) session.createQuery("from GrupoEntity where idGrupo = ?")
				.setParameter(0, idGrupo)
				.uniqueResult();
		session.close();
		if (grupoEntity != null) {
			return grupoEntity;
		} else {
			throw new GrupoException("El grupo con id: " + idGrupo + "no existe en la base de datos");
		}
	}

	public Grupo buscarGrupoById(int idGrupo) throws GrupoException, CategoriaException, UsuarioException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		GrupoEntity grupoEntity = (GrupoEntity) session.createQuery("from GrupoEntity where idGrupo = ?")
				.setParameter(0, idGrupo)
				.uniqueResult();
		session.close();
		if (grupoEntity != null) {
			return toNegocio(grupoEntity);
		} else {
			throw new GrupoException("El grupo con id: " + idGrupo + "no existe en la base de datos");
		}
	}
	
	public Grupo buscarGrupoByNombre(String nombre) throws CategoriaException, UsuarioException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		GrupoEntity grupoEntity = (GrupoEntity) session.createQuery("from GrupoEntity where nombre = ?")
				.setParameter(0, nombre)
				.uniqueResult();
		session.close();
		if (grupoEntity != null)
			return toNegocio(grupoEntity);
		else
			return null;		
	}

	public Grupo toNegocio(GrupoEntity grupoEntity) throws CategoriaException, UsuarioException {
		Usuario administrador = null;
		administrador = UsuarioDAO.getInstancia().buscarUsuarioById(grupoEntity.getAdministrador().getIdUsuario());
		
		Grupo grupo = new Grupo(grupoEntity.getNombre(), administrador);
		return grupo;
	}

	public int guardarGrupo(Grupo grupo) throws CategoriaException {
		UsuarioEntity admin = null;
		try {
			admin = UsuarioDAO.getInstancia().buscarUsuarioByIdEntity(grupo.getAdministrador().getIdUsuario());
		} catch (UsuarioException e) {
			e.printStackTrace();
		}
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		GrupoEntity grupoEntity = new GrupoEntity(grupo.getIdGrupo(), admin, grupo.getNombre());
		session.beginTransaction();
		session.saveOrUpdate(grupoEntity);
		session.getTransaction().commit();
		session.close();
		return grupoEntity.getIdGrupo();
	}

	public void bajaGrupo(Grupo grupo) throws GrupoException, CategoriaException {
		GrupoEntity grupoEntity = null;
		grupoEntity = this.buscarGrupoByIdEntity(grupo.getIdGrupo());
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		grupoEntity.setActivo(false);
		session.beginTransaction();
		session.saveOrUpdate(grupoEntity);
		session.getTransaction().commit();
		session.close();	
		
	}

}
