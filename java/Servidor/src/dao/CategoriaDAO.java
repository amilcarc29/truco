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

	public Categoria toNegocio(CategoriaEntity categoriaEntity) throws CategoriaException {
		return FactoryCategoria.getCategoria(categoriaEntity.getIdCategoria(), categoriaEntity.getNombre(), categoriaEntity.getScore(),
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
