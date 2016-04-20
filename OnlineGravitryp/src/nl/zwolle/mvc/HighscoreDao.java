package nl.zwolle.mvc;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public abstract class HighscoreDao {

	public static void add(Highscore highscore) {		
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		em.persist(highscore);
		t.commit();
		
		em.close();
	}
	
	public static List<Highscore> all(){
		EntityManager em = EntityManagerManager.getEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		List<Highscore> highscores = em.createQuery("from Highscore", Highscore.class).getResultList();
		t.commit();
		
		em.close();
		
		return highscores;
	}
}
