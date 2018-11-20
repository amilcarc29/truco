package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.CategoriaEntity;
import excepciones.CategoriaException;
import hbt.HibernateUtil;
import negocio.Categoria;
import negocio.FactoryCategoria;

public class CategoriaDAO {

	private static CategoriaDAO instancia;

	public CategoriaDAO() {
	}

	public static CategoriaDAO getInstancia() {
		if (instancia == null) {
			instancia = new CategoriaDAO();
		}
		return instancia;
	}

	public CategoriaEntity buscarCategoriaById(int idCategoria) throws CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		CategoriaEntity categoriaEntity = (CategoriaEntity) session.createQuery("from CategoriaEntity where idCategoria = ?")
				.setParameter(0, idCategoria).uniqueResult();
		session.close();
		if (categoriaEntity != null) {
			return categoriaEntity;
		} else {
			throw new CategoriaException("La categoria con id: " + idCategoria + "no existe en la base de datos.");
		}
	}
	public CategoriaEntity buscarCategoriaByNombre(String nombre) throws CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		CategoriaEntity categoriaEntity = (CategoriaEntity) session.createQuery("from CategoriaEntity where nombre = ?")
				.setParameter(0, nombre).uniqueResult();
		session.close();
		
		if (categoriaEntity != null) {
			return categoriaEntity;
		} else {
			throw new CategoriaException("La categoria con Nombre: " + nombre + "no existe en la base de datos.");
		}
	}
	
	public Categoria buscarCategoriaByNombreNegocio(String nombre) throws CategoriaException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		CategoriaEntity categoriaEntity = (CategoriaEntity) session.createQuery("from CategoriaEntity where nombre = ?")
				.setParameter(0, nombre).uniqueResult();
		session.close();
		if (categoriaEntity != null) {
			return toNegocio(categoriaEntity);
		} else {
			throw new CategoriaException("La categoria con Nombre: " + nombre + "no existe en la base de datos.");
		}
	}

	public Categoria toNegocio(CategoriaEntity categoriaEntity) throws CategoriaException {
		/*
		 switch (getNombre()) {
		case "NOVATO":
			return new Novato(idCategoria, nombre, score, minimoPartidas, minimoPuntos, promedioMinimo);
		case "MASTER":
			return new Master(idCategoria, nombre, score, minimoPartidas, minimoPuntos, promedioMinimo);
		case "CALIFICADO":
			return new Calificado(idCategoria, nombre, score, minimoPartidas, minimoPuntos, promedioMinimo);
		case "EXPERTO":
			return new Experto(idCategoria, nombre, score, minimoPartidas, minimoPuntos, promedioMinimo);
		default:
			return null;
		}
		 */
		return FactoryCategoria.getCategoria(categoriaEntity.getIdCategoria(), categoriaEntity.getNombre(),
				categoriaEntity.getScore(),
				categoriaEntity.getMinimoPartida(), categoriaEntity.getMinimoPuntos(),
				categoriaEntity.getPromedioMinimo());
	}

	public void guardarCategoria(Categoria categoria) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		CategoriaEntity categoriaEntity = toEntity(categoria);
		session.beginTransaction();
		session.saveOrUpdate(categoriaEntity);
		session.getTransaction().commit();
		session.close();
	}

	public CategoriaEntity toEntity(Categoria categoria) {
		return new CategoriaEntity(categoria.getIdCategoria(), categoria.getNombre(), categoria.getScore(), categoria.getMinimoPartida(), categoria.getMinimoPuntos(),
				categoria.getPromedioMinimo());
	}
}
