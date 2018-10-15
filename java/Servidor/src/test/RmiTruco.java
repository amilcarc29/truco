package test;

import java.rmi.RemoteException;
import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;

import hbt.HibernateUtil;
import servidor.ServidorTruco;
import servidor.ThreadParejas;

//SERVIDOR
public class RmiTruco {
	public static void main(String[] args) {
		
		try {
			
			HibernateUtil.getSessionFactory();
			
			new ServidorTruco();
			new ThreadParejas().run();
			
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "No pude arrancar el servidor");
		}
	}

}
