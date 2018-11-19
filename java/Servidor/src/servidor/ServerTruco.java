package servidor;

import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import hbt.HibernateUtil;


//SERVIDOR
public class ServerTruco {
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory();
			new ServidorRmiTruco();
			new ThreadParejas().run();
			
//			Thread t2 = new Thread(new ThreadArmadoParejas());
//			t2.start();
//			
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "No pude arrancar el servidor");
		}
	}
}
