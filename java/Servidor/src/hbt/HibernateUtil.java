package hbt;

import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entities.BazaEntity;
import entities.CartaEntity;
import entities.CategoriaEntity;
import entities.ChicoEntity;
import entities.GrupoEntity;
import entities.JuegoEntity;
import entities.JugadaEntity;
import entities.JugadorCartaEntity;
import entities.JugadorEntity;
import entities.ManoEntity;
import entities.MiembroEntity;
import entities.ParejaEntity;
import entities.PuntuacionEntity;
import entities.UsuarioEntity;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;

	static {
		try {
			
			AnnotationConfiguration config = new AnnotationConfiguration();
			config.addAnnotatedClass(UsuarioEntity.class);
			config.addAnnotatedClass(CategoriaEntity.class);
			config.addAnnotatedClass(ParejaEntity.class);
			config.addAnnotatedClass(JugadorEntity.class);
			config.addAnnotatedClass(MiembroEntity.class);
			config.addAnnotatedClass(GrupoEntity.class);
			config.addAnnotatedClass(JuegoEntity.class);
			config.addAnnotatedClass(ChicoEntity.class);
			config.addAnnotatedClass(ManoEntity.class);
			config.addAnnotatedClass(PuntuacionEntity.class);
			config.addAnnotatedClass(ChicoEntity.class);
			config.addAnnotatedClass(BazaEntity.class);
			config.addAnnotatedClass(CartaEntity.class);
			config.addAnnotatedClass(JugadaEntity.class);
			config.addAnnotatedClass(JugadorCartaEntity.class);

			sessionFactory = config.buildSessionFactory();
			
			
		} catch (Throwable ex) {
			JOptionPane.showMessageDialog(null, ("Initial SessionFactory creation failed." + ex));
			throw new ExceptionInInitializerError(ex);
		}
	}
	

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
