package demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Demo {

	public static void main(String[] args) {
		Person p = new Person();
		p.setAge(26);
		p.setName("Cesar");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("personen");
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		em.persist(p);
		
		t.commit();
	}
}
