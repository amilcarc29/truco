package hbt;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entities.CategoriaEntity;
import entities.GrupoEntity;
import entities.JugadorEntity;
import entities.MiembroEntity;
import entities.ParejaEntity;
import entities.UsuarioEntity;
import negocio.Usuario;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
             config.addAnnotatedClass(UsuarioEntity.class);
             config.addAnnotatedClass(CategoriaEntity.class);
             config.addAnnotatedClass(ParejaEntity.class);
             config.addAnnotatedClass(JugadorEntity.class);
             config.addAnnotatedClass(MiembroEntity.class);
             config.addAnnotatedClass(GrupoEntity.class);

             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
