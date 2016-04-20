package nl.zwolle.mvc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EntityManagerManager implements ServletContextListener {

	private static EntityManagerFactory emf; 
	
	private static EntityManagerFactory getEMF(){
		if(emf == null){
			emf = Persistence.createEntityManagerFactory("highscore");
		}
		return emf;
	}
	
	public static EntityManager getEntityManager(){
		return getEMF().createEntityManager();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if(emf != null){
			System.out.println("Closing EntityManagerFactory");
			emf.close();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}
}
