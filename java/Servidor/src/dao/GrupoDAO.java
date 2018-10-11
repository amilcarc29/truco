package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.GrupoEntity;
import excepciones.CategoriaException;
import excepciones.GrupoException;
import hbt.HibernateUtil;
import negocio.Grupo;

public class GrupoDAO {

	private static GrupoDAO instancia;

	public GrupoDAO() {
	}

	public static GrupoDAO getInstancia() {
		if (instancia == null)
			instancia = new GrupoDAO();
		return instancia;
	}

	public Grupo buscarGrupoById(int idGrupo) throws GrupoException, CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		GrupoEntity grupoEntity = (GrupoEntity) session.createQuery("from Grupos where idGrupo = ?")
				.setParameter(0, idGrupo)
				.uniqueResult();
		session.close();
		if (grupoEntity != null) {
			return toNegocio(grupoEntity);
		} else {
			throw new GrupoException("El grupo con id: " + idGrupo + "no existe en la base de datos");
		}
	}

	public Grupo toNegocio(GrupoEntity grupoEntity) throws CategoriaException {
		Grupo grupo = new Grupo(grupoEntity.getIdGrupo(), grupoEntity.getNombre());
		grupo.setAdministrador(UsuarioDAO.getInstancia().toNegocio(grupoEntity.getAdministrador()));
		return grupo;
	}

	public void guardarGrupo(Grupo grupo) throws CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		GrupoEntity grupoEntity = toEntity(grupo);
		session.beginTransaction();
		session.saveOrUpdate(grupoEntity);
		session.getTransaction().commit();
		session.close();
	}

	public GrupoEntity toEntity(Grupo grupo) throws CategoriaException {
		return new GrupoEntity(grupo.getIdGrupo(), UsuarioDAO.getInstancia().toEntity(grupo.getAdministrador()), grupo.getNombre());
	}
}
