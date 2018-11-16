package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CategoriaEntity;
import entities.JugadorEntity;
import entities.UsuarioEntity;
import excepciones.CategoriaException;
import excepciones.UsuarioException;
import hbt.HibernateUtil;
import negocio.Jugador;
import negocio.Usuario;

public class UsuarioDAO {

	private static UsuarioDAO instancia;

	public UsuarioDAO() {
	}

	public static UsuarioDAO getInstancia() {
		if (instancia == null) {
			instancia = new UsuarioDAO();
		}
		return instancia;
	}
	
	public Usuario obtenerUsuarioJuegoIndividual (Jugador jugador) throws UsuarioException, CategoriaException {
		JugadorEntity je = null;
		je = JugadorDAO.getInstancia().buscarJugadorById(jugador.getId());
		return toNegocio(je.getUsuario());
	}
	
	public void actualizarPuntajesUsuario(Usuario usuario) throws UsuarioException, CategoriaException {
		UsuarioEntity ue = null;
		ue = this.buscarUsuarioByIdEntity(usuario.getIdUsuario());
		
		ue.setPartidasGanadas(usuario.getPartidasGanadas());
		ue.setPartidasJugadas(usuario.getPartidasJugadas());
		ue.setPuntaje(usuario.getPuntaje());
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ue);
		session.getTransaction().commit();
		session.close();
		
	}

	
	public Usuario buscarUsuarioById(int idUsuario) throws UsuarioException, CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		UsuarioEntity usuarioEntity = (UsuarioEntity) session
				.createQuery("from UsuarioEntity where idUsuario = ? AND activo = 1").setParameter(0, idUsuario)
				.uniqueResult();
		session.close();
		if (usuarioEntity != null) {
			return toNegocio(usuarioEntity);
		} else {
			throw new UsuarioException("El usuario con id: " + idUsuario + "no existe en la base de datos.");
		}
	}

	public UsuarioEntity buscarUsuarioByIdEntity(int idUsuario) throws UsuarioException, CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		UsuarioEntity usuarioEntity = (UsuarioEntity) session
				.createQuery("from UsuarioEntity where idUsuario = ? and activo  = 1").setParameter(0, idUsuario)
				.uniqueResult();
		session.close();
		if (usuarioEntity != null) {
			return usuarioEntity;
		} else {
			throw new UsuarioException("El usuario con id: " + idUsuario + "no existe en la base de datos.");
		}
	}
	
	public void actualizarCategoria(Usuario usuario) throws UsuarioException, CategoriaException {
		CategoriaEntity cat = null;
		UsuarioEntity ue = null;
		
		ue = this.buscarUsuarioByIdEntity(usuario.getIdUsuario());
		
		try {
			cat = CategoriaDAO.getInstancia().buscarCategoriaByNombre(usuario.getCategoria().getNombre());
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

	public Usuario toNegocio(UsuarioEntity usuarioEntity) throws CategoriaException {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(usuarioEntity.getIdUsuario());
		usuario.setApodo(usuarioEntity.getApodo());
		usuario.setPartidasGanadas(usuarioEntity.getPartidasGanadas());
		usuario.setPartidasJugadas(usuarioEntity.getPartidasJugadas());
		usuario.setPuntaje(usuarioEntity.getPuntaje());
		usuario.setEmail(usuarioEntity.getEmail());
		usuario.setCategoria(CategoriaDAO.getInstancia().toNegocio(usuarioEntity.getCategoria()));
		usuario.setPass(usuarioEntity.getPass());
		return usuario;
	}

	public int guardarUsuario(Usuario usuario) throws CategoriaException {
		CategoriaEntity cat = null;
		UsuarioEntity ue = new UsuarioEntity(usuario.getPartidasGanadas(), usuario.getPartidasJugadas(),
				usuario.getPuntaje(), usuario.getApodo(), usuario.getPass(), usuario.getEmail(), usuario.getActivo());
		try {
			cat = CategoriaDAO.getInstancia().buscarCategoriaByNombre("NOVATO");
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
		
		return ue.getIdUsuario();
	}

	public Usuario buscarUsuarioByApodo(String apodo) throws CategoriaException, UsuarioException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		UsuarioEntity usuarioEntity = (UsuarioEntity) session.createQuery("from UsuarioEntity where apodo = ?")
				.setParameter(0, apodo).uniqueResult();
		session.close();
		if (usuarioEntity != null) {
			return toNegocio(usuarioEntity);
		} else {
			// pasarla ! a un metodo de busqueda nuevo 
			throw new UsuarioException("El usuario con apodo: " + apodo + "no existe en la base de datos.");
		}
	}

	public UsuarioEntity toEntity(Usuario usuario) throws CategoriaException {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario.getIdUsuario(), usuario.getPartidasGanadas(), usuario.getPuntaje(), usuario.getApodo(), usuario.getPass(),
				usuario.getEmail(), usuario.getActivo());
		CategoriaEntity categoriaEntity = CategoriaDAO.getInstancia().toEntity(usuario.getCategoria());
		usuarioEntity.setCategoria(categoriaEntity);
		return usuarioEntity;
	}

	public void acutualizarUsuario(Usuario usuario) throws UsuarioException, CategoriaException {
		// TODO Auto-generated method stub
		
		UsuarioEntity ue = new UsuarioEntity();
		ue = this.buscarUsuarioByIdEntity(usuario.getIdUsuario());
		ue.setEmail(usuario.getEmail());
		ue.setPass(usuario.getPass());
		ue.setApodo(usuario.getApodo());
		
		ue.setIdUsuario(usuario.getIdUsuario());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(ue);
		session.getTransaction().commit();
		session.close();
		
	}
}
