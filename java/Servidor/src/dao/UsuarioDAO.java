package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CategoriaEntity;
import entities.UsuarioEntity;
import excepciones.CategoriaException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Usuario;

public class UsuarioDAO {

	private static UsuarioDAO instancia;

	public UsuarioDAO() {
	}

	public static UsuarioDAO getInstancia() {
		if (instancia == null)
			instancia = new UsuarioDAO();
		return instancia;
	}

	public Usuario buscarUsuarioById(int idUsuario) throws UsuarioException, CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		UsuarioEntity usuarioEntity = (UsuarioEntity) session.createQuery("from Usuarios where idUsuario = ?")
				.setParameter(0, idUsuario).uniqueResult();
		session.close();
		if (usuarioEntity != null) {
			return toNegocio(usuarioEntity);
		} else {
			throw new UsuarioException("El usuario con id: " + idUsuario + "no existe en la base de datos.");
		}
	}

	public Usuario toNegocio(UsuarioEntity usuarioEntity) throws CategoriaException {

		// Usuario usuario = new Usuario(usuarioEntity.getIdUsuario(),
		// usuarioEntity.getPartidasGanadas(),
		// usuarioEntity.getPartidasPerdidas(), usuarioEntity.getPuntaje(),
		// usuarioEntity.getApodo(),
		// usuarioEntity.getPass(), usuarioEntity.getEmail(),
		// usuarioEntity.isActivo());
		// usuario.setCategoria(CategoriaDAO.getInstancia().toNegocio(usuarioEntity.getCategoria()));
		// return usuario;
		return null;
	}

	public void guardarUsuario(Usuario usuario) throws CategoriaException {
		CategoriaEntity cat = null;
		UsuarioEntity ue = new UsuarioEntity(usuario.getPartidasGanadas(), usuario.getPartidasJugadas(),
				usuario.getPuntaje(), usuario.getApodo(), usuario.getPass(), usuario.getEmail(), usuario.getActivo());

		try {

			cat = CategoriaDAO.getInstancia().buscarCategoriaById(1);

		} catch (CategoriaException e) {
			e.printStackTrace();

		}
		ue.setCategoria(cat);

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ue);
		session.getTransaction().commit();
		session.close();
	}

	//
	//
	// public UsuarioEntity toEntity(Usuario usuario) throws CategoriaException
	// {
	// UsuarioEntity usuarioEntity = new UsuarioEntity(usuario.getIdUsuario(),
	// usuario.getPartidasGanadas(),
	// usuario.getPartidasPerdidas(), usuario.getPuntaje(), usuario.getApodo(),
	// usuario.getPass(),
	// usuario.getEmail(), usuario.isActivo());
	// CategoriaEntity categoriaEntity =
	// CategoriaDAO.getInstancia().toEntity(usuario.getCategoria());
	// usuarioEntity.setCategoria(categoriaEntity);
	// return usuarioEntity;
	// }
}
